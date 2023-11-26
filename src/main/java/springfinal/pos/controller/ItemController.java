package springfinal.pos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springfinal.pos.domain.Item;
import springfinal.pos.domain.ItemForm;
import springfinal.pos.service.ItemService;

import java.util.List;

@Controller
public class ItemController {
    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService){
        this.itemService = itemService;
    }

    @GetMapping("/items/new")
    public String create(Model model){
        model.addAttribute("itemForm", new ItemForm());
        return "items/itemForm";
    }
    @PostMapping("items/new")
    public String create(ItemForm form){
        Item item = new Item();
        item.setItemId(form.getItemId());
        item.setName(form.getName());
        item.setPrice(form.getPrice());
        item.setRegisterDate(form.getRegisterDate());
        item.setQuantity(form.getQuantity());
        itemService.regist(item);
        return "redirect:/";
    }
    @GetMapping("/items/list")
    public String itemList(Model model){
        List<Item> itemList =  itemService.findAll();
        model.addAttribute("items", itemList);
        return "items/showItemList";
    }
    @GetMapping("/items/update")
    public String updateItemList(Model model){
        List<Item> itemList =  itemService.findAll();
        model.addAttribute("items", itemList);
        return "items/updateItemForm";
    }
    @GetMapping("/items/update/{itemId}")
    public String updateItem(@PathVariable("itemId")String itemId, Model model){
        Item foundItem = itemService.findByCode(itemId);
        List<Item> itemList = itemService.findByItemId(itemId);
        model.addAttribute("foundItem", foundItem);
        model.addAttribute("itemList", itemList);
        return "items/updateItemForm";
    }
    @PostMapping("items/update/{itemId}")
    public String updateItem(@RequestParam("itemId") String itemId,
                             @RequestParam("quantity") int quantity) {
        itemService.editStock(itemId,quantity);
        return "redirect:/";
    }
}
