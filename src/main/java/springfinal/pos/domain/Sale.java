package springfinal.pos.domain;

import java.time.LocalDateTime;

public class Sale {
    private Long id;
    private String saleId; // 팔린 productId
    private String name;
    private int price;
    private int quantity;
    private LocalDateTime datetime;

    public Sale(String saleId, String name, int price, int quantity, LocalDateTime datetime){
        this.saleId = saleId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.datetime = datetime;
    }
    public Sale(String saleId){
        this.saleId=saleId;
    }
    public Sale(String saleId, int price, LocalDateTime datetime ){
        this.saleId =saleId;
        this.price  = price;
        this.datetime = datetime;
    }
    public Sale(String saleId, int price, int quantity, LocalDateTime datetime){
        this.saleId = saleId;
        this.price = price;
        this.quantity = quantity;
        this.datetime = datetime;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getSaleId() {
        return saleId;
    }
    public void setSaleId(String saleId) {
        this.saleId = saleId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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

    public LocalDateTime getDatetime() {
        return datetime;
    }
    public void setDatetime(LocalDateTime datetime) {
        this.datetime = datetime;
    }

    public int getTotalPrice(){
        return getPrice() * getQuantity();
    }

}
