package com.keya.demoelastic;

public class InvoiceResourceNotFoundException extends RuntimeException{


    public InvoiceResourceNotFoundException(){
        super();
    }

    public InvoiceResourceNotFoundException(String message){
        super(message);
    }
}
