package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable //값타입은 변경되면 안됨 Setter를 제거하고 생성자에서 값을 모두 초기화 한다.
@Getter
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address() { //임베디드 타입은 기본생성자를 protected로 설정하는 것이 안전하다.
    }

    public Address(String city, String street, String zipcode) { //리플랙션이나 프록시같은 기술을 사용하기 위해
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
