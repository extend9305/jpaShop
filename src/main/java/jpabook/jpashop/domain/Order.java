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

    // 기본이 EAGER 그래서 LAZY 로 모두 바꿔줘야한다.
    // 1.이유는 EAGER는 JPQL 사용시 order  조회를 날렸을때 관련된 member가 같이 조회된다.
    // 2. 그러면 order가  1000개의 member가 관련되어있을경우 1000개를 조회하는 쿼리가 날라가기 때문에
    // 3. 망함. 그렇기 때문에 LAZY를 사용하는걸 기본원칙으로 한다.

    @ManyToOne(fetch = FetchType.LAZY) // 기본이 EAGER 그래서 LAZY 로 모두 바꿔줘야한다.
    @JoinColumn(name = "member_id")
    private Member member;
    // 기본 LAZY
    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    //cascade 는 order 가 저장시 orderItem들도 함께 상태변화를 전이시키는것.
    private List<OrderItem> orderItems =  new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    //order 를 persist 할때 delivery도 persist같이.
    private Delivery delivery;

    private LocalDateTime orderDate; //하이버네이트가 자동지원

    @Enumerated(EnumType.STRING)//  ORDINAL,STRING 두개가있는데 꼭 string으로 써야함 이유는 ORDINAL은 숫자기 때문에 중간에 추가가 된다면 꼬임.
    private OrderStatus status; //주문상태[ORDER,CANCEL]

    //==연관관계 메서드 ==//
    //양방향 일때 사용하는 메소드
    public void setMember(Member member){
        this.member = member;
        member.getOrders().add(this);
    }
    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
    public void setDelivery(Delivery delivery){
        this.delivery = delivery;
        delivery.setOrder(this);
    }

}
