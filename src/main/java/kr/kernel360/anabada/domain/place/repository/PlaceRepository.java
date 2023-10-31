package kr.kernel360.anabada.domain.place.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.kernel360.anabada.domain.place.entity.Place;

public interface PlaceRepository extends JpaRepository<Place,Long> {
}
