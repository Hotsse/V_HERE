package com.hotsse.vhere.api.image.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hotsse.vhere.api.image.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Integer> {

	@Query(value = "SELECT i FROM Image i WHERE i.boardId = :boardId AND i.useYn = 'Y'")
	List<Image> findAvailableByBoardId(int boardId);
}
