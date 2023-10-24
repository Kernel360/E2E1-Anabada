package kr.kernel360.anabada.trade;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import kr.kernel360.anabada.category.Category;
import kr.kernel360.anabada.comment.Comment;
import kr.kernel360.anabada.global.commons.entity.BaseEntity;
import kr.kernel360.anabada.member.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "trade")
public class Trade extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, name = "title", columnDefinition = "varchar(40)")
	private String title;

	@Column(nullable = false, name = "content", columnDefinition = "text")
	private String content;

	@Column(nullable = false, name = "trade_type", columnDefinition = "varchar(40)")
	private String tradeType;

	@Column(name = "image_path", columnDefinition = "varchar(40)")
	private String imagePath;

	@Column(nullable = false, name = "trade_status", columnDefinition = "tinyint")
	private boolean tradeStatus;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Column(nullable = false)
	private Member member;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Column(nullable = false)
	private Category category;

	@OneToMany(mappedBy = "trade")
	private List<Comment> comments;
}
