package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

// @Repository
public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence); // name은 넘어온 상태, store에 넣기전 멤버의 id값 세팅
        store.put(member.getId(), member); // store에 저장, 즉 map에 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // null이 반환되더라도 감싸서 반환할 수 있다
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny(); // map에서 쭉 돌면서 찾는다. 찾아지면 Optional로 반환, 값이 없는 경우 Optional에 null 포함해서 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); // values는 store에 있는 멤버들이다.
    }

    public void clearStore(){
        store.clear();
    }
}
