package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import java.util.List;
import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item) {
        if (item.getId() == null) {
            em.persist(item);
        } else {
            //변경하려는 값만 설정하는것이 아니라, 모든 값을 다시 재설정하는것. 의도치 않게 null로 설정 될 수 있음
            //put과 patch의 차이점과 동일한 것 같다.
            //merge 말고 JPA의 변경 감지를 사용하는 것이 깔끔
            em.merge(item);
        }
    }

    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }
}
