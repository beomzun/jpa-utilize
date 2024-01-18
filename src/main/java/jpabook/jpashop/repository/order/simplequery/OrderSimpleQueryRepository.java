package jpabook.jpashop.repository.order.simplequery;

import jakarta.persistence.EntityManager;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class OrderSimpleQueryRepository {

    private final EntityManager em;

    //jpa 는 엔티티나 value object(ex.address) 만 반환할 수 있음. 다른 dto 사용하려면 new 키워드 사용해야 해서 더러워짐.
    public List<OrderSimpleQueryDto> findOrderDtos() {
        return em.createQuery(
                        "select new jpabook.jpashop.repository.order.simplequery.OrderSimpleQueryDto(o.id, m.name, o.orderDate, o.status, d.address)" +
                                " from Order o" +
                                " join o.member m" +
                                " join o.delivery d", OrderSimpleQueryDto.class)
                .getResultList();
    }
}
