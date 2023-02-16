package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름으로 조회")
    void findBeanByname() {
        MemberService memberService = ac.getBean("memberService123", MemberService.class);
        System.out.println("memberService = " + memberService);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("이름 없이 타입으로만 조회")
    void findBeanByType() {
        MemberService memberService = ac.getBean(MemberService.class); //타입만 넣음.
        System.out.println("memberService = " + memberService);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회")
    void findBeanByname2() {
        MemberService memberService = ac.getBean("memberService123", MemberServiceImpl.class); //꼭 인터페이스가 아니고 구체적인 구현체로 적어도 됨. 다만 역할과 구현중에는 역할로 의존하는게 좋음.
        System.out.println("memberService = " + memberService);
        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("빈 이름으로 조회X")
    void findByNameX(){
        //MemberService xxxx = ac.getBean("XXXX", MemberService.class);
        assertThrows(NoSuchBeanDefinitionException.class ,
                () -> ac.getBean("xxxxx", MemberService.class)); //오른쪽파람에있는걸 실행하면 왼쪽이 터져야한다.
    }
}
