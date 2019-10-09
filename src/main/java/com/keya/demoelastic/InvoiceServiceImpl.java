package com.keya.demoelastic;

import com.google.common.collect.Lists;
import com.keya.demoelastic.artiz.domaine.AnnualReport;
import com.keya.demoelastic.artiz.domaine.Invoice;
import com.keya.demoelastic.artiz.infra.InvoiceEntity;
import com.keya.demoelastic.artiz.infra.InvoiceEntityRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.ResourceNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Slf4j
@Component
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceEntityRepository invoiceEntityRepository;

    private final PdfGenaratorUtil pdfGenaratorUtil;


    @Override
    public Invoice save(Invoice invoice) {
        //build no off-side effects
        invoice.setId(UUID.randomUUID().toString());
        invoice.setStatus(InvoiceStatusEnum.NEW.getCode());

        InvoiceEntity invoiceEntity = InvoiceEntity.builder().build();

        BeanUtils.copyProperties(invoice, invoiceEntity);
        InvoiceEntity invoiceSaved = invoiceEntityRepository.save(invoiceEntity);
        Invoice invoiceResult=Invoice.builder().build();
        BeanUtils.copyProperties(invoiceSaved,invoiceResult);
        return invoiceResult;
    }

    @Override
    public Invoice update(String id, Invoice invoice) {
        invoiceEntityRepository.findById(id).orElseThrow(() -> new InvoiceResourceNotFoundException());

        InvoiceEntity invoiceEntity = InvoiceEntity.builder().id(id).build();

        BeanUtils.copyProperties(invoice, invoiceEntity);
        invoiceEntity.setId(id);
       final InvoiceEntity saveInvoiceEntity = invoiceEntityRepository.save(invoiceEntity);
       BeanUtils.copyProperties(saveInvoiceEntity,invoice);
        return invoice;
    }


    @Override
    public List<Invoice> getAll() {
        List<InvoiceEntity> invoiceEntities = Lists.newArrayList(invoiceEntityRepository.findAll());
         return  CollectionUtils.isEmpty(invoiceEntities)? Lists.newArrayList():invoiceEntities.stream()
                 .map(invoiceEntity -> {
                     Invoice invoice =Invoice.builder().build();
                     BeanUtils.copyProperties(invoiceEntity,invoice);
                  return invoice;})
                 .collect(Collectors.toList()) ;

    }

    @Override
    public List<AnnualReport> getAllReport() {
        List<Invoice> invoices = this.getAll().stream().filter(inv -> Objects.nonNull(inv.getYear())).collect(Collectors.toList());

       final List<AnnualReport> annualReports = invoices.stream().map(Invoice::getYear).filter(Objects::nonNull
        ).distinct().map(
                integer ->
                {
                    AnnualReport annualReport = AnnualReport.builder()
                            .year(integer).totalValue(BigDecimal.ZERO).workeddays(BigDecimal.ZERO).build();
                    return annualReport;
                }).collect(Collectors.toList());

        final List<AnnualReport> results = new ArrayList<>();

        annualReports.forEach(annualReport -> {
            BigDecimal wkDaysAccumulated = invoices.stream().filter(inv->Objects.nonNull(inv.getYear()))
                    .filter(invoice -> invoice.getYear().equals(annualReport.getYear())).map(Invoice::getWorkeddays)
                    .reduce((integer, integer2) -> integer.add(integer2)).get();

            BigDecimal totValAccumulated = invoices.stream().filter(invoice -> invoice.getYear().equals(annualReport.getYear())).map(invoice -> {
                return invoice.getDailywage().multiply(invoice.getWorkeddays());
            })
                    .reduce((bigDecimal, bigDecimal2) -> bigDecimal.add(bigDecimal2)).get();

            results.add(AnnualReport.builder().totalValue(totValAccumulated).year(annualReport.getYear()).workeddays(wkDaysAccumulated).build());
        });

        results.sort(Comparator.comparingInt(AnnualReport::getYear));

        return results;
    }

    @Override
    public void delete(String id) {
        InvoiceEntity invoiceEntity = invoiceEntityRepository.findById(id).orElseThrow(() -> new InvoiceResourceNotFoundException());
        if(InvoiceStatusEnum.VALID.getCode().equalsIgnoreCase(invoiceEntity.getStatus())){
            throw new InvoiceResourceIllegalOperationException("Invoice with id: "+id +" is at status" +invoiceEntity.getStatus());
        }
            invoiceEntityRepository.deleteById(id);
    }

    @Override
    public Resource preview(String id) {
        InvoiceEntity invoiceEntity =  invoiceEntityRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id));
        Invoice invoice = Invoice.builder().build();
        BeanUtils.copyProperties(invoiceEntity, invoice);
        File resultFile=null;
        Resource urlResource = null;

        Map<String, Object> invoiceMap = new HashMap();
        invoiceMap.put("invoice",invoice);
        try {
            resultFile=pdfGenaratorUtil.generatePDF(id,"invoice",invoiceMap);
        } catch (IOException e) {
           log.error(e.getMessage(), e);
        }

        try {
              urlResource = new UrlResource(resultFile.toURI());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return urlResource;
    }

    @Override
    public void send(String id) {

    }

    @Override
    public Invoice findById(String id) throws ResourceNotFoundException {
        InvoiceEntity invoiceEntity =  invoiceEntityRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(id));
        Invoice invoice = Invoice.builder().build();
        BeanUtils.copyProperties(invoiceEntity, invoice);
        return invoice;
    }


}
