package hello.aop.pointcut;

import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.*;

@Slf4j
public class ExecutionTest {

    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    Method method;

    @BeforeEach
    public void setUp() throws NoSuchMethodException {
        method = MemberServiceImpl.class.getMethod("hello", String.class);
    }

    @Test
    public void printMethod() {
        log.info("helloMethod={}", method);
    }

    @Test
    public void exactMethod() {
        pointcut.setExpression("execution(public String hello.aop.member.MemberServiceImpl.hello(String))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    @Test void allMatch() {
        pointcut.setExpression("execution(* *(..))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    @Test void nameMatch() {
        pointcut.setExpression("execution(* hello(..))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    @Test void nameMatchPattern() {
        pointcut.setExpression("execution(* hel*(..))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    @Test void nameMatchPattern2() {
        pointcut.setExpression("execution(* *el*(..))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    @Test void nameMatchFalse() {
        pointcut.setExpression("execution(* nono(..))");
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isFalse() ;
    }

    @Test void nameMatchPattern3() {
        pointcut.setExpression("execution(* hello.aop..*(..))");//..하위 모듈까지 보는거임.
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    @Test void typeMatch() {
        pointcut.setExpression("execution(* hello.aop.member.MemberService.*(..))");//superclass도 가능.
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void typeMisMatch() throws NoSuchMethodException {//superclass로 할거면 오버라이딩 받은 메서드만 가능임.
        pointcut.setExpression("execution(* hello.aop.member.MemberService.*(..))");
        Method internalMethod = MemberServiceImpl.class.getMethod("internal", String.class);
        assertThat(pointcut.matches(internalMethod, MemberServiceImpl.class)).isFalse();
    }

    @Test
    void typeMisMatch2() throws NoSuchMethodException {//superclass로 할거면 오버라이딩 받은 메서드만 가능임.
        pointcut.setExpression("execution(* hello.aop.member.MemberServiceImpl.*(..))");
        Method internalMethod = MemberServiceImpl.class.getMethod("internal", String.class);
        assertThat(pointcut.matches(internalMethod, MemberServiceImpl.class)).isTrue();
    }

    @Test
    void paramMatch() {
        pointcut.setExpression("execution(* *(*))");//파라미터 하나 *
        assertThat(pointcut.matches(method, MemberServiceImpl.class)).isTrue();
    }
}
