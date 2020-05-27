package kr.com.yourHelper.Service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.com.yourHelper.Dao.ArticleRepository;
import kr.com.yourHelper.Dao.MemberRepository;
import kr.com.yourHelper.Domain.ArticleEntire;
import kr.com.yourHelper.Domain.ArticleList;
import kr.com.yourHelper.Dto.ArticleCreateDto;
import kr.com.yourHelper.Dto.ArticleUpdateDto;
import kr.com.yourHelper.QueryDto.ArticleQueryDto;
import kr.com.yourHelper.QueryDto.CategoryQueryDto;
import kr.com.yourHelper.QueryDto.MemberQueryDto;

@Service
public class ArticleServiece{
	private Logger logger = LoggerFactory.getLogger(ArticleServiece.class);
	
	@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	private MemberRepository memberRepository;
	
	/**
	 * article 저장.
	 * 
	 * 필수저장값>> $title $content $categoryId $memberId $createDate.
	 * 파일업로드 기능 미구현.
	 * 
	 * @param articleCreateDto $title $content $nickName $categoryCode $fileName $fileDate
	 * 
	 */
	public void createArticle(ArticleCreateDto articleCreateDto) {
		
		//controller받은값
		logger.info("service articleCreateDto>><{}>", articleCreateDto);
		
		//categoryId정보
		CategoryQueryDto categoryInfo = articleRepository.findCategoryByCode(articleCreateDto.getCategoryCode());
		logger.debug("categoryInfo>><{}>", categoryInfo);
		
		//memberId정보
		String memberId = memberRepository.findMemberIdByNickName(articleCreateDto.getNickName());
		logger.debug("memberId>><{}>", memberId);
		
		//article테이블 저장
		ArticleQueryDto articleQueryDto = new ArticleQueryDto();
		articleQueryDto.setMemberId(memberId);
		articleQueryDto.setCategoryId(categoryInfo.getId());
		articleQueryDto.setTitle(articleCreateDto.getTitle());
		articleQueryDto.setContent(articleCreateDto.getContent());
		logger.debug("articleQueryDto>><{}>", articleQueryDto);
		articleRepository.saveArticle(articleQueryDto);
		
	}
	
	/**
	 * category별  article 리스트.
	 *  
	 * @return $count [$title $nickName $createDate $modifyDate $hit]
	 * 
	 */
	public ArticleList getArticleList(String code) {
		
		//controller받은값
		logger.info("insertValueFromContoller>><{}>", code);
		
		//categoryId값
		CategoryQueryDto categoryInfo = articleRepository.findCategoryByCode(code);
		logger.debug("categoryInfo>><{}>", categoryInfo);
		
		//count정보
		int count = articleRepository.findCountByCategoryId(categoryInfo.getId());
		logger.debug("count>><{}>", count);
		
		//article정보
		List<ArticleQueryDto> articleInfo = articleRepository.findArticleInfoByCategoryId(categoryInfo.getId());
		logger.debug("articleInfo>><{}>", articleInfo);
		
		//return타입에 저장 및 nickName찾기
		List<ArticleEntire> list = new ArrayList<>();
		
		for(ArticleQueryDto value : articleInfo) {
			ArticleEntire listInfo = new ArticleEntire();
			listInfo.setId(value.getId());
			listInfo.setCreateDate(value.getCreateDate());
			listInfo.setModifyDate(value.getModifyDate());
			listInfo.setHit(value.getHit());
			listInfo.setTitle(value.getTitle());
			
			//nickName정보
			MemberQueryDto meberInfo = memberRepository.findMemberInfoByMemberId(value.getMemberId());
			listInfo.setNickName(meberInfo.getNickName());
			logger.debug("meberInfo>><{}>", meberInfo);
			list.add(listInfo);
		}
		
		//return
		logger.info("returnArticleList>><{}>", list);
		ArticleList articleList = new ArticleList(count, list);
		return articleList;
		
	}
	
	/**
	 * article 조회.
	 * 
	 * @return 
	 * 
	 */
	public ArticleEntire getArticle(String id) {
		
		//controller받은값
		logger.info("insertValueFromContoller>><{}>", id);
		
		//article정보
		ArticleQueryDto articleQueryDto = articleRepository.findArticleInfoById(id);
		logger.debug("articleQueryDto>><{}>", articleQueryDto);
		
		//memberNickName정보
		MemberQueryDto memberQueryDto = memberRepository.findMemberInfoByMemberId(articleQueryDto.getMemberId());
		logger.debug("memberQueryDto>><{}>", memberQueryDto);
		
		//return(convert코드로 바꾸기)
		ArticleEntire articleEntire = new ArticleEntire();
		articleEntire.setId(articleQueryDto.getId());
		articleEntire.setTitle(articleQueryDto.getTitle());
		articleEntire.setContent(articleQueryDto.getContent());
		articleEntire.setCreateDate(articleQueryDto.getCreateDate());
		articleEntire.setModifyDate(articleQueryDto.getModifyDate());
		articleEntire.setHit(articleQueryDto.getHit());
		articleEntire.setNickName(memberQueryDto.getNickName());
		
		//return
		return articleEntire;
		
	}
	
	/**
	 * article 수정.
	 * 
	 * @param articleUpdateDto
	 * 
	 */
	public void updateArticle(ArticleUpdateDto articleUpdateDto) {
		
		//controller받은값
		logger.info("insertValueFromContoller>><{}>", articleUpdateDto);
		
		//article업데이트
		articleRepository.update(articleUpdateDto);
		
	}
	
	/**
	 * article 삭제.
	 * 
	 */
	public void deleteArticle(String id) {
		articleRepository.delete(id);
	}
	
}
