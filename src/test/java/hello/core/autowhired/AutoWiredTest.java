package hello.core.autowhired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;

import java.util.Optional;

//옵션처리!!
public class AutoWiredTest {

    @Test
    void AutowiredOption() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    @Configuration
    static class TestBean {

        @Autowired(required = false) //의존관계가 없으면 메소드 자체가 호출이 안된다.
        public void setNoBean1(Member noBean1){ //스프링 컨테이너에서 관리하는게 아무것도 없을때
            System.out.println("noBean1 + "+noBean1);
        }

        @Autowired
        public void setNoBean2(@Nullable Member noBean1){ //스프링 컨테이너에서 관리하는게 아무것도 없을때
            System.out.println("noBean2 + "+noBean1);
        }

        @Autowired
        public void setNoBean3(Optional<Member> noBean3){ //스프링 컨테이너에서 관리하는게 아무것도 없을때
            System.out.println("noBean3 + "+noBean3);
        }



    }
}
