package jpabook.jpashop;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional  //해당 어노테이션 사용하지 않으면 에러 발생 - EntityManager를 통한 모든 데이터 변경은 항상 transaction 안에서 이루어져야 함
                    //해당 어노테이션을 테스트에서 사용 시 테스트 종료 시 db rollback
    @Rollback(value = false)        //눈으로 보고 싶으면 해당 어노테이션 설정하여 커밋시키기
    public void testMember() throws Exception {
        //given
        Member member = new Member();
        member.setUsername("bzun");

        //when
        Long savedId = memberRepository.save(member);
        Member findMember = memberRepository.find(savedId);
        //then
        //Assertions.assertThat 으로 검증
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        Assertions.assertThat(findMember).isEqualTo(member);    //한 트랜잭션 내부에 있으므로 같은 영속성 컨텍스트에서 관리. 식별자가 같으므로 같은 Entity이다.
    }
}