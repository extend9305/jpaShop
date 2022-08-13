package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;
import org.apache.tomcat.jni.Address;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Delivery {
    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)//  ORDINAL,STRING 두개가있는데 꼭 string으로 써야함 이유는 ORDINAL은 숫자기 때문에 중간에 추가가 된다면 꼬임.
    private DeliveryStatus status;  //READY ,COMP


}
