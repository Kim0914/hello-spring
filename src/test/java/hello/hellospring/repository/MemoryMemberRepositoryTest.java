package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){ // 테스트 함수가 끝날때마다 실행하는 함수
        repository.clearStore();
    }

    // 테스트 함수들이 테스트 하는 동안 랜덤인 순서로 실행되는데, 동시성 문제가 생김 => 의존 관계가 없이 설정 되야함으로 @afterEach 함수를 이용해 데이터를 다 비움
   @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findByID(member.getId()).get();
        //System.out.println("result = " + (result == member)); // soutv 입력하면 자동으로 system.out.print..... 나옴(신기)
        //Assertions.assertEquals(member, result);
        assertThat(member).isEqualTo((result));
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findByAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
