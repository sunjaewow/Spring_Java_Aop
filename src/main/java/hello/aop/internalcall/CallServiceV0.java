package hello.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV0 {

    public void external() {
        log.info("call external");
        internal();//내부 메서드 호출 this.internal() 메서드를 호출할 때 대상을 지정하지 않으면 this가 붙게됨
    }

    public void internal() {
        log.info("call internal");
    }
}
