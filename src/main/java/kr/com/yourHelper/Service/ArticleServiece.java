package kr.com.yourHelper.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.com.yourHelper.Dao.ArticleRepository;
import kr.com.yourHelper.Dto.ArticleContentDto;
import kr.com.yourHelper.Dto.ArticleCreateDto;
import kr.com.yourHelper.Dto.ArticleDto;
import kr.com.yourHelper.Dto.ArticleFileDto;

@Service
public class ArticleServiece{
	
	@Autowired
	private ArticleRepository articleRepository;
	
	public void createArticle(ArticleCreateDto articleCreateDto) {
		
		//controller���� �޴� ���ʰ�
		System.out.println("service articleCreateDto>>" + articleCreateDto);
		
		//article���̺� ����
		ArticleDto articleDto = new ArticleDto();
		articleDto.setMemberId(articleCreateDto.getMemberId());
		articleDto.setTitle(articleCreateDto.getTitle());
		articleDto.setCategoryId(articleCreateDto.getCategoryId());
		articleDto.setNickName(articleCreateDto.getNickName());

//		articleRepository.saveArticle(articleDto);
		System.out.println("service article mapper>>"+articleDto);
		
		//content���̺� ����
		ArticleContentDto articleContentDto = new ArticleContentDto();
		articleContentDto.setArticleId(articleDto.getArticleId());
		articleContentDto.setContent(articleCreateDto.getContent());
		
//		articleRepository.saveContent(articleContentDto);
		System.out.println("service content mapper>>"+articleContentDto);
		
		//file���̺� ����
		if(articleCreateDto.getFileDate() != null) {
			ArticleFileDto articleFileDto = new ArticleFileDto();
			articleFileDto.setArticleId(articleDto.getArticleId());
			articleFileDto.setFileName(articleCreateDto.getFileName());
			articleFileDto.setFileDate(articleCreateDto.getFileDate());
			
//			articleRepository.saveFile(articleFileDto);
			System.out.println("service content mapper>>"+articleContentDto);
		}

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
