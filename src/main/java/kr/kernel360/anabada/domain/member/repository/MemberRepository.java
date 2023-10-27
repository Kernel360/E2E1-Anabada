package kr.kernel360.anabada.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.kernel360.anabada.domain.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
