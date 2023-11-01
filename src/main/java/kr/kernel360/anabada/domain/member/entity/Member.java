package kr.kernel360.anabada.domain.member.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;

import kr.kernel360.anabada.global.commons.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@DynamicUpdate
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE member SET accountStatus = 0 WHERE id  = ?")
@Table(name = "member")
public class Member extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true, name ="email", columnDefinition = "varchar(50)")
	private String email;

	@Column(nullable = false, unique = true, name = "nickname", columnDefinition = "varchar(40)")
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

	@Builder
	public Member(Long id, String email, String nickname, String password, String authority, String gender,
		String birth,
		String socialProvider, Boolean accountStatus) {
		this.id = id;
		this.email = email;
		this.nickname = nickname;
		this.password = password;
		this.authority = authority;
		this.gender = gender;
		this.birth = birth;
		this.socialProvider = socialProvider;
		this.accountStatus = accountStatus;
	}

	public Member(String email, String nickname, String password, String gender, String birth) {
		this.email = email;
		this.nickname = nickname;
		this.password = password;
		this.gender = gender;
		this.birth = birth;
	}

	public void update(String email, String nickname, String password, String gender, String birth) {
		this.email = email;
		this.nickname = nickname;
		this.password = password;
		this.gender = gender;
		this.birth = birth;
	}

	public void remove() {
		this.accountStatus = false;
	}
}
