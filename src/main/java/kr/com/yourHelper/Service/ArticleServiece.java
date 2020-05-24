package kr.com.yourHelper.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.com.yourHelper.Dao.ArticleRepository;
import kr.com.yourHelper.Dao.MemberRepository;
import kr.com.yourHelper.Dto.ArticleContentDto;
import kr.com.yourHelper.Dto.ArticleCreateDto;
import kr.com.yourHelper.Dto.ArticleDto;
import kr.com.yourHelper.Dto.ArticleFileDto;
import kr.com.yourHelper.Dto.CategoryDto;

@Service
public class ArticleServiece{
	
	@Autowired
	private ArticleRepository articleRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	/**
	 * article 저장
	 * 
	 * 필수저장값>> $title $content $categoryId $memberId.
	 * categoryId는 categoryCode로 memberId는 nickName으로 찾음.
	 * 
	 * @param articleCreateDto $title $content $nickName $categoryCode $fileName $fileDate
	 * 
	 */
	public void createArticle(ArticleCreateDto articleCreateDto) {
		
		//controller에서 받는 최초값
		System.out.println("service articleCreateDto>>" + articleCreateDto);
		
		//categoryId값 찾기
		CategoryDto categoryInfo = articleRepository.findCategoryByCode(articleCreateDto.getCategoryCode());
		System.out.println("categoryInfo>>" + categoryInfo);
		
		//memberId값 찾기
		int memberId = memberRepository.findMemberIdByNickName(articleCreateDto.getNickName());
		System.out.println("memberId>>" + memberId);
		
		//article테이블 저장
		ArticleDto articleDto = new ArticleDto();
		articleDto.setMemberId(memberId);
		articleDto.setTitle(articleCreateDto.getTitle());
		articleDto.setCategoryId(categoryInfo.getId());
		articleDto.setNickName(articleCreateDto.getNickName());
		System.out.println("service article mapper>>"+articleDto);
		articleRepository.saveArticle(articleDto);
		
		//content테이블 저장
		System.out.println("articleId??>>" + articleDto.getArticleId()); // article에 저장되고 아뒤값이 넘어옴
		ArticleContentDto articleContentDto = new ArticleContentDto();
		articleContentDto.setArticleId(articleDto.getArticleId());
		articleContentDto.setContent(articleCreateDto.getContent());
		System.out.println("service content mapper>>"+articleContentDto);
		articleRepository.saveContent(articleContentDto);
		
		//file테이블 저장
		if(articleCreateDto.getFileDate() != null) {
			ArticleFileDto articleFileDto = new ArticleFileDto();
			articleFileDto.setArticleId(articleDto.getArticleId());
			articleFileDto.setFileName(articleCreateDto.getFileName());
			articleFileDto.setFileDate(articleCreateDto.getFileDate());
			System.out.println("service content mapper>>"+articleContentDto);
//			articleRepository.saveFile(articleFileDto);
		}

	}
	
	
	
	
//	public List<ArticleDto> allList(String index) {
//		모든 article 정보 가져오기
//		List<ArticleDto> list = articleMapper.findAllListByIndexId(index);
		
//		article의 불필요한 정보 걸러내기
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
