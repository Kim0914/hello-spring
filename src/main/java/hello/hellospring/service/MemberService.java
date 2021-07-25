package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// @Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    // @Autowired
    // 1. 생성자로 정의 2. setter 3. 필드 주입 => DI를 설정하는 세가지 방법
    // 생성자로 정의하는 것이 atomic 하게 즉, 생성되는 순간에 설정하며 다시 접근할 수 없도록 한다.
    public MemberService(MemberRepository memberRepository) { // 멤버 서비스는 멤버 리포지토리가 필요하기 때문에, 컨테이너에 등록하면서 스프링 컨테이너에 멤버 리포지토리를 저장
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Long join(Member member){
        // 같은 이름이 있는 중복 회원 X
        validateDuplicateMember(member); // 중복회원 검증, 신기한 방법 : 코드를 작성하고 ctrl + alt + shift + t 누르면 함수로 뺄 수 있다
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        Optional<Member> result = memberRepository.findByName(member.getName()); // ctrl + alt + v 하면 자동으로 리턴 객체 생성가능
        result.ifPresent(member1 -> { // Optional안에 객체가 존재하고, 객체가 none이 아닌경우에 예외처리
            throw new IllegalStateException("이미 존재하는 회원입니다");
        });
    }

    /**
     * 전체회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

}
