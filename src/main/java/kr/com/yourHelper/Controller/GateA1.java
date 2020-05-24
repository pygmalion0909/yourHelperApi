package kr.com.yourHelper.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import kr.com.yourHelper.Domain.MemberList;
import kr.com.yourHelper.Dto.ArticleCreateDto;
import kr.com.yourHelper.Dto.ArticleDto;
import kr.com.yourHelper.Dto.MemberCreateDto;
import kr.com.yourHelper.Service.ArticleServiece;
import kr.com.yourHelper.Service.MemberService;

@RestController
@RequestMapping("/api/v1")
//erd : http://localhost:8080/swagger-ui.html#/
public class GateA1 {
	
	@Autowired
	ArticleServiece articleService;
	@Autowired
	MemberService memberService;
	
	/**
	 * article 생성.
	 * 
	 * param필수값>> $title $content $nickName $categoryCode.
	 * 
	 * @param articleDto $title $content $nickName $categoryCode $fileName $fileDate.
	 * 
	 */
	@PostMapping(value="/article/create")
	@ApiOperation(value = "article", tags = "article")
	public void createArticle(@RequestBody ArticleCreateDto articleCreateDto) {
		System.out.println("Controller articleCreate>> " + articleCreateDto);
		articleService.createArticle(articleCreateDto);
	}
	
	/**
	 * article 상세보기.
	 * 
	 */
	@GetMapping(value="/article/{id}")
	@ApiOperation(value = "article", tags = "article")
	public void getArticleDetail() {
		
	}
	
	/**
	 * article 모든 리스트.
	 * 
	 */
	
	/**
	 * article 수정.
	 */
	
	/**
	 * article 삭제.
	 */
	
	
	
	
	/**
	 * 
	 * @param index
	 * @return
	 * 
	 */
//	@GetMapping(value="/article/{index}")
//	@ApiOperation(value = "article", tags = "article")
//	public List<ArticleDto> articleAllList(@PathVariable(value="index") String index) {
//		List<ArticleDto> list = articleService.allList(index);
//		return list;
//	}
	
//	article Detail by id(R)
//	@GetMapping(value="/board/detail/{id}")
//	public List<ArticleDto> articleDetailList(@RequestParam(value="index") String index){
//		System.out.println("index : >> " + index);
//		List<ArticleDto> detailList = article.articleDetailList(index);
//		return detailList;
//	}
	
//	update board list(U)
//	@PutMapping(value="/board/update")
//	public void articleUpdate(@RequestBody ArticleDto articleDto) {
//		System.out.println(articleDto);
//		article.articleUpdate(articleDto);
//	}
//	
//	delete board list(D)
//	@DeleteMapping(value="/board/delete/{id}")
//	public void articleDelete(@PathVariable(value="id") String id) {
//		System.out.println(id);
//		article.articleDelete(id);
//	}
	
	/**
	 * member생성.
	 * 
	 * memberId, password, nickName, authorityCode은 필수값.
	 * 
	 * @param userCreateDto $memberId $password $nickName $authorityCode
	 * 
	 */
	@PostMapping("/member/create")
	@ApiOperation(value = "member", tags = "member")
	public void createMember(@RequestBody MemberCreateDto memberCreateDto) {
		System.out.println("member회원가입 controller>>" + memberCreateDto);
		memberService.createMember(memberCreateDto);
	}
	
	/**
	 * 모든 member 조회.
	 *
	 * 해당 api는 admin만 접근가능.
	 * 
	 * @return
	 * 
	 */
	@GetMapping("/member")
	@ApiOperation(value = "member", tags = "member")
	public MemberList getMemberInfo() {
		MemberList allMemberInfo = memberService.getMemberInfo();
		System.out.println("모든member정보 controller>>" + allMemberInfo);
		return allMemberInfo;
	}
	
}
