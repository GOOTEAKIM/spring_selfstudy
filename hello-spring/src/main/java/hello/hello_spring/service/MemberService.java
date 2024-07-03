package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MemberService {


    // ctrl + shift + T : 테스트 자동으로 생성
    private final MemberRepository memberRepository;

    // alt + insert -> constructor 생성
    // 회원 가입
    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long join(Member member) {

        validateDuplicateMember(member);
        // ctrl + alt + shift + t -> extract method
        // ctrl + alt + l : 코드 정리
        memberRepository.save(member);
        return member.getId();

    }

    private void validateDuplicateMember(Member member) {


        memberRepository.findByName(member.getName())
                // optional 을 바로 반환하면 안좋다.
                // ctrl + alt + v
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원임");
                });
    }


    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
