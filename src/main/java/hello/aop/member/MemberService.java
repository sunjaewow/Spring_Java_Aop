package hello.aop.member;

import org.springframework.stereotype.Component;

@Component
public interface MemberService {
    String hello(String param);
}
