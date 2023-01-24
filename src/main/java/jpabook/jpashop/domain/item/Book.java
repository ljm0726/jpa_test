package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("B") //SingleTable일 때 저장할 때 구분해줘야함
@Getter
@Setter
public class Book extends Item{
    private String author;
    private String isbn;

}
