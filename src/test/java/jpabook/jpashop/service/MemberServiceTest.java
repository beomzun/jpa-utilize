package jpabook.jpashop.service;

import static org.junit.jupiter.api.Assertions.*;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional  //해당 어노테이션 사용하지 않으면 에러 발생 - EntityManager를 통한 모든 데이터 변경은 항상 transaction 안에서 이루어져야 함
                //해당 어노테이션을 테스트에서 사용 시 테스트 종료 시 db rollback
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("bzun");

        //when
        Long savedId = memberService.join(member);

        //then
        assertEquals(member, memberRepository.findOne(savedId));
    }

    @Test()
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("bzun");

        Member member2 = new Member();
        member2.setName("bzun");
        //when
        memberService.join(member1);
        //터진 예외를 저장하여 then의 assertEquals로 검증
        Exception e = Assertions.assertThrows(IllegalStateException.class, () ->
                memberService.join(member2)
        );

        //then
        assertEquals("이미 존재하는 회원입니다", e.getMessage());
//        fail("예외가 발생해야 합니다.");
    }
}