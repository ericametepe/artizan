package com.keya.demoelastic;

import com.keya.demoelastic.artiz.domaine.AnnualReport;
import com.keya.demoelastic.artiz.domaine.Invoice;
import org.springframework.core.io.Resource;

import java.util.List;

public interface InvoiceService {

    Invoice save(Invoice invoice);

    Invoice update(String id, Invoice invoice) throws InvoiceResourceNotFoundException;

    List<Invoice> getAll();

    List<AnnualReport> getAllReport();

    void delete(String id);

    Resource preview(String id);

    void send(String id);


    Invoice findById(String id);
}
