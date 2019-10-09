package com.keya.demoelastic;

import com.keya.demoelastic.artiz.domaine.AnnualReport;
import com.keya.demoelastic.artiz.domaine.Invoice;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import javax.print.attribute.standard.Media;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(allowedHeaders = "*")
@AllArgsConstructor
@Slf4j
public class InvoiceResource {
    private final InvoiceService invoiceService;


@PostMapping(value = "/invoices")
    public ResponseEntity<Invoice> create(@RequestBody Invoice invoice){
        return new ResponseEntity<>(invoiceService.save(invoice), HttpStatus.CREATED);
    }

    @PutMapping(value = "/invoices/{id}")
    public ResponseEntity<Invoice> update(@RequestBody Invoice invoice, @PathVariable String id){
        return new ResponseEntity<>(invoiceService.update(id, invoice), HttpStatus.OK);
    }


    @GetMapping(value = "/invoices/{id}")
    public ResponseEntity<Invoice> getByID(@PathVariable String id){
        return new ResponseEntity<>(invoiceService.findById(id), HttpStatus.OK);
    }
    @DeleteMapping(value = "/invoices/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        log.info("Delete resource with : "+id);
        invoiceService.delete(id);
        log.info("Resource deleted with : "+id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/invoices/{id}/preview",produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<Resource> generateView(@PathVariable String id, HttpServletRequest request){

    Resource resource = invoiceService.preview(id);
    String contentType=null;

        try {
            contentType=request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (StringUtils.isEmpty(contentType)){
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"" )
                .body(resource);
    }

    @PostMapping(value = "/invoices/{id}")
    public ResponseEntity<?> send(@PathVariable String id){
        invoiceService.send(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/invoices")
    public ResponseEntity<List<Invoice>> getAll(){
        return new ResponseEntity<>(invoiceService.getAll(), HttpStatus.OK);
    }


    @GetMapping(value = "/invoices/report")
    public ResponseEntity<List<AnnualReport>> getReport(){
        return new ResponseEntity<>(invoiceService.getAllReport(), HttpStatus.OK);
    }


}
