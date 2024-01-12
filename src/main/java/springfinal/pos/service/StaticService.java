package springfinal.pos.service;

import org.springframework.beans.factory.annotation.Autowired;
import springfinal.pos.dao.ItemDao;
import springfinal.pos.dao.SaleDao;
import springfinal.pos.dao.StaticDao;
import springfinal.pos.domain.Sale;
import springfinal.pos.domain.SaleItem;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

public class StaticService {
    final ItemDao itemDao;
    private final SaleDao saleDao;
    private final StaticDao staticDao;

    @Autowired
    public StaticService(ItemDao itemDao, SaleDao saleDao, StaticDao staticDao) {
        this.itemDao = itemDao;
        this.saleDao = saleDao;
        this.staticDao = staticDao;
    }
    public List<Sale> getAllSale() {
        return saleDao.selectAll();
    }

    /**
     * 최다 판매 제품
     */
    public  List<SaleItem> getMaxSoldProducts() {
        return staticDao.getMaxSoldProducts();
    }

    /**
     * 하루 판매 금액
     */
    public int getDailySalesPrice(LocalDate date) {
        return staticDao.getDailySales(date);
    }

    /**
     * 일주일 판매 금액
     */
    public int getWeekSalePrice(LocalDate startDate) {
        return staticDao.getWeeklySales(startDate);
    }

    /**
     * 한달 판매 금액
     */
    public int getMonthlySalesPrice(YearMonth yearMonth) {
        return staticDao.getMonthlySales(yearMonth);
    }

    /**
     * 하루 판매량
     */
    public List<SaleItem> getSalesInfoByDay(LocalDate date) {
        return staticDao.getSalesInfoByDay(date);
    }

    public int getTotalSalesByDate(LocalDate date) {
        return staticDao.getTotalSalesByDate(date);
    }

    /**
     * 주간 판매량
     */
    public List<SaleItem> getSalesInfoByWeekly(LocalDate startDate) {
        return staticDao.getSalesInfoByWeekly(startDate);
    }

    public int getTotalSalesByWeekly(LocalDate startDate) {
        return staticDao.getTotalSalesByWeekly(startDate);
    }

    /**
     * 월별 판매량
     */
    public List<SaleItem> getSalesByMonth(YearMonth yearMonth) {
        return staticDao.getSalesByMonth(yearMonth);
    }

    public int getTotalSalesByMonth(YearMonth yearMonth) {
        return staticDao.getTotalSalesByMonth(yearMonth);
    }
}