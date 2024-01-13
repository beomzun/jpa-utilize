package jpabook.jpashop.domain.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@DiscriminatorValue("B")    //지정 안할시 클래스명으로 자동 지정 ex)Book
@Getter
@Setter
public class Book extends Item {

    private String author;
    private String isbn;

}
