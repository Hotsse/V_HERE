package com.hotsse.vhere.api.image.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotsse.vhere.api.image.vo.ImageVO;
import com.hotsse.vhere.core.base.BaseDao;

@Repository
public class ImageDao extends BaseDao {

	public List<Integer> getImageIds(int boardId) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("boardId", boardId);
		
		return sqlSession.selectList("image.getImageIds", param);
	}
	
	public List<ImageVO> getImages(int boardId) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("boardId", boardId);
		
		return sqlSession.selectList("image.getImages", param);
	}
	
	public ImageVO getImage(int imgId) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("imgId", imgId);
		
		return sqlSession.selectOne("image.getImage", param);
	}
	
	public int insertImage(ImageVO img) throws Exception {
		
		this.sqlSession.insert("image.insertImage", img);
		return img.getImgId();
	}
	
	public int deleteImage(int imgId) throws Exception {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("imgId", imgId);
		
		return sqlSession.update("image.deleteImage", param);
	}
}
