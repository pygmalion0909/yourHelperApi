package kr.com.yourHelper.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import kr.com.yourHelper.Domain.MemberList;
import kr.com.yourHelper.Dto.ArticleDto;
import kr.com.yourHelper.Dto.MemberCreateDto;
import kr.com.yourHelper.Dto.MemberDto;
import kr.com.yourHelper.Service.ArticleServiece;
import kr.com.yourHelper.Service.MemberService;

@RestController
@RequestMapping("/api/v1")
public class GateA1 {
	
	@Autowired
	ArticleServiece articleService;
	@Autowired
	MemberService memberService;
	
//	로그인한 member와 guest도 접속 가능(모든 사용자 접속 가능)
//	각각의 카테고리의 내용을 최신 5개 불러와서 로딩하기
//	@GetMapping(value="/board")
//	public void helperHome(){
//		articleService.articleMainList();
//	}
	/**
	 * 
	 * @param index
	 * @return
	 * 
	 */
	@GetMapping(value="/article/{index}")
	@ApiOperation(value = "article", tags = "article")
	public List<ArticleDto> articleAllList(@PathVariable(value="index") String index) {
		List<ArticleDto> list = articleService.allList(index);
		return list;
	}
	
//	article Detail by id(R)
//	@GetMapping(value="/board/detail/{id}")
//	public List<ArticleDto> articleDetailList(@RequestParam(value="index") String index){
//		System.out.println("index : >> " + index);
//		List<ArticleDto> detailList = article.articleDetailList(index);
//		return detailList;
//	}
	
//	create board list(C)
//	use just member
//	/board/create?id=1
//	@PostMapping(value="/board/create")
//	public void articleCreate(@RequestBody ArticleDto articleDto) {
//		System.out.println(articleDto);
//		article.articleCreate(articleDto);
//	}
	
//	update board list(U)
//	@PutMapping(value="/board/update")
//	public void articleUpdate(@RequestBody ArticleDto articleDto) {
//		System.out.println(articleDto);
//		article.articleUpdate(articleDto);
//	}
	
//	delete board list(D)
//	@DeleteMapping(value="/board/delete/{id}")
//	public void articleDelete(@PathVariable(value="id") String id) {
//		System.out.println(id);
//		article.articleDelete(id);
//	}
	
	/**
	 * member생성.
	 * 
	 * memberId, password, nickName은 필수값.
	 * 
	 * @param userCreateDto $memberId $password $nickName
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
	public MemberList allListMember() {
		MemberList allListInfo = memberService.allList();
		System.out.println("모든member정보 controller>>" + allListInfo);
		return allListInfo;
	}
	
	
	
	
	
	
	
}
