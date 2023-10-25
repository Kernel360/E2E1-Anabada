package kr.kernel360.anabada.domain.comment.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import kr.kernel360.anabada.domain.member.entity.Member;
import kr.kernel360.anabada.domain.trade.entity.Trade;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "comment")
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "created_by", columnDefinition = "bigint(50)")
	private Member member;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(nullable = false, name = "trade_id", columnDefinition = "bigint(50)")
	private Trade trade;
}
