package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    //private final MemberRepository memberRepository = new MemoryMemberRepository(); //(1)구현객체를 선택해준다.
    private final MemberRepository memberRepository ; //(2) AppConfig에 등록했을경우 지우고 아래 생성자를 만든다.

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
    
    //테스트 용도

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
