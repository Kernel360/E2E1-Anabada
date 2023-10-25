package kr.kernel360.anabada.domain.member.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import kr.kernel360.anabada.domain.trade.entity.Trade;
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

	@OneToMany(mappedBy = "member")
	private List<Trade> trades = new ArrayList<>();
}
