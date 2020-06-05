package kr.com.yourHelper.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import kr.com.yourHelper.Domain.ArticleEntire;
import kr.com.yourHelper.Domain.ArticleList;
import kr.com.yourHelper.Domain.MemberList;
import kr.com.yourHelper.Dto.ArticleCreateDto;
import kr.com.yourHelper.Dto.ArticleUpdateDto;
import kr.com.yourHelper.Dto.MemberCreateDto;
import kr.com.yourHelper.Dto.MemberDto;
import kr.com.yourHelper.Service.ArticleServiece;
import kr.com.yourHelper.Service.MemberService;

@RestController
@RequestMapping("/api/v1")
public class GateA1 {
	private Logger logger = LoggerFactory.getLogger(GateA1.class);
	
	@Autowired
	ArticleServiece articleService;
	@Autowired
	MemberService memberService;
	
	/**
	 * article 생성.
	 * 
	 * 필수param>> $title $content $nickName $categoryCode.
	 * 
	 * @param ArticleCreateDto $title $content $nickName $categoryCode $fileName $fileDate.
	 * 
	 */
	@PostMapping(value="/article/create")
	@ApiOperation(value = "article", tags = "article")
	public void createArticle(@RequestBody ArticleCreateDto articleCreateDto) {
		logger.info("insertValueFront>><{}>", articleCreateDto);
		articleService.createArticle(articleCreateDto);
	}
	
	/**
	 * category별 article 리스트.
	 * 
	 * 필수return>>$count [$id $title $nickName $createDate $modifyDate $hit]
	 * offset, limit는 쿼리스트링으로 category는 파라미터로 전달 받기.
	 * 예시 url>> www.YourHelper.com.kr/NT?offest=0&limit=10.
	 * 
	 * @return $count [$id $title $nickName $createDate $modifyDate $hit]
	 * 
	 */
	@GetMapping(value="/article/{code}")
	@ApiOperation(value = "article", tags = "article")
	public ArticleList getArticleList(@PathVariable("code") String code) {
		logger.info("insertValueFront>><{}>", code);
		logger.info("outPutFromService>><{}>", articleService.getArticleList(code));
		return articleService.getArticleList(code);
	}
	
	/**
	 * article 상세 조회.
	 * 
	 * 필수return>> $id $title $content $createDate $modifyDate $nickName $hit.
	 * article id로 조회.
	 * 
	 * @return $id $title $content $createDate $modifyDate $nickName $hit $file정보
	 * 
	 */
	@GetMapping(value="/article/detail/{id}")
	@ApiOperation(value = "article", tags = "article")
	public ArticleEntire getArticle(@PathVariable("id") String id) {
		logger.info("insertValueFront>><{}>", id);
		logger.info("outPutFromService>><{}>", articleService.getArticle(id));
		return articleService.getArticle(id);
	}
	
	/**
	 * article 수정.
	 * 
	 * 필수param>>$id.
	 * <strong>
	 * 동일 member만 접근가능.
	 * </strong>
	 */
	@PutMapping(value="/article/update")
	@ApiOperation(value = "article", tags = "article")
	public void updateArticle(@RequestBody ArticleUpdateDto articleUpdateDto) {
		logger.info("insertValueFront>><{}>", articleUpdateDto);
		articleService.updateArticle(articleUpdateDto);
	}
	
	/**
	 * article 삭제.
	 * 
	 * <strong>
	 * 동일 member 또는 admin만 접근가능.
	 * </strong>
	 * 
	 */
	@DeleteMapping(value="/article/{id}")
	@ApiOperation(value = "article", tags = "article")
	public void deleteArticle(@PathVariable("id") String id) {
		articleService.deleteArticle(id);
	}
	
	/**
	 * member생성.
	 * 
	 * 필수param>> $memberId $password $nickName $authorityCode.
	 * 
	 * @param userCreateDto $memberId $password $nickName $authorityCode.
	 * 
	 */
	@PostMapping("/member/create")
	@ApiOperation(value = "member", tags = "member")
	public void createMember(@RequestBody MemberCreateDto memberCreateDto) {
		logger.info("insertValueFront>><{}>", memberCreateDto);
		memberService.createMember(memberCreateDto);
	}
	
	/**
	 * 모든 member 조회.
	 *
	 * 필수return>>$count [$loginId $nickName $createDate $modifyDate].
	 * 
	 * <strong>
	 * 접근가능 권한>> admin.
	 * </strong>
	 * 
	 * @return $count [$loginId $nickName $createDate $modifyDate].
	 * 
	 */
	@GetMapping("/member")
	@ApiOperation(value = "member", tags = "member")
	public MemberList getMemberInfo() {
		logger.info("outPutFromService>><{}>", memberService.getMemberInfo());
		return memberService.getMemberInfo();
	}
	
	/**
	 * login
	 * 
	 * 필수param>> $loginId $password
	 * 
	 * @return JWT token
	 * 
	 */
	@PostMapping("/login")
	@ApiOperation(value = "login", tags = "login")
	public String login(@RequestBody MemberDto member) {
		System.out.println("!!!" + member);
		logger.info("insertValueFront>><{}>", member);
		return memberService.login(member);
	}
	
	@GetMapping("/test")
	public String test() {
		return "성공!";
	}
	
}
