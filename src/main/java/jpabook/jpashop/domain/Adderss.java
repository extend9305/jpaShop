package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter @Setter
public class Adderss {
    private String city;
    private String street;
    private String zipcode;

}
