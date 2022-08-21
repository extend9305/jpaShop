package jpabook.jpashop.repository;

import jdk.jfr.Registered;
import jpabook.jpashop.domain.Order;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;

@Registered
@RequiredArgsConstructor
public class OrderRepository {
    public final EntityManager em;

    public void save(Order order){
        em.persist(order);
    }

    public Order findOne(Long id){
        return em.find(Order.class,id);
    }

    //public List<Order> findAll(OrderSearch orderSearch){}
}









































