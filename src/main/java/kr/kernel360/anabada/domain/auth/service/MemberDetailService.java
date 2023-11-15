package kr.kernel360.anabada.domain.auth.service;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kr.kernel360.anabada.domain.member.entity.Member;
import kr.kernel360.anabada.domain.member.repository.MemberRepository;
import kr.kernel360.anabada.global.commons.domain.PrincipalDetails;
import kr.kernel360.anabada.global.error.code.MemberErrorCode;
import kr.kernel360.anabada.global.error.exception.BusinessException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberDetailService implements UserDetailsService {
	private final MemberRepository memberRepository;

	@Override
	public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
		return memberRepository.findByEmail(username)
			.map(member -> createUser(username, member))
			.orElseThrow(() -> new BusinessException(MemberErrorCode.NOT_FOUND_MEMBER));
	}

	public UserDetails createUser(String username, Member member) {
		isDeactivate(member);
		PrincipalDetails principalDetails = new PrincipalDetails(member);
		List<SimpleGrantedAuthority> grantedAuthorities = (List<SimpleGrantedAuthority>)principalDetails.getAuthorities();

		return new User(username, member.getPassword(), grantedAuthorities);
	}

	private static void isDeactivate(Member member) {
		if (!member.getAccountStatus()) {
			throw new BusinessException(MemberErrorCode.DEACTIVATE_MEMBER);
		}
	}

}
