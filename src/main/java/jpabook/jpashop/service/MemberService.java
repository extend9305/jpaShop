package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)// jpa 데이터 변경이나 로직은 항상 트랜잭션 안에서 해야함. spring이 지원해주는거 사용하는게 좋음.

@RequiredArgsConstructor // 롬복기능 final 에 있는 것만 생성자 만들어줌
public class MemberService {
    //@Autowired // filed injection
    private final MemberRepository memberRepository; // final 하는게 권장.

//    @Autowired // setter injection
//    public void setMemberRepository(MemberRepository memberRepository) { // test mock 주입가능.
//        this.memberRepository = memberRepository;
//    } 이것도 안좋음

//    @Autowired // 생성자 injection 최신버전에는 생성자가 하나일경우 없어도됨.
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    /**
     *회원 가입
     */
                    // 기본적으로 readOnly가 false 임으로 따로설정안한다.
    @Transactional  // 밑에 조회가많으므로 class 단위에서 readonly true 처리
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        List<Member> findBymembers = memberRepository.findByName(member.getName());

        if(!findBymembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

    }

    //회원 전체조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Member findOne(Long id){
        return memberRepository.findOne(id);
    }
}
