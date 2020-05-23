package kr.com.yourHelper.Dao;

import org.apache.ibatis.annotations.Mapper;

import kr.com.yourHelper.Dto.ArticleDto;

@Mapper
public interface ArticleRepository {
	
	public void saveArticle(ArticleDto articleDto);
//	public void saveContent(ArticleCreateDto articleCreateDto);
//	public void saveFile(ArticleCreateDto articleCreateDto);
//	public List<ArticleDto> findAllListByIndexId(String id);
//	public int listAllCount(int id);
//	public List<ArticleDto> findDetatilList(String index);
//	public void createByArticleId(ArticleDto articleDto);
//	public void deleteById(String id);
//	public void UpdateById(ArticleDto articleDto);
}
