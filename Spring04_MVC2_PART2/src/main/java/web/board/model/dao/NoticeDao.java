package web.board.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import common.util.Paging;
import web.board.model.vo.Notice;

@Repository
public class NoticeDao {

	@Autowired
	private SqlSessionTemplate sqlSession;
	
	// 게시물 테이블에 게시물 추가
	public int insertNotice(Notice notice) {
		return sqlSession.insert("NOTICE.insertNotice", notice);
	}

	// 파일 테이블에 파일 정보 추가
	public int insertFile(Map<String, Object> fileInfo) {
		return sqlSession.insert("NOTICE.insertFile", fileInfo);
	}
	
	// 게시글 목록
	public List<Notice> selectNoticeList(Paging paging){
		return sqlSession.selectList("NOTICE.selectNoticeList", paging);
	}
	
	// 전체 게시글 수
	public int selectContentCnt() {
		return sqlSession.selectOne("NOTICE.selectContentCnt");
	}
	
	// 게시글 상세
	public Notice selectNoticeDetail(int nIdx) {
		return sqlSession.selectOne("NOTICE.selectNoticeDetail", nIdx);
	}
	
	// 게시글 파일 정보
	public List<Map<String, Object>> selectFileWithNotice(int nIdx){
		return sqlSession.selectList("NOTICE.selectFileWithNotice", nIdx);
	}

	// 첨부파일 삭제
	public int deleteFile(int fIdx) {
		return sqlSession.delete("NOTICE.deleteFile", fIdx);
	}

	// 첨부파일 정보
	public Map<String, Object> selectFileByfIdx(int fIdx) {
		return sqlSession.selectOne("NOTICE.selectFile", fIdx);
	}

	// 게시글 수정
	public int updateNotice(Notice notice) {
		return sqlSession.update("NOTICE.updateNotice", notice);
	}

	public int insertFile2(Map<String, Object> map) {
		return sqlSession.insert("NOTICE.insertFile2", map);
	}

	public int deleteFile2(int nIdx) {
		return sqlSession.insert("NOTICE.deleteFile2", nIdx);
	}

	public int deleteNotice(int nIdx) {
		return sqlSession.insert("NOTICE.deleteNotice", nIdx);
	}
}
