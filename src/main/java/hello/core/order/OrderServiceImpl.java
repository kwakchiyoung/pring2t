package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor //final필드값을 인식하여 생성자를 자동으로 만들어준다...
public class OrderServiceImpl implements OrderService{

    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    //private final DiscountPolicy discountPolicy     = new FixDiscountPolicy(); //고정할인정책
    //private final DiscountPolicy discountPolicy     = new RateDiscountPolicy(); //퍼센트할인정책
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    /* (2)수정자 주입(setter 주입)
    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }
    */
     

    // @RequiredArgsConstructor 를 넣었기에 주석처리.
    @Autowired //생성자가 하나면 생략가능 스프링 빈에서만 해당          //@Qualifier("mainDiscountPolicy") //(1)이걸 지우고 우리가 직접 만들어준

    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) { //@Autowired 타입매칭이 두개일경우 @Qualifier를 사용하여 새로운 별칭자를 정해준다.
        //1.타입매칭 , 타입 매칭 결과가 2개이상일때 필드 명,파라미터 명으로 빈 이름 매칭 discountPolicy->rateDiscountPolicy
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }



    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);            //(1)회원정보조회
        int discountPrice = discountPolicy.discount(member, itemPrice); //(2)할인정책에 회원을 넘겨 할인금을 받고

        return new Order(memberId , itemName,itemPrice,discountPrice);  //(3)최종생성된 주문을 반환시킨다.
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
