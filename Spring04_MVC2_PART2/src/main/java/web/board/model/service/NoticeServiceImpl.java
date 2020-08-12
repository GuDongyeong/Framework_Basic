package web.board.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import common.exception.FileException;
import common.util.FileUtil;
import common.util.Paging;
import web.board.model.dao.NoticeDao;
import web.board.model.vo.Notice;

@Service
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired
	private NoticeDao noticeDao;
	
//	@Transactional
	public int insertNotice(Notice notice, List<MultipartFile> files, String root) throws FileException {
		
		int result = noticeDao.insertNotice(notice);
		
		// 에러 발생을 위한 코드
//		int errorNumber = 10/0;
		
		if( !(files.size() == 1 && files.get(0).getOriginalFilename().equals(""))) {
			
			// 파일 업로드를 위해 FileUtil.fileUpload() 호출
			List<Map<String, Object>> fileData = new FileUtil().fileUpload(files, root);
			
			for(Map<String, Object> map : fileData) {
				noticeDao.insertFile(map);
			}
		}
		
		return result;
	}
	
	public Map<String, Object> selectNoticeList(int currentPage, int cntPerPage){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 페이지 객체 생성
		Paging paging = new Paging(noticeDao.selectContentCnt(), currentPage, cntPerPage);
		
		// 현재 페이지 게시글 목록
		List<Notice> nlist = noticeDao.selectNoticeList(paging);
		
//		System.out.println(nlist);
		
		map.put("nlist", nlist);
		map.put("paging", paging);
		
		return map;
	}
	
	// 게시글 상세
	public Map<String, Object> selectNoticeDatail(int nIdx){
		
		Map<String, Object> map = new HashMap<>();
		
		Notice notice = noticeDao.selectNoticeDetail(nIdx);
		
		List<Map<String, Object>> flist = noticeDao.selectFileWithNotice(nIdx);
		
		map.put("notice", notice);
		map.put("flist", flist);
		
		return map;
	}

	// 게시글 첨부파일 삭제
	public int deleteFile(int fIdx) {
		
		// 첨부파일 정보
		Map<String, Object> map = noticeDao.selectFileByfIdx(fIdx);

		String savepath = (String)map.get("savePath");
		
		new FileUtil().deleteFile(savepath);
		
		int res = noticeDao.deleteFile(fIdx);
		
		return res;
	}

	// 게시글 수정
	public int modifyNotice(Notice notice, List<MultipartFile> files, String root) throws FileException {
		
		int result = noticeDao.updateNotice(notice);
		
		int nIdx = notice.getnIdx();
		
		if( files.size() != 1 && !files.get(0).getOriginalFilename().equals("")){
			
			List<Map<String, Object>> list = new FileUtil().fileUpload(files, root);
			
			for(Map<String, Object> map : list) {
				map.put("nIdx", nIdx);
				noticeDao.insertFile2(map);
			}
		}
		
		return result;
		
	}

	// 게시글 삭제
	public int deleteNotice(int nIdx) {
		
		List<Map<String, Object>> fileData = noticeDao.selectFileWithNotice(nIdx);
		
		for( Map<String, Object> map : fileData) {
			new FileUtil().deleteFile((String)map.get("savePath"));
		}
		
		noticeDao.deleteFile2(nIdx);
		
		noticeDao.deleteNotice(nIdx);
		
		
		return 0;
	}

}















