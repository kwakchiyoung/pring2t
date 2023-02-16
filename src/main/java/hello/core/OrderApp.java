package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {

    public static void main(String[] args) {

        //MemberService memberService = new MemberServiceImpl(); //(1) 기존
        //OrderService orderService = new OrderServiceImpl();

        //(2)주입 의존성 주입으로 변경과정
        //AppConfig appConfig = new AppConfig();
        //MemberService memberService = appConfig.memberService123();
        //OrderService orderService = appConfig.orderService();

        //(3)주석 처리후 스프링컨테이너로 수정
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class); //주의!! 안에 어디서 주입해줬는지 꼭 넣어줄것
        MemberService memberService           = applicationContext.getBean("memberService123", MemberService.class); //메소드이름, 두번째는 반환타입
        OrderService orderService             = applicationContext.getBean("orderService"    , OrderService.class); //메소드이름, 두번째는 반환타입

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order = " +order);
        System.out.println("order.calculatePrice = " +order.calculatePrice());
    }
}
