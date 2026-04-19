package com.parkalot.controller;

import com.parkalot.model.Invoice;
import com.parkalot.service.InvoiceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
@CrossOrigin(origins = "*")
public class InvoiceApiController {

    private final InvoiceService invoiceService;

    public InvoiceApiController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping("/generate/{email}")
    public Invoice generateInvoice(@PathVariable String email) {
        return invoiceService.generateInvoiceForCustomer(email);
    }

    @GetMapping("/customer/{email}")
    public List<Invoice> getInvoicesByCustomer(@PathVariable String email) {
        return invoiceService.getInvoicesByCustomer(email);
    }
}