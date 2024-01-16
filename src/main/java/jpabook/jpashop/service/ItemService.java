package jpabook.jpashop.service;

import java.util.List;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    //new Book 으로 새로운 엔티티를 만들어서 기존값 세팅 후 저장하는 방식이 아님
    //Transactional 걸고 itemId로 영속성 컨텍스트에 존재하는 item 을 찾은 후, 해당 item 의 필드값 변경.
    //메서드 종료되면서 commit 시점에 변경감지(Dirty Checking) 동작. 알아서 commit 되므로 따로 메서드를 호출할 필요 없음.
//    @Transactional
//    public void updateItem(Long itemId, Book param) {
//        Item findItem = itemRepository.findOne(itemId);
//        //예제라 set을 사용했지만, 의미있는 메서드를 통해 관리해야함.
//        //set 남발 시 추후 어디서 값이 변경되는지 찾아야 하는 고생을 겪음
//
//        findItem.setPrice(param.getPrice());
//        findItem.setName(param.getName());
//        findItem.setStockQuantity(param.getStockQuantity());
//    }

    @Transactional
    public void updateItem(Long itemId, String name, int price, int stockQuantity) {
        Item findItem = itemRepository.findOne(itemId);

        findItem.setName(name);
        findItem.setPrice(price);
        findItem.setStockQuantity(stockQuantity);
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOne(Long itemId) {
        return itemRepository.findOne(itemId);
    }
}
