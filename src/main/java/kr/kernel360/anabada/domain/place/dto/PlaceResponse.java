package kr.kernel360.anabada.domain.place.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PlaceResponse {
	private String regionType;

	private String addressName;

	@JsonAlias("region_1depth_name")
	private String region1depthName;

	@JsonAlias("region_2depth_name")
	private String region2depthName;

	@JsonAlias("region_3depth_name")
	private String region3depthName;

	@JsonAlias("region_4depth_name")
	private String region4depthName;

	private String code;

	private Double x;

	private Double y;
}
