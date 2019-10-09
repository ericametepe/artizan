package com.keya.demoelastic;

import com.itextpdf.text.DocumentException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.w3c.tidy.Tidy;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;

import static org.apache.commons.codec.CharEncoding.UTF_8;

@Component
@Slf4j
public class PdfGenaratorUtil {

    @Value("${invoice.storage.dir:/}")
    private   String directory;
    private final SpringTemplateEngine templateEngine;

    public PdfGenaratorUtil(SpringTemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }


    public File generatePDF(String invoiceId, String templateName, Map dataMap) throws IOException {
        Assert.notNull(templateName, "The templateName can not be null");
        Context ctx = new Context();

        if (dataMap != null) {
            Iterator itMap = dataMap.entrySet().iterator();
            while (itMap.hasNext()) {
                Map.Entry pair = (Map.Entry) itMap.next();
                ctx.setVariable(pair.getKey().toString(), pair.getValue());
            }
        }

        File outputFile;

        String processedHtml = templateEngine.process(templateName, ctx);
        FileOutputStream fileOutputStream = null;

        String fileName = String.join("_", "INVOICE", invoiceId).concat(".pdf");
        try {
             //outputFile = File.createTempFile(fileName, ".pdf");
             Path path = Paths.get(directory,fileName);

             outputFile= new File(path.normalize().toString());

            outputFile.createNewFile();
            fileOutputStream = new FileOutputStream(outputFile);
            // itext soap render
            ITextRenderer renderer = new ITextRenderer();

            // convert html to xhtml
            String xhtml =  convertToXhtml(processedHtml);


            // FlyingSaucer has a working directory. src/main/resources
            String baseUrl = FileSystems
                    .getDefault()
                    .getPath("src", "main", "resources", "templates")
                    .toUri()
                    .toURL()
                    .toString();

            renderer.setDocumentFromString(xhtml,baseUrl);

            renderer.layout();
            try {
                renderer.createPDF(fileOutputStream, false);
            } catch (DocumentException e) {
                log.error(e.getMessage(),e);
            }
            renderer.finishPDF();
            log.info("PDF created successfully:{},{}"+outputFile.getAbsolutePath());
        }
        finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    log.error(e.getMessage(),e);
                }
            }
        }
        return outputFile;
    }

    private String convertToXhtml(String html) throws UnsupportedEncodingException {

        Tidy tidy = new Tidy();
        tidy.setInputEncoding(UTF_8);
        tidy.setOutputEncoding(UTF_8);
        tidy.setXHTML(true);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(html.getBytes(UTF_8));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        tidy.parseDOM(inputStream, outputStream);
        return outputStream.toString(UTF_8);
    }


}
