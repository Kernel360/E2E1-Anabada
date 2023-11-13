package kr.kernel360.anabada.domain.faq.api;

import java.net.URI;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.kernel360.anabada.domain.faq.dto.CreateFaqRequest;
import kr.kernel360.anabada.domain.faq.dto.CreateFaqResponse;
import kr.kernel360.anabada.domain.faq.dto.FaqSearchCondition;
import kr.kernel360.anabada.domain.faq.dto.FindAllFaqResponse;
import kr.kernel360.anabada.domain.faq.dto.FindFaqResponse;
import kr.kernel360.anabada.domain.faq.dto.UpdateFaqRequest;
import kr.kernel360.anabada.domain.faq.dto.UpdateFaqResponse;
import kr.kernel360.anabada.domain.faq.service.FaqService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class FaqController {
	private final FaqService faqService;

	@PostMapping("/v1/faqs")
	public ResponseEntity<CreateFaqResponse> create(@RequestBody CreateFaqRequest createFaqRequest) {
		CreateFaqResponse createFaqResponse = faqService.create(createFaqRequest);
		URI url = URI.create("api/v1/faqs/" + createFaqResponse.getId());
		return ResponseEntity.created(url).body(createFaqResponse);

	}

	@GetMapping("/v1/faqs")
	public ResponseEntity<FindAllFaqResponse> findAll(FaqSearchCondition faqSearchCondition,
													  @RequestParam(value="pageNo", defaultValue="1") int pageNo) {
		Pageable pageable = PageRequest.of(pageNo<1 ? 0 : pageNo-1, 10);
		FindAllFaqResponse faqs = faqService.findAll(faqSearchCondition, pageable);
		return ResponseEntity.ok(faqs);
	}

	@GetMapping("/v1/faqs/{faqId}")
	public ResponseEntity<FindFaqResponse> find(@PathVariable Long faqId) {
		FindFaqResponse faq = faqService.find(faqId);
		return ResponseEntity.ok(faq);
	}

	@PutMapping("/v1/faqs")
	public ResponseEntity<UpdateFaqResponse> update(@RequestBody UpdateFaqRequest updateFaqRequest) {
		UpdateFaqResponse updateFaqResponse = faqService.update(updateFaqRequest);
		return ResponseEntity.ok(updateFaqResponse);
	}

	@DeleteMapping("/v1/faqs/{faqId}")
	public ResponseEntity remove(@PathVariable Long faqId) {
		faqService.delete(faqId);
		return ResponseEntity.ok().build();

	}

}
