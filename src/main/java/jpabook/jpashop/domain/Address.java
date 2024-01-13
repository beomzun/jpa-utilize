package jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable     //내장 타입임을 나타냄
@Getter
public class Address {

    private String city;

    private String street;

    private String zipcode;

    //값 타입은 변경 불가능해야 함 -> 생성자로 초기값 설정 후 변경 불가능하도록 Setter 사용하지 않음.
    //JPA 라이브러리가 객체 생성 시 리플렉션 같은 기술을 사용해야하므로 기본 생성자를 public, protected로 설정해야 함. public보다 안전한 protected 사용
    protected Address() {
    }

    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
