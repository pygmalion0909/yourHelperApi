package kr.com.yourHelper.Dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.com.yourHelper.Domain.ArticleEntire;
import kr.com.yourHelper.Dto.ArticleUpdateDto;
import kr.com.yourHelper.QueryDto.ArticleQueryDto;
import kr.com.yourHelper.QueryDto.CategoryQueryDto;

@Mapper
public interface ArticleRepository {
	
	public void saveArticle(ArticleQueryDto articleDto);
	public CategoryQueryDto findCategoryByCode(String code);
	public List<ArticleQueryDto> findArticleInfoByCategoryId(String categoryId);
	public ArticleQueryDto findArticleInfoById(String id);
	public int findCountByCategoryId(String categoryId);
	public void update(ArticleUpdateDto articleUpdateDto);
	public void delete(String id);
	
}
