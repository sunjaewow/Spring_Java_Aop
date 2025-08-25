package hello.aop.proxyvs;

import hello.aop.member.MemberService;
import hello.aop.member.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.aop.framework.ProxyFactory;

import static org.assertj.core.api.Assertions.*;

@Slf4j
public class ProxyTestingTest {

    @Test
    void jdkProxy() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(false);//false면 jdk, true 면 CGLIB

        MemberService memberServiceProxy = (MemberService) proxyFactory.getProxy();

        //JDK 동적 프록시를 구현 클래스로 캐스팅 시도 실패, ClassCastException 예외 발생
        assertThatThrownBy(() -> {
                    MemberServiceImpl memberServiceImplProxy = (MemberServiceImpl) memberServiceProxy;
                })
                .isInstanceOf(ClassCastException.class);


//        MemberServiceImpl memberServiceImplProxy = (MemberServiceImpl) memberServiceProxy;//에러 남 jdk는 인터페이스만 프록시 가능.
    }

    @Test
    void cglibProxy() {
        MemberServiceImpl target = new MemberServiceImpl();
        ProxyFactory proxyFactory = new ProxyFactory(target);
        proxyFactory.setProxyTargetClass(true);//false면 jdk, true 면 CGLIB

        MemberService memberServiceProxy = (MemberService) proxyFactory.getProxy();

        //CGLIB 프록시를 구현 클래스로 캐스팅 시도 성공
         MemberServiceImpl memberServiceImplProxy = (MemberServiceImpl) memberServiceProxy;


//        MemberServiceImpl memberServiceImplProxy = (MemberServiceImpl) memberServiceProxy;//에러 남 jdk는 인터페이스만 프록시 가능.
    }
}
