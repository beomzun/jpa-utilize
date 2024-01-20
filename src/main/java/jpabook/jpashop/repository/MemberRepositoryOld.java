package jpabook.jpashop.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor        //final 설정된 필드들을 포함하는 생성자 자동 생성
public class MemberRepositoryOld {

//    @PersistenceContext     //영속성 컨텍스트 관리를 위한 Entity Manager 주입
//    private EntityManager em;

//    Required 사용으로 @Autowired 생략 가능
    private final EntityManager em;

    public void save(Member member) {
        em.persist(member);
    }

    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        //List를 반환해야 할 경우, JPQL 사용.
        //SQL은 테이블을 대상으로 하는 쿼리. JPQL은 엔티티 객체를 대상으로 함
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
