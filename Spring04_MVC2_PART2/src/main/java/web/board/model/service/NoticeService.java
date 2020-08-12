package web.board.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import common.exception.FileException;
import web.board.model.vo.Notice;

@Service
public interface NoticeService {
	
	public int insertNotice(Notice notice, List<MultipartFile> files, String root) throws FileException;
	
	public Map<String, Object> selectNoticeList(int currentPage, int cntPerPage);
	
	// 게시글 상세
	public Map<String, Object> selectNoticeDatail(int nIdx);

	// 게시글 첨부파일 삭제
	public int deleteFile(int fIdx);

	// 게시글 수정
	public int modifyNotice(Notice notice, List<MultipartFile> files, String root) throws FileException;

	// 게시글 삭제
	public int deleteNotice(int nIdx);

}















