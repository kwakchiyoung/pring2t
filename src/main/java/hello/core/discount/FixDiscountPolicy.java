package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("fixDiscountPolicy") //@Autowired 타입매칭이 두개일경우 @Qualifier를 사용하여 새로운 별칭자를 정해준다.
//@Primary이걸쓰면 이걸 우선적으로 쓴다고 정의
public class FixDiscountPolicy implements DiscountPolicy{

    private int discountFixAmout = 1000;
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return discountFixAmout;
        }else{
            return 0;
        }

    }
}
