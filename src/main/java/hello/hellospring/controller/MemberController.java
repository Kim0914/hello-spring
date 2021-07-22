package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) { // 컨트롤러와 서비스 연결, 멤버 컨트롤러가 생성될 때 스프링 빈에 등록되어있는 멤버 서비스 객체를 넣어준다(DI)
        this.memberService = memberService;
    }
}
