package kr.kernel360.anabada.domain.trade.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import kr.kernel360.anabada.domain.category.entity.Category;
import kr.kernel360.anabada.domain.comment.entity.Comment;
import kr.kernel360.anabada.domain.member.entity.Member;
import kr.kernel360.anabada.global.commons.domain.DeletedStatus;
import kr.kernel360.anabada.global.commons.domain.TradeStatus;
import kr.kernel360.anabada.global.commons.domain.TradeType;
import kr.kernel360.anabada.global.commons.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
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

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, name = "trade_type", columnDefinition = "varchar(40)")
	private TradeType tradeType;

	@Column(name = "image_path", columnDefinition = "varchar(40)")
	private String imagePath;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, name = "trade_status", columnDefinition = "varchar(20)")
	private TradeStatus tradeStatus;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, name = "deleted_status", columnDefinition = "varchar(20)")
	private DeletedStatus deletedStatus;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "created_by", columnDefinition = "bigint(50)")
	private Member member;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_id", columnDefinition = "bigint(50)")
	private Category category;

	@OneToMany(mappedBy = "trade")
	private List<Comment> comments = new ArrayList<>();

	@Builder
	public Trade(String title, String content, TradeType tradeType, String imagePath,
		DeletedStatus deletedStatus, TradeStatus tradeStatus, Member member, Category category) {
		this.title = title;
		this.content = content;
		this.tradeType = tradeType;
		this.imagePath = imagePath;
		this.deletedStatus = deletedStatus;
		this.tradeStatus = tradeStatus;
		this.category = category;
		this.member = member;
	}
}
