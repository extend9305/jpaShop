package jpabook.jpashop.service;

import jpabook.jpashop.domain.Delivery;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    /**
     * 주문
     */
    @Transactional
    public Long order(Long memberId,Long itemId,int count){
        //엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        //OrderItem,Delivery 는 cascade 옵션으로 persist 필요없이 order persist 될때 함께됨.
        //==> 이러한 cascade 옵션은 order만 OrderItem,Delivery를 사용하고 다른곳에서 사용되지 않을때만 이렇게 함.

        //배송정보 생성
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        //주문 상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count);

        //주문생성
        Order order = Order.createOrder(member, delivery, orderItem);

        //주문저장
        orderRepository.save(order);
        return order.getId();
    }

    /**
     *주문 취소
     */

    @Transactional
    public void cancelOrder(Long orderId){
        //주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);
        //주문 취소
        order.cancel();
    }
    //검색
//    public List<Order> findOrders(OrderSearch orderSearch){
//        return orderRepository.findAll(orderSearch);
//    }
}
