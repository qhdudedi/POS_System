package springfinal.pos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springfinal.pos.domain.Item;
import springfinal.pos.domain.Sale;
import springfinal.pos.service.ItemService;
import springfinal.pos.service.SaleService;
import springfinal.pos.service.StaticService;

import java.util.List;

@Controller
public class SaleController {
    final SaleService saleService;
    final ItemService itemService;
    final StaticService staticService;

    public SaleController(SaleService saleService, ItemService itemService, StaticService staticService){
        this.saleService = saleService;
        this.itemService = itemService;
        this.staticService= staticService;
    }
    @GetMapping("/sale")
    public String sale(Model model){

        List<Item>  itemList = itemService.findAll();
        model.addAttribute("itemList", itemList);
        List<Sale> saleList = saleService.findAll();
        model.addAttribute("saleList",saleList);

       return "sale/saleItem";
    }
    @PostMapping("/sale")
    public String sale(String saleId){
        List<Sale> saleList = saleService.findSaleBySaleId(saleId);
        for(int i=0; i<saleList.size(); i++) {
            saleService.saleItem(saleId);
        }

        return "redirect:/";
    }
    @GetMapping("/sale/order")
    public String order(Model model){
        List<Sale> saleList = saleService.findAll();
        List<Item> itemList = itemService.findAll();

        model.addAttribute("saleList",saleList);
        model.addAttribute("itemList",itemList);

        int total = 0;
        for(int i=0; i<saleList.size(); i++){
            total += saleList.get(i).getTotalPrice();
        }
        model.addAttribute("total",total);
        return "sale/cartList";
    }
    @PostMapping(value = "sale/order")
    public String order(@RequestParam("itemId") String itemId
                        ,@RequestParam("quantity") int quantity) {
        saleService.addCart(itemId,quantity);
        itemService.updateList(itemId,quantity); // 주문한 만큼 재고 감소
        return "sale/cartList";
    }

}

