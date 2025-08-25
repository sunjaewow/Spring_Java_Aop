package hello.aop.internalcall;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CallServiceV2 {

//    private final ApplicationContext applicationContext; //apllicationConext를 통해 모든걸 가져올필요가없음
//
//    public CallServiceV2(ApplicationContext applicationContext) {
//        this.applicationContext = applicationContext;
//    }

    /**
     *Spring에서 ObjectProvider<T> 는 조금 더 유연하게 빈을 주입할 수 있도록 도와주는 지연 의존성 조회(Dependency Lookup) 도구
     *프록시 빈을 부입받는거라 수정자가 아닌 생성자 주입.
     */
    private final ObjectProvider<CallServiceV2> callServiceProvider;

    public CallServiceV2(ObjectProvider<CallServiceV2> callServiceProvider) {
        this.callServiceProvider = callServiceProvider;
    }

    public void external() {
        log.info("call external");
//        CallServiceV2 bean = applicationContext.getBean(CallServiceV2.class);
        CallServiceV2 bean = callServiceProvider.getObject();
        bean.internal();
    }

    public void internal() {
        log.info("call internal");
    }
}
