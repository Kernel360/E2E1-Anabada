package kr.kernel360.anabada.domain.member.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import kr.kernel360.anabada.global.commons.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
public class Member extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, name ="email", columnDefinition = "varchar(50)")
	private String email;

	@Column(nullable = false, name = "nickname", columnDefinition = "varchar(40)")
	private String nickname;

	@Column(nullable = false, name = "password", columnDefinition = "varchar(255)")
	private String password;

	@Column(nullable = false, name = "authority", columnDefinition = "varchar(40)")
	private String authority;

	@Column(nullable = false, name = "gender", columnDefinition = "varchar(40)")
	private String gender;

	@Column(nullable = false, name = "birth", columnDefinition = "varchar(20)")
	private String birth;

	@Column(name = "social_provider", columnDefinition = "varchar(40)")
	private String socialProvider;

	@Column(nullable = false, name = "account_status", columnDefinition = "tinyint")
	private Boolean accountStatus;

	// 1. 현재 상태에서는 회원에서 교환을 join하여 교환테이블에서 데이터를 가져오는 기능은 없기 떄문에 해당 부분은 지워야하는 것으로 인지하였습니다.
	// 하지만 회원테이블을 기준으로 교환테이블과 join해 교환테이블의 데이터를 가져와야할 경우가 생긴다면 해당 부분이 추가될 텐데,
	// 이 부분을 추가했을 떄 순환참조가 일어나는 이유를 알고싶습니다.

	// 2. 이 부분을 주석 하더라도 직렬화를 하는 과정에서 에러가 나고 있는데 원인을 알고 싶습니다.
	// @OneToMany(mappedBy = "member")
	// private List<Trade> trades = new ArrayList<>();
}
