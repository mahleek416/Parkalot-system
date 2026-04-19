package com.parkalot.service;

import com.parkalot.model.Invoice;
import com.parkalot.model.Reservation;
import com.parkalot.repository.InvoiceRepository;
import com.parkalot.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final ReservationRepository reservationRepository;

    public InvoiceService(InvoiceRepository invoiceRepository, ReservationRepository reservationRepository) {
        this.invoiceRepository = invoiceRepository;
        this.reservationRepository = reservationRepository;
    }

    public Invoice generateInvoiceForCustomer(String email) {

        List<Reservation> reservations = reservationRepository.findByEmail(email);

        if (reservations.isEmpty()) {
            return null;
        }

        String month = reservations.get(0).getMonth();

        double total = reservations.stream()
                .mapToDouble(r -> r.getCharge() != null ? r.getCharge() : 0.0)
                .sum();

        // 🔥 CHECK IF INVOICE ALREADY EXISTS
        List<Invoice> existingInvoices = invoiceRepository.findByEmail(email);

        Invoice invoice;

        if (!existingInvoices.isEmpty()) {
            // 👉 Update existing invoice for same month
            invoice = existingInvoices.stream()
                    .filter(i -> month.equals(i.getBillingMonth()))
                    .findFirst()
                    .orElse(new Invoice());
        } else {
            invoice = new Invoice();
        }

        invoice.setEmail(email);
        invoice.setBillingMonth(month);
        invoice.setTotalAmount(total);
        invoice.setStatus("Pending");

        return invoiceRepository.save(invoice);
    }

    public List<Invoice> getInvoicesByCustomer(String email) {
        return invoiceRepository.findByEmail(email);
    }
}