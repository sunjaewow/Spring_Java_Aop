package hello.aop.member.annotaion;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)//실행 시점에 애노테이션이 살아잇기를 위한. 동적으로 이 애노테이션을 읽을 수 있음.
public @interface MethodAop {
    String value();

}
