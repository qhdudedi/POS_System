package springfinal.pos.service;

import springfinal.pos.dao.ItemDao;
import springfinal.pos.domain.Item;

import java.util.List;

public class ItemService {
    private ItemDao itemDao;

    public ItemService(ItemDao itemDao){ this.itemDao = itemDao;}

    /**상품등록*/
    public void regist(Item item){
        itemDao.read(item);
    }

    public List<Item> findAll(){
        return itemDao.selectAll();
    }

    public Item findById(Long id){
        return itemDao.selectById(id);
    }

    public List<Item>findByItemId(String itemId){return itemDao.findByItemId(itemId);}

    public Item findByCode(String itemId){
        return itemDao.selectByItemId(itemId);
    }
    public void update(Item item){
        itemDao.update(item);
    }
    public void updateList(String itemId, int quantity){
        Item item = itemDao.selectByItemId(itemId);
        item.setQuantity(item.getQuantity()-quantity);
        itemDao.update(item);
    }
    public void editStock(String itemId, int quantity){
        Item item = itemDao.selectByItemId(itemId);
        item.setQuantity(item.getQuantity()+quantity);
        itemDao.edit(item);
    }

}
