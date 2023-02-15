package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {

    //리펙토링 CNT+ART+M (1)(2)
    private static MemoryMemberRepository MemberRepository() {//(1)
        return new MemoryMemberRepository(); //이 코드만 바꾸면 된다.
    }

    public DiscountPolicy discountPolicy(){ //(2)
        //return new FixDiscountPolicy(); //이 코드만 바꾸면 된다.
        return new RateDiscountPolicy(); //이 코드만 바꾸면 된다.
    }


    public MemberService memberService123(){
        return new MemberServiceImpl(MemberRepository());
    }



    public OrderService orderService(){
        return new OrderServiceImpl(MemberRepository(),discountPolicy());
    }


}
