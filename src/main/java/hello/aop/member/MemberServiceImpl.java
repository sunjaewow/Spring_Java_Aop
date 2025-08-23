package hello.aop.member;

import hello.aop.member.annotaion.ClassAop;
import hello.aop.member.annotaion.MethodAop;
import org.springframework.stereotype.Component;

@Component
@ClassAop
public class MemberServiceImpl implements MemberService{
    @MethodAop("test value")
    @Override
    public String hello(String param) {
        return "ok";
    }

    public String internal(String param) {
        return "internal";
    }

}
