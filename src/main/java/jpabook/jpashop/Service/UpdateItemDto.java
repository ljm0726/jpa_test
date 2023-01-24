package jpabook.jpashop.Service;

import lombok.Getter;

@Getter
public class UpdateItemDto {
    private Long itemId;
    private String name;
    private int price;
    private int stockQuantity;
}
