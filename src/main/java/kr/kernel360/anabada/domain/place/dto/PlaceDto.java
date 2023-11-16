package kr.kernel360.anabada.domain.place.dto;


import io.swagger.annotations.ApiModelProperty;
import kr.kernel360.anabada.domain.place.entity.Place;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlaceDto {
	@ApiModelProperty(value = "특별시, 도", example = "서울특별시")
	private String state;

	@ApiModelProperty(value = "구", example = "강남구")
	private String city;

	@ApiModelProperty(value = "동", example = "삼성동")
	private String address1;

	public static Place toEntity(PlaceDto placeDto) {
		return Place.builder()
			.state(placeDto.state)
			.city(placeDto.city)
			.address1(placeDto.address1)
			.build();
	}

	public static PlaceDto of(PlaceResponse placeResponse) {
		return PlaceDto.builder()
			.state(placeResponse.getRegion1depthName())
			.city(placeResponse.getRegion2depthName())
			.address1(placeResponse.getRegion3depthName())
			.build();
	}
}
