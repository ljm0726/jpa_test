package jpabook.jpashop.Service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) //기본적으로 Transactional이 걸림
@RequiredArgsConstructor //final 필드만 생성자를 만들어줌 ->
                        // memberRepository생성자가 만들어지고
                        //@Autowired는 생성자 하나만 있는경우 생략 가능
public class MemberService {

    private final MemberRepository memberRepository;

//    @Autowired //생성자가 하나 있는 경우 자동으로 주입 해줌
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    //회원가입
    @Transactional //readOnly=false
    public Long join(Member member){
        validateDuplicateMember(member); //중복 회원 검증
        memberRepository.save(member);
        return member.getId(); //em.persist를 통해 영속성 Context에 있기 때문에 항상 존재함
    }

    //중복 회원 검증
    private void validateDuplicateMember(Member member) { //실무에서는 최후의 방어를 해줘야함. 2명이 동시에 가입하는 경우(name에 unique 조건걸기)
        //EXCEPTION
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if(!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원 입니다.");
        }
    }

    //회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }
}
