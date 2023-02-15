package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class OrderApp {

    public static void main(String[] args) {

        //MemberService memberService = new MemberServiceImpl(); //(1)의존성 주입으로 변경과정
        //OrderService orderService = new OrderServiceImpl();

        //(2)주입
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService123();
        OrderService orderService = appConfig.orderService();

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order = " +order);
        System.out.println("order.calculatePrice = " +order.calculatePrice());
    }
}
