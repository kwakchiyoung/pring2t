package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy     = new FixDiscountPolicy(); //고정할인정책
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);            //(1)회원정보조회
        int discountPrice = discountPolicy.discount(member, itemPrice); //(2)할인정책에 회원을 넘겨 할인금을 받고

        return new Order(memberId , itemName,itemPrice,discountPrice);  //(3)최종생성된 주문을 반환시킨다.
    }
}
