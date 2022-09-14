package mybookstore.domain;

import org.zkoss.zhtml.Big;
import tools.dynamia.domain.jpa.SimpleEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@Table(name = "invoice_details")
public class InvoiceDetail extends SimpleEntity {
    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;
    @OneToOne
    @JoinColumn(name = "book_id")
    @NotNull
    private Book book;
    private int quantity = 1;
    private BigDecimal subtotal;

    public BigDecimal calcSubtotal() {
        if (book != null) {
            var old = this.subtotal;
            subtotal = BigDecimal.valueOf(quantity).multiply(book.getPrice());
            notifyChange("subtotal", old, subtotal);
        }
        return subtotal;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
        calcSubtotal();
    }


    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        calcSubtotal();
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
}