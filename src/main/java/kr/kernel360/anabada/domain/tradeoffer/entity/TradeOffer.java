package kr.kernel360.anabada.domain.tradeoffer.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SQLDelete;

import kr.kernel360.anabada.domain.member.entity.Member;
import kr.kernel360.anabada.domain.trade.entity.Trade;
import kr.kernel360.anabada.global.commons.domain.DeletedStatus;
import kr.kernel360.anabada.global.commons.domain.TradeOfferStatus;
import kr.kernel360.anabada.global.commons.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@DynamicUpdate
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SQLDelete(sql = "UPDATE trade_offer SET deleted_status = 1 WHERE id  = ?")
@Table(name = "trade_offer")
public class TradeOffer extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, name = "title", columnDefinition = "varchar(40)")
	private String title;

	@Column(name = "content", columnDefinition = "text")
	private String content;

	@Column(name = "image_path", columnDefinition = "varchar(255)")
	private String imagePath;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, name = "trade_offer_status", columnDefinition = "varchar(20)")
	private TradeOfferStatus tradeOfferStatus;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, name = "deleted_status", columnDefinition = "varchar(20)")
	private DeletedStatus deletedStatus;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "created_by", columnDefinition = "bigint(50)")
	private Member member;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "trade_id", columnDefinition = "bigint(50)")
	private Trade trade;

	@Builder
	public TradeOffer(Long id, String title, String content, String imagePath, TradeOfferStatus tradeOfferStatus,
		DeletedStatus deletedStatus, Member member, Trade trade) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.imagePath = imagePath;
		this.tradeOfferStatus = tradeOfferStatus;
		this.deletedStatus = deletedStatus;
		this.member = member;
		this.trade = trade;
	}

	public void update(String title, String content, String imagePath) {
		this.title = title;
		this.content = content;
		this.imagePath = imagePath;
	}
  
	public void remove() {
		this.deletedStatus = DeletedStatus.TRUE;
	}
}
