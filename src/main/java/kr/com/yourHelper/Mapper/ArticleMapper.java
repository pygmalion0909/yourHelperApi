package kr.com.yourHelper.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.com.yourHelper.Dto.ArticleDto;


@Mapper
public interface ArticleMapper {
	public List<ArticleDto> findAllListByIndexId(String id);
	
	
	
	
	
	public int listAllCount(int id);
	public List<ArticleDto> findDetatilList(String index);
	public void createByArticleId(ArticleDto articleDto);
	public void deleteById(String id);
	public void UpdateById(ArticleDto articleDto);
}
