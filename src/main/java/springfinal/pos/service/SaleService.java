package springfinal.pos.service;

import org.springframework.beans.factory.annotation.Autowired;
import springfinal.pos.dao.ItemDao;
import springfinal.pos.dao.SaleDao;
import springfinal.pos.domain.Item;
import springfinal.pos.domain.Sale;

import java.time.LocalDateTime;
import java.util.List;

public class SaleService {
    final SaleDao saleDao;
    final ItemDao itemDao;

    @Autowired
    public SaleService(SaleDao saleDao, ItemDao itemDao) {
        this.saleDao = saleDao;
        this.itemDao = itemDao;
    }

    public void saleItem(String saleId) {
        LocalDateTime nowDateTime = LocalDateTime.now();
        Sale sale = new Sale(saleId);
        saleDao.insert(sale);
    }

    public List<Sale> findSaleBySaleId(String saleId) {
        return saleDao.selectBySaleId(saleId);
    }

    public void addCart(String saleId, int quantity) {
        Item item = itemDao.selectByItemId(saleId);
        item.setQuantity(quantity);
        itemDao.add(item);
    }

    public List<Sale> findAll() {
        return saleDao.selectAll();
    }
}