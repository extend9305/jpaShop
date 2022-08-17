package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
// @SpringBootApplication이 하위 패키지
// componet 스캔을 하는데 - > repostory는 component
// 스캔 대상이므로 시작과 동시에 빈으로 등록시켜버림
@RequiredArgsConstructor // 얘는 final 인 것을 생성자로 만들어주는 lombok 기능
public class MemberRepository {

    //@PersistenceContext == > spring jpa 가 @Autowired 로 제공
    private final EntityManager em;
//    @Autowired // 생성자 하나는 생략가능.
//    public MemberRepository(EntityManager em) {
//        this.em = em;
//    }

    public void save(Member member){
        em.persist(member);
    }

    public Member findOne(Long id){
        return em.find(Member.class,id);
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class) //parameter  1 : 쿼리 , 2 : 반환타입.
                .getResultList();
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name",Member.class)
                .setParameter("name",name)
                .getResultList();
    }

}
