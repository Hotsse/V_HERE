package com.hotsse.vhere.api.image.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotsse.vhere.api.image.vo.ImageVO;
import com.hotsse.vhere.core.base.BaseDao;

@Repository
public class ImageDao extends BaseDao {

	/**
	 * 이미지번호 리스트 조회
	 * 
	 * @param boardId 게시글 고유번호
	 * @return {@link List} 이미지번호 리스트
	 * @throws Exception
	 */
	public List<Integer> getImageIds(int boardId) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("boardId", boardId);
		
		return this.sqlSession.selectList("image.getImageIds", param);
	}
	
	/**
	 * 이미지 리스트 조회
	 * 
	 * @param boardId 게시글 고유번호
	 * @return {@link List} 이미지 리스트
	 * @throws Exception
	 */
	public List<ImageVO> getImages(int boardId) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("boardId", boardId);
		
		return this.sqlSession.selectList("image.getImages", param);
	}
	
	/**
	 * 이미지 조회
	 * @param imgId 이미지번호
	 * @return {@link ImageVO} 이미지
	 * @throws Exception
	 */
	public ImageVO getImage(int imgId) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("imgId", imgId);
		
		return this.sqlSession.selectOne("image.getImage", param);
	}
	
	/**
	 * 이미지 생성
	 * 
	 * @param img 이미지
	 * @return {@link Integer} 이미지번호
	 * @throws Exception
	 */
	public int insertImage(ImageVO img) throws Exception {
		
		this.sqlSession.insert("image.insertImage", img);
		return img.getImgId();
	}
	
	/**
	 * 이미지 삭제
	 * @param imgId 이미지번호
	 * @return
	 * @throws Exception
	 */
	public int deleteImage(int imgId) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("imgId", imgId);
		
		return this.sqlSession.update("image.deleteImage", param);
	}
}
