package jpabook.jpashop;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class MemberRepository {//dao

    @PersistenceContext //찾아보기 스프링컨테이너가 주입해줌.
    private EntityManager em;

    public Long save(Member member){
        em.persist(member); //insert 기능
        return member.getId();
    }

    public Member find(Long id){
        return em.find(Member.class,id);
    }



}
