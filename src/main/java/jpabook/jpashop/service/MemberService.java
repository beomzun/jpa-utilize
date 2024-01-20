package jpabook.jpashop.service;

import java.util.List;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)     //읽기 전용임. 조회할때의 성능 최적화 면에서 좋음.
@RequiredArgsConstructor
public class MemberService {

    //1번 방법 -> 테스트할 때 변경 불가한 단점.
//    @Autowired
//    private MemberRepository memberRepository;

    //2번 방법 -> 추후 해당 메서드를 사용하지 않으므로 버그 위험도 증가
//    private MemberRepository memberRepository;
//
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    //3번 방법: 생성자 주입(생성자가 하나일 경우 Autowired 생략 가능), MemberRepository 변경할 일 없으므로 final 설정.
//    private final MemberRepository memberRepository;
//
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    //4번 방법 lombok @RequiredArgsConstructor
    private final MemberRepository memberRepository;

    @Transactional      //readOnly보다 우선권을 가짐. 설정 안할 시 클래스 전체에 적용된 readOnly로 인해 데이터 변경이 불가함
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    //이렇게 설정해도 정말 동시에 접근할 수 있기 때문에(여러 개의 WAS, 멀티스레드 등) Name을 Unique로 설정
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다");
        }
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findById(memberId).get();
    }

    @Transactional
    public void update(Long id, String name) {
        Member member = memberRepository.findById(id).get();
        member.setName(name);
    }
}
