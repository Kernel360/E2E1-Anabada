package kr.kernel360.anabada.domain.trade.service;

import java.util.List;

import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.kernel360.anabada.domain.trade.dto.TradeResponse;
import kr.kernel360.anabada.domain.trade.entity.Trade;
import kr.kernel360.anabada.domain.trade.repository.TradeRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TradeService {
	private final TradeRepository tradeRepository;

	public TradeResponse findTrades() {
		List<Trade> trades = tradeRepository.findAll();
		return TradeResponse.of(trades);
	}

}
