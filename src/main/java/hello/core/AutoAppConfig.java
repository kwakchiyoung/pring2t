package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

//컴포넌트스캔

@Configuration
@ComponentScan(

        //basePackages = "hello.core.member", //이렇게 하면 hello.core.member하위 컴포넌트 스캔 대상이 된다.
        //우리가 수동으로 등록한 AppConfig안에 Configuration은 제외헤야해서. @Configuration안에 @ComponentScan이 포함되어있음.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION , classes = Configuration.class)

        //그렇다면 defualt값은 무엇일까? @ComponentScan이 붙은 클레스의 패키지인 hello.core하위 패키지 전부를 찾는다.
        //가장 권장하는 방법은 최상단에 두는것.

        //중요!!Spring boot를 사용하면 굳이 @ComponentScan를 할필요가 없다. 왜냐하면 메인실행부분에 @SpringBootApplication에 @ComponentScan이 포함되어있다.
)
public class AutoAppConfig {
}
