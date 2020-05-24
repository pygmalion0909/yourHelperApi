package kr.com.yourHelper.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.com.yourHelper.Dao.ArticleRepository;
import kr.com.yourHelper.Dao.MemberRepository;
import kr.com.yourHelper.Domain.ArticleList;
import kr.com.yourHelper.Dto.ArticleContentDto;
import kr.com.yourHelper.Dto.ArticleCreateDto;
import kr.com.yourHelper.Dto.ArticleDto;
import kr.com.yourHelper.Dto.ArticleEntireDto;
import kr.com.yourHelper.Dto.ArticleFileDto;
import kr.com.yourHelper.Dto.CategoryDto;
import kr.com.yourHelper.Dto.MemberDto;

@Service
public class ArticleServiece{
	
	@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	private MemberRepository memberRepository;
	
	/**
	 * article ����.
	 * 
	 * �ʼ����尪>> $title $content $categoryId $memberId.
	 * categoryId�� categoryCode�� memberId�� nickName���� ã��.
	 * 
	 * @param articleCreateDto $title $content $nickName $categoryCode $fileName $fileDate
	 * 
	 */
	public void createArticle(ArticleCreateDto articleCreateDto) {
		
		//controller���� �޴� ���ʰ�
		System.out.println("service articleCreateDto>>" + articleCreateDto);
		
		//categoryId�� ã��
		CategoryDto categoryInfo = articleRepository.findCategoryByCode(articleCreateDto.getCategoryCode());
		System.out.println("categoryInfo>>" + categoryInfo);
		
		//memberId�� ã��
		String memberId = memberRepository.findMemberIdByNickName(articleCreateDto.getNickName());
		System.out.println("memberId>>" + memberId);
		
		//article���̺� ����
		ArticleDto articleDto = new ArticleDto();
		articleDto.setMemberId(memberId);
		articleDto.setTitle(articleCreateDto.getTitle());
		articleDto.setCategoryId(categoryInfo.getId());
		System.out.println("service article mapper>>"+articleDto);
		articleRepository.saveArticle(articleDto);
		
		//content���̺� ����
		ArticleContentDto articleContentDto = new ArticleContentDto();
		articleContentDto.setArticleId(articleDto.getId());
		articleContentDto.setContent(articleCreateDto.getContent());
		System.out.println("service content mapper>>"+articleContentDto);
		articleRepository.saveContent(articleContentDto);
		
		//file���̺� ����
		if(articleCreateDto.getFileDate() != null) {
			ArticleFileDto articleFileDto = new ArticleFileDto();
			articleFileDto.setArticleId(articleDto.getId());
			articleFileDto.setFileName(articleCreateDto.getFileName());
			articleFileDto.setFileDate(articleCreateDto.getFileDate());
			System.out.println("service content mapper>>"+articleContentDto);
//			articleRepository.saveFile(articleFileDto);
		}

	}
	
	/**
	 * article ����Ʈ.
	 *  
	 * @return
	 * 
	 */
	public ArticleList getArticleList(String code) {
		
		//controller���� ������ ���ʰ�
		System.out.println("from insert controller>>" + code);
		
		//categoryId��
		CategoryDto categoryInfo = articleRepository.findCategoryByCode(code);
		System.out.println("categoryInfo in service>>" + categoryInfo);
		
		//count����
		int count = articleRepository.findCountByCategoryId(categoryInfo.getId());
		System.out.println("count in service>>" + count);
		
		//article����
		List<ArticleDto> articleInfo = articleRepository.findArticleInfoByCategoryId(categoryInfo.getId());
		System.out.println("articleInfo in service>>" + articleInfo);

		//returnŸ�Կ� ���� �� nickNameã��
		List<ArticleEntireDto> list = new ArrayList<>();
				
		for(ArticleDto value : articleInfo) {
			ArticleEntireDto listInfo = new ArticleEntireDto();
			listInfo.setId(value.getId());
			listInfo.setCreateDate(value.getCreateDate());
			listInfo.setModifyDate(value.getModifyDate());
			listInfo.setHit(value.getHit());
			listInfo.setTitle(value.getTitle());
			
			//nickName����
			MemberDto meberInfo = memberRepository.findMemberInfoByMemberId(value.getMemberId());
			listInfo.setNickName(meberInfo.getNickName());
			System.out.println("meberInfo in service>>" + meberInfo);
			
			list.add(listInfo);
		}
		
		//return
		System.out.println("return in service>>" + list);
		ArticleList articleList = new ArticleList(count, list);
		return articleList;
		
	}
	
//	public List<ArticleDto> allList(String index) {
//		��� article ���� ��������
//		List<ArticleDto> list = articleMapper.findAllListByIndexId(index);
		
//		article�� ���ʿ��� ���� �ɷ�����
//		ArticleDto articleDto = new ArticleDto();
//		List<ArticleDto> filterArticleDate = new ArrayList<ArticleDto>();
//		for(ArticleDto listValue : list) {
//			articleDto.setId(listValue.getId());
//			articleDto.setTitle(listValue.getTitle());
//			articleDto.setCreateDate(listValue.getCreateDate());
//			articleDto.setUpdateDate(listValue.getUpdateDate());
//			articleDto.setHits(listValue.getHits());
//			articleDto.setArticleListId(listValue.getArticleListId());
//			filterArticleDate.add(articleDto);
//		}
//
//		return filterArticleDate;
//	};
	
//	public List<ArticleDto> articleDetailList(String index){
//		return articleMapper.findDetatilList(index);
//	}
//	
//	public void articleCreate(ArticleDto articleDto) {
//		articleMapper.createByArticleId(articleDto);
//	}
//	
//	public void articleDelete(String id) {
//		articleMapper.deleteById(id);
//	}
//	
//	public void articleUpdate(ArticleDto articleDto) {
//		articleMapper.UpdateById(articleDto);
//	}
}
