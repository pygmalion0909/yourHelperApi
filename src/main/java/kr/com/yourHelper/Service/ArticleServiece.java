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
	 * article ����.
	 * 
	 * �ʼ����尪>> $title $content $categoryId $memberId $createDate.
	 * ���Ͼ��ε� ��� �̱���.
	 * 
	 * @param articleCreateDto $title $content $nickName $categoryCode $fileName $fileDate
	 * 
	 */
	public void createArticle(ArticleCreateDto articleCreateDto) {
		
		//controller������
		logger.info("service articleCreateDto>><{}>", articleCreateDto);
		
		//categoryId����
		CategoryQueryDto categoryInfo = articleRepository.findCategoryByCode(articleCreateDto.getCategoryCode());
		logger.debug("categoryInfo>><{}>", categoryInfo);
		
		//memberId����
		String memberId = memberRepository.findMemberIdByNickName(articleCreateDto.getNickName());
		logger.debug("memberId>><{}>", memberId);
		
		//article���̺� ����
		ArticleQueryDto articleQueryDto = new ArticleQueryDto();
		articleQueryDto.setMemberId(memberId);
		articleQueryDto.setCategoryId(categoryInfo.getId());
		articleQueryDto.setTitle(articleCreateDto.getTitle());
		articleQueryDto.setContent(articleCreateDto.getContent());
		logger.debug("articleQueryDto>><{}>", articleQueryDto);
		articleRepository.saveArticle(articleQueryDto);
		
	}
	
	/**
	 * category��  article ����Ʈ.
	 *  
	 * @return $count [$title $nickName $createDate $modifyDate $hit]
	 * 
	 */
	public ArticleList getArticleList(String code) {
		
		//controller������
		logger.info("insertValueFromContoller>><{}>", code);
		
		//categoryId��
		CategoryQueryDto categoryInfo = articleRepository.findCategoryByCode(code);
		logger.debug("categoryInfo>><{}>", categoryInfo);
		
		//count����
		int count = articleRepository.findCountByCategoryId(categoryInfo.getId());
		logger.debug("count>><{}>", count);
		
		//article����
		List<ArticleQueryDto> articleInfo = articleRepository.findArticleInfoByCategoryId(categoryInfo.getId());
		logger.debug("articleInfo>><{}>", articleInfo);
		
		//returnŸ�Կ� ���� �� nickNameã��
		List<ArticleEntire> list = new ArrayList<>();
		
		for(ArticleQueryDto value : articleInfo) {
			ArticleEntire listInfo = new ArticleEntire();
			listInfo.setId(value.getId());
			listInfo.setCreateDate(value.getCreateDate());
			listInfo.setModifyDate(value.getModifyDate());
			listInfo.setHit(value.getHit());
			listInfo.setTitle(value.getTitle());
			
			//nickName����
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
	 * article ��ȸ.
	 * 
	 * @return 
	 * 
	 */
	public ArticleEntire getArticle(String id) {
		
		//controller������
		logger.info("insertValueFromContoller>><{}>", id);
		
		//article����
		ArticleQueryDto articleQueryDto = articleRepository.findArticleInfoById(id);
		logger.debug("articleQueryDto>><{}>", articleQueryDto);
		
		//memberNickName����
		MemberQueryDto memberQueryDto = memberRepository.findMemberInfoByMemberId(articleQueryDto.getMemberId());
		logger.debug("memberQueryDto>><{}>", memberQueryDto);
		
		//return(convert�ڵ�� �ٲٱ�)
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
	 * article ����.
	 * 
	 * @param articleUpdateDto
	 * 
	 */
	public void updateArticle(ArticleUpdateDto articleUpdateDto) {
		
		//controller������
		logger.info("insertValueFromContoller>><{}>", articleUpdateDto);
		
		//article������Ʈ
		articleRepository.update(articleUpdateDto);
		
	}
	
	/**
	 * article ����.
	 * 
	 */
	public void deleteArticle(String id) {
		articleRepository.delete(id);
	}
	
}
