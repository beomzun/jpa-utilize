package jpabook.jpashop.service;

import static org.junit.jupiter.api.Assertions.*;

import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.ItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class ItemServiceTest {

    @Autowired
    ItemService itemService;

    @Autowired
    ItemRepository itemRepository;

    @Test
    public void 상품등록() throws Exception {
        //given
        Item item = new Book();
        item.setStockQuantity(100);

        //when
        item.removeStock(80);
        itemService.saveItem(item);

        Exception e = Assertions.assertThrows(NotEnoughStockException.class, () ->
                item.removeStock(50)
        );

        //then
        assertEquals("need more stock", e.getMessage());
    }
}