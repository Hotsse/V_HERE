package com.hotsse.vhere.api.board.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.hotsse.vhere.api.board.vo.BoardVO;
import com.hotsse.vhere.core.base.BaseDao;

@Repository
public class BoardDao extends BaseDao {

    /**
     * 게시글 리스트 조회
     *
     * @param swLat 남서경도
     * @param swLng 남서위도
     * @param neLat 북동경도
     * @param neLng 북동위도
     * @return {@link List} 게시글 리스트
     * @throws Exception
     */
    public List<BoardVO> getBoards(double swLat, double swLng, double neLat, double neLng) throws Exception {
		Map<String, Object> param = Map.of(
				"swLat", swLat
				, "swLng", swLng
				, "neLat", neLat
				, "neLng", neLng
		);

        return this.sqlSession.selectList("board.getBoards", param);
    }

    /**
     * 게시글 조회
     *
     * @param boardId 게시글 고유번호
     * @return {@link BoardVO} 게시글
     * @throws Exception
     */
    public BoardVO getBoard(int boardId) throws Exception {
        Map<String, Object> param = Map.of(
				"boardId", boardId
		);

        return this.sqlSession.selectOne("board.getBoard", param);
    }

    /**
     * 게시글 생성
     *
     * @param board 게시글
     * @return 생성된 게시글 고유번호
     * @throws Exception
     */
    public int insertBoard(BoardVO board) throws Exception {
        this.sqlSession.insert("board.insertBoard", board);
        return board.getBoardId();
    }

    /**
     * 게시글 수정
     *
     * @param board 게시글
     * @return
     * @throws Exception
     */
    public int updateBoard(BoardVO board) throws Exception {
        return this.sqlSession.update("board.updateBoard", board);
    }

    /**
     * 게시글 삭제
     *
     * @param boardId 게시글 고유번호
     * @return
     * @throws Exception
     */
    public int deleteBoard(int boardId) throws Exception {
        Map<String, Object> param = Map.of(
				"boardId", boardId
		);

        return this.sqlSession.update("board.deleteBoard", param);
    }
}
