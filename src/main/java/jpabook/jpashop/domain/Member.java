package jpabook.jpashop.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Embedded       //내장 타입을 포함했다는 어노테이션.   Address 클래스에 @Embeddable 와 둘 중 하나만 사용해도 되지만 둘 다 표시해줌
    private Address address;

    @OneToMany(mappedBy = "member")     //mappedBy를 통해 연관관계의 주인 설정. mappedBy를 사용한 필드는 자신이 변경하지 않고 변경됨 = 읽기 전용. 사용된 필드(member)는 자신이 변경함
    private List<Order> orders = new ArrayList<>();
}
