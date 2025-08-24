package hello.aop.internalcall;

import hello.aop.internalcall.aop.CallLogAspect;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@Import(CallLogAspect.class)
@SpringBootTest
class CallServiceV0Test {

    @Autowired
    CallServiceV0 callServiceV0;//이건 프록시 객체가 스프링 컨테이너에 올라가는 것

    @Test
    void external() {
        callServiceV0.external();
        /**
         *  aop=void hello.aop.internalcall.CallServiceV0.external()
         *  call external
         *  call internal
         *  internal에 프록시가 안결렸음.
         *  aop=void hello.aop.internalcall.CallServiceV0.internal()이 나와야하는데 안나옴.
         *      @Before("execution(* hello.aop.internalcall..*.*(..))") 이렇게 했는데도 안나옴 문제
         */
    }

    @Test
    void internal() {
        callServiceV0.internal();
    }
}