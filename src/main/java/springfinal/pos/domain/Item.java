package springfinal.pos.domain;

import java.sql.Timestamp;

public class Item {
    private long id; //상품 순번
    private String itemId; // 상품코드
    private String name; // 상품명
    private int price; //  상품 가격
    private Timestamp registerDate; //상품 등록 날짜
    private int quantity;

    public Item(){

    }
    public Item(String itemId, String name, int price, Timestamp registerDate, int quantity){
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.registerDate = registerDate;
        this.quantity = quantity;
    }
    public Item(String itemId,  int quantity){
        this.itemId = itemId;
        this.quantity = quantity;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getItemId() {
        return itemId;
    }
    public void setItemId(String itemId) {
        this.itemId = itemId;
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

    public Timestamp getRegisterDate() {
        return registerDate;
    }
    public void setRegisterDate(Timestamp registerDate) {
        this.registerDate = registerDate;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


//    public void addStock(int quantity){this.quantity += quantity;}
//
//    public void removeStock(int quantity){
//        int remStock = this.quantity - quantity;
//        this.quantity = remStock;
//    }
}
