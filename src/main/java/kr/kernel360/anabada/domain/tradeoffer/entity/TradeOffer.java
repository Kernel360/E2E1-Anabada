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

import kr.kernel360.anabada.global.commons.domain.DeletedStatus;
import kr.kernel360.anabada.global.commons.entity.BaseEntity;
import kr.kernel360.anabada.domain.member.entity.Member;
import kr.kernel360.anabada.domain.trade.entity.Trade;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "trade_offer")
public class TradeOffer extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, name = "title", columnDefinition = "varchar(40)")
	private String title;

	@Column(name = "content", columnDefinition = "text")
	private String content;

	@Column(name = "image_path", columnDefinition = "varchar(40)")
	private String imagePath;

	@Column(nullable = false, name = "trade_offer_status", columnDefinition = "varchar(20)")
	private Boolean tradeOfferStatus;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, name = "deleted_status", columnDefinition = "varchar(20)")
	private DeletedStatus deletedStatus;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "created_by", columnDefinition = "bigint(50)")
	private Member member;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "trade_id", columnDefinition = "bigint(50)")
	private Trade trade;
}
