package springfinal.pos.domain;

import java.time.LocalDateTime;

public class SaleItem {
    private String saleId;
    private int price;
    private int quantity;
    private LocalDateTime dateTime;

    public SaleItem() {
    }

    public SaleItem(String saleId, int price, int quantity) {
        this.saleId = saleId;
        this.price = price;
        this.quantity = quantity;
    }

    public SaleItem(String saleId, int price, int quantity, LocalDateTime dateTime) {
        this.saleId = saleId;
        this.price = price;
        this.quantity = quantity;
        this.dateTime = dateTime;
    }

    public String getSaleId() {
        return saleId;
    }

    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
