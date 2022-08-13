package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="orders")
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems =  new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "delivery_id")
    private Deliver y delivery;

    private LocalDateTime orderDate; //하이버네이트가 자동지원

    @Enumerated(EnumType.STRING)//  ORDINAL,STRING 두개가있는데 꼭 string으로 써야함 이유는 ORDINAL은 숫자기 때문에 중간에 추가가 된다면 꼬임.
    private OrderStatus status; //주문상태[ORDER,CANCEL]




}
