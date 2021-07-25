package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest { // 자동으로 Test를 만들고 싶으면, 해당하는 class에서 ctrl + shilt + t 누르면 된다.. 신기

    // 스프링 컨테이너에 등록되어있는 bean을 끌고온다.
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;


    // Test코드는 과감하게 한글로 써도 된다
    @Test
    void 회원가입() { // Test를 진행할 때 1. given 2. when 3. then 순서로 차근차근 진행해보기
        // given
        Member member = new Member();
        member.setName("hello0914");

        // when
        Long saveId = memberService.join(member);

        // then
        Member findMember = memberService.findOne(member.getId()).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
//        }


        // then

    }

}