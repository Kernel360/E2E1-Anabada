package kr.kernel360.anabada.tradeoffer;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import kr.kernel360.anabada.global.commons.entity.BaseEntity;
import kr.kernel360.anabada.member.Member;
import kr.kernel360.anabada.trade.Trade;
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

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Column(nullable = false)
	private Member member;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Column(nullable = false)
	private Trade trade;

	@Column(nullable = false, name = "title", columnDefinition = "varchar(40)")
	private String title;

	@Column(name = "content", columnDefinition = "text")
	private String content;

	@Column(name = "image_path", columnDefinition = "varchar(40)")
	private String imagePath;

	@Column(nullable = false, name = "trade_offer_status", columnDefinition = "tinyint")
	private Boolean tradeOfferStatus;
}
