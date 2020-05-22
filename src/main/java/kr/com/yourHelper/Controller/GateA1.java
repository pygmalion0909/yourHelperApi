package kr.com.yourHelper.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.com.yourHelper.Dto.ArticleDto;
import kr.com.yourHelper.Dto.MemberCreateDto;
import kr.com.yourHelper.Service.ArticleServiece;
import kr.com.yourHelper.Service.MemberService;

@RestController
@RequestMapping("/v1")
public class GateA1 {
	
	@Autowired
	ArticleServiece articleService;
	@Autowired
	MemberService memeberService;
	
//	로그인한 member와 guest도 접속 가능(모든 사용자 접속 가능)
//	각각의 카테고리의 내용을 최신 5개 불러와서 로딩하기
//	@GetMapping(value="/board")
//	public void helperHome(){
//		articleService.articleMainList();
//	}
	
	@GetMapping(value="/article/{index}")
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
	 * member생성
	 * 
	 * @param userCreateDto UserCreateDto $memberId $password $nickName
	 * 
	 */
	//member 등록
	@PostMapping("/member/create")
	public void createMember(@RequestBody MemberCreateDto memberCreateDto) {
		System.out.println("memberCreateDto>>" + memberCreateDto);
		memeberService.createMember(memberCreateDto);
	}
	
}
