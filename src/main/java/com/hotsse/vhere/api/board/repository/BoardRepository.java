package com.hotsse.vhere.api.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hotsse.vhere.api.board.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {

	@Query(value = "SELECT b FROM Board b WHERE (b.latitude BETWEEN :swLat AND :neLat) AND (b.longitude BETWEEN :swLng AND :neLng) AND b.useYn = 'Y'")
	List<Board> findAllByCoordinate(double swLat, double swLng, double neLat, double neLng);
}
