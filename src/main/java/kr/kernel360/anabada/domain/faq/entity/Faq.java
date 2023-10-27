package kr.kernel360.anabada.domain.faq.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import kr.kernel360.anabada.domain.member.entity.Member;
import kr.kernel360.anabada.global.commons.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DynamicUpdate
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "faq")
public class Faq extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, name = "title", columnDefinition = "varchar(50)")
	private String title;

	@Column(name = "content", columnDefinition = "text")
	private String content;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "created_by", columnDefinition = "bigint(50)")
	private Member member;

	@Builder
	public Faq(Long id, String title, String content, Member member) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.member = member;
	}

	public void update(String title, String content){
		this.title = title;
		this.content = content;
	}

	private Faq(String title, String content, Member member){
		this.title = title;
		this.content = content;
		this.member = member;
	}

	public static Faq of(String title, String content, Member member){
		return new Faq(title, content, member);
	}
}
