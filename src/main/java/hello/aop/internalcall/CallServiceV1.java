package hello.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV1 {

    private CallServiceV1 callServiceV1;

    /**
     * 📌 동작 과정
     * 스프링이 OrderService 객체를 new 로 먼저 생성 (기본 생성자 호출)
     * 빈 생성이 끝나면, @Autowired 붙은 setter 메서드를 실행
     * MemberService 빈을 찾아서 setMemberService() 호출 → 의존성 주입 완료
     * 👉 생성자 주입은 "객체 만들면서 바로 주입"이고,
     * 👉 수정자 주입은 "객체 먼저 만든 뒤, 나중에 세팅" 차이가 있어요.
     */
    @Autowired
    public void setCallServiceV1(CallServiceV1 callServiceV1) {
        log.info("callServiceV1 = {}", callServiceV1.getClass());// 스프링이 빈 생성이 끝나면 빈을 찾아서 주입 그 빈이 프록시이므로 프록시 객체를 반환
        this.callServiceV1 = callServiceV1;
    }

    /**
     *
     * 자기 자신을 의존관계를 주입 반음 ->순환 사이클 발생
     *내가 생성이 되지않았는데 어떻게 주입을 받음 닭이 먼저냐 달걀이 먼저냐..
     *수정자 주입으로하면됨.
     * 스프링은 생성으로 주입하는 단계와 수정자로 주입하는 단계가 나눠져 있음 그래서 먼저 생성한 뒤 수정자로 주입받으면 됨.
     */
//    @Autowired
//    public CallServiceV1(CallServiceV1 callServiceV1) {
//        this.callServiceV1 = callServiceV1;
//    }

    public void external() {
        log.info("call external");
        callServiceV1.internal();//외부 호출로 해야됨. cllServiceV1은 프록시 객체
    }

    public void internal() {
        log.info("call internal");
    }
}
