package hello.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect//이거는 컴포넌트가 아님 스프링 빈으로 등록하려면 따로 해야됨.
public class AspectV1 {

    @Around("execution(* hello.aop.order..*(..))")//포인트 컷
    public Object doLog(ProceedingJoinPoint joinPoint) throws Throwable {//doLog는 어드바이스.
        log.info("[log] {}", joinPoint.getSignature()); // join point 시그니처(메서드의 정보가 다 출력)
        return joinPoint.proceed(); //이거 해야 실제 타겟이 호출이됨.
    }
}
