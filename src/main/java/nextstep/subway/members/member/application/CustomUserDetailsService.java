package nextstep.subway.members.member.application;

import nextstep.subway.auths.application.provider.UserDetails;
import nextstep.subway.auths.application.provider.UserDetailsService;
import nextstep.subway.members.member.domain.LoginMember;
import nextstep.subway.members.member.domain.Member;
import nextstep.subway.members.member.domain.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private MemberRepository memberRepository;

    public CustomUserDetailsService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(RuntimeException::new);
        return LoginMember.of(member);
    }
}