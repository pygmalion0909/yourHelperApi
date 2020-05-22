package kr.com.yourHelper.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.com.yourHelper.Dao.ArticleRepository;
import kr.com.yourHelper.Dto.ArticleDto;

@Service
public class ArticleServiece{
	
	@Autowired
	private ArticleRepository articleMapper;
	
	public List<ArticleDto> allList(String index) {
//		��� article ���� ��������
		List<ArticleDto> list = articleMapper.findAllListByIndexId(index);
		
//		article�� ���ʿ��� ���� �ɷ�����
		ArticleDto articleDto = new ArticleDto();
		List<ArticleDto> filterArticleDate = new ArrayList<ArticleDto>();
		for(ArticleDto listValue : list) {
			articleDto.setId(listValue.getId());
			articleDto.setTitle(listValue.getTitle());
			articleDto.setCreateDate(listValue.getCreateDate());
			articleDto.setUpdateDate(listValue.getUpdateDate());
			articleDto.setHits(listValue.getHits());
			articleDto.setArticleListId(listValue.getArticleListId());
			filterArticleDate.add(articleDto);
		}

		return filterArticleDate;
	};
	
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
