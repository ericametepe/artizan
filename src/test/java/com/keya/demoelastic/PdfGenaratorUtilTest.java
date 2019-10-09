package com.keya.demoelastic;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import static org.junit.Assert.*;
@Slf4j
public class PdfGenaratorUtilTest {

    @Test
    public void generatePDF() throws IOException {

       log.info(" result : {}" + String.join("_","INVOICE","54774746",".pdf"));

        String invoice_54774746 = Paths.get("/Users/kodjovi1", "INVOICE_54774746").toString();
        log.info(" {}"+ new File(invoice_54774746+".pdf").createNewFile());



    }
}