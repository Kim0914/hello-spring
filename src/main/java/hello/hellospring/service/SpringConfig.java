package hello.hellospring.service;

import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 자바코드로 직접 스프링 빈 (의존관계 DI) 등록하기 ( autowired, service, controller 와 같은 것들)
@Configuration
public class SpringConfig {

    //private DataSource dataSource;
    //private EntityManager em;

    /*@Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }*/

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }

    /*@Bean
    public MemberRepository memberRepository(){
        //return new MemoryMemberRepository();
        //return new JdbcMemberRepository(dataSource);
        //return new JdbcTemplateMemberRepository(dataSource);
        //return new JpaMemberRepository(em);
    }*/
}
