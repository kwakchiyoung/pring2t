package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //(3)지금부터는 스프링기반으로 변경 구성정보 안에 @Bean라 적힌 메서드를 모두 호출하여 반환된 객체를 스프링 컨테이너에 등록한다.
public class AppConfig {

    //리펙토링 CNT+ART+M (1)(2)
    @Bean //@Bean이 붙은 메서드의 명을 스프링 빈의 이름으로 사용한다.
    public MemberRepository memberRepository() {//(1)
        return new MemoryMemberRepository(); //이 코드만 바꾸면 된다.
    }

    @Bean
    public DiscountPolicy discountPolicy(){ //(2)
        //return new FixDiscountPolicy(); //(2)이 코드만 바꾸면 된다.
        return new RateDiscountPolicy(); //(2)이 코드만 바꾸면 된다.
    }


    @Bean
    public MemberService memberService123(){
        return new MemberServiceImpl(memberRepository());
    }



    @Bean
    public OrderService orderService(){
        return new OrderServiceImpl(memberRepository(),discountPolicy());
    }


}
