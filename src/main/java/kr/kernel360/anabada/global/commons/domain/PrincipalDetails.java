package kr.kernel360.anabada.global.commons.domain;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import kr.kernel360.anabada.domain.member.entity.Member;

public class PrincipalDetails implements UserDetails, OAuth2User {

	private Member member;
	private Map<String, Object> attributes;

	public PrincipalDetails(Member member) {
		this.member = member;
	}

	public PrincipalDetails(Member member, Map<String, Object> attributes) {
		this.member = member;
		this.attributes = attributes;
	}

	public Member getMember() {
		return member;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public <A> A getAttribute(String name) {
		return OAuth2User.super.getAttribute(name);
	}

	@Override
	public Map<String, Object> getAttributes() {
		return attributes;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return toList(member.getAuthorities())
			.stream()
			.map(authority -> new SimpleGrantedAuthority(authority))
			.collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return member.getPassword();
	}

	@Override
	public String getUsername() {
		return member.getNickname();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public List<String> toList(String str) {
		return Arrays.stream(str.split(", "))
			.map(String::trim)
			.toList();
	}
}
