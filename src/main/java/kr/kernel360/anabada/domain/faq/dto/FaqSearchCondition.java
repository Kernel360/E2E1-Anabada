package kr.kernel360.anabada.domain.faq.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FaqSearchCondition {
	@ApiModelProperty(value = "제목", example = "제가 쓴 글이 보이지 않아요.")
	private String title;

	@JsonProperty(value = "created_by")
	@ApiModelProperty(value = "작성자", example = "집에가고싶은관리자")
	private String createdBy;
}
