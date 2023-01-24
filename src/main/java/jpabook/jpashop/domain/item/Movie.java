package jpabook.jpashop.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("M") //SingleTable일 때 저장할 때 구분해줘야함
@Getter
@Setter
public class Movie extends Item{
    private String director;
    private String actor;
}
