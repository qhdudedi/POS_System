package springfinal.pos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springfinal.pos.domain.Sale;
import springfinal.pos.domain.SaleItem;
import springfinal.pos.service.StaticService;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;


@Controller
public class StaticController {

    private final StaticService staticService;

    @Autowired
    public StaticController(StaticService staticService) {
        this.staticService = staticService;
    }

    @GetMapping(value = "/static")
    public String staticList(Model model) {
        List<Sale> sales = staticService.getAllSale();
        model.addAttribute("sales", sales);
        return "static/staticView";
    }

    /**최다 판매 제품 출력*/
    @GetMapping("/static/popularItem")
    public String getMaxSoldProduct(Model model) {
        List<SaleItem> maxSoldProduct = staticService.getMaxSoldProducts();
        model.addAttribute("maxSoldProduct", maxSoldProduct);
        return "static/max_sold_product";
    }

    /**
     * 하루 총 판매금액
     */
    @GetMapping("/sale/day")
    public String getDailySalesAmount(@RequestParam("date") String date, Model model) {
        LocalDate parsedDate = LocalDate.parse(date);
        int dailySalesAmount = staticService.getDailySalesPrice(parsedDate);
        model.addAttribute("date", date);
        model.addAttribute("dailySalesAmount", dailySalesAmount);
        return "static/staticPrice";
    }

    /**
     * 일주일 총 판매 금액
     */
    @GetMapping("/sale/weekly")
    public String getWeeklySalesPrice(@RequestParam("startDate") String startDate, Model model) {
        LocalDate parsedStartDate = LocalDate.parse(startDate);
        int weeklySalesAmount = staticService.getWeekSalePrice(parsedStartDate);
        model.addAttribute("startDate", startDate);
        model.addAttribute("weeklySalesAmount", weeklySalesAmount);
        return "static/staticPrice";
    }

    /**
     * 월별 총 판매 금액
     */
    @GetMapping("/sale/monthly")
    public String getMonthlySalesAmount(@RequestParam("yearMonth") String yearMonth, Model model) {
        YearMonth parsedMonth = YearMonth.parse(yearMonth);
        int monthlySaleAmount = staticService.getMonthlySalesPrice(parsedMonth);
        model.addAttribute("yearMonth", yearMonth);
        model.addAttribute("monthlySalesAmount", monthlySaleAmount);

        return "static/staticPrice";
    }
    @GetMapping("/sale/dailyItem")
    public String getSalesByDay(@RequestParam("date") String st_data, Model model) {
        LocalDate date = LocalDate.parse(st_data);

        List<SaleItem> salesList = staticService.getSalesInfoByDay(date);
        int totalSales = staticService.getTotalSalesByDate(date);

        model.addAttribute("salesList", salesList);
        model.addAttribute("totalSales", totalSales);

        return "static/staticView";
    }
    @GetMapping("/sale/weeklyItem")
    public String getWeeklySaleItem(@RequestParam("startDate") String startDate, Model model) {
        LocalDate parsedStartDate = LocalDate.parse(startDate);

        List<SaleItem> salesList = staticService.getSalesInfoByWeekly(parsedStartDate);
        int totalSales = staticService.getTotalSalesByWeekly(parsedStartDate);

        model.addAttribute("salesList", salesList);
        model.addAttribute("totalSales", totalSales);

        return "static/staticView";
    }
    @GetMapping("/sales/monthly")
    public String getSalesByMonth(@RequestParam("yearMonth") YearMonth yearMonth, Model model) {
        List<SaleItem> salesList = staticService.getSalesByMonth(yearMonth);
        int totalSales = staticService.getTotalSalesByMonth(yearMonth);

        model.addAttribute("salesList", salesList);
        model.addAttribute("totalSales", totalSales);

        return "static/staticView";
    }
}


