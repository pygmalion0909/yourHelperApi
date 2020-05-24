package kr.com.yourHelper.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import kr.com.yourHelper.Domain.ArticleList;
import kr.com.yourHelper.Domain.MemberList;
import kr.com.yourHelper.Dto.ArticleCreateDto;
import kr.com.yourHelper.Dto.MemberCreateDto;
import kr.com.yourHelper.Service.ArticleServiece;
import kr.com.yourHelper.Service.MemberService;

@RestController
@RequestMapping("/api/v1")
public class GateA1 {
	
	@Autowired
	ArticleServiece articleService;
	@Autowired
	MemberService memberService;
	
	/**
	 * article ����.
	 * 
	 * param�ʼ���>> $title $content $nickName $categoryCode.
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
	 * article ����Ʈ.
	 * 
	 * offset, limit�� ������Ʈ������ category�� �Ķ���ͷ� ���� �ޱ�.
	 * ���� url>> www.YourHelper.com.kr/NT?offest=0&limit=10.
	 * 
	 * @return $count [$title $nickName $createDate $modifyDate $hit]
	 * 
	 */
	@GetMapping(value="/article/{code}")
	@ApiOperation(value = "article", tags = "article")
	public ArticleList getArticleList(@PathVariable("code") String code) {
		return articleService.getArticleList(code);
	}
	
	/**
	 * member����.
	 * 
	 * �ʼ���>> $memberId $password $nickName $authorityCode.
	 * 
	 * @param userCreateDto $memberId $password $nickName $authorityCode.
	 * 
	 */
	@PostMapping("/member/create")
	@ApiOperation(value = "member", tags = "member")
	public void createMember(@RequestBody MemberCreateDto memberCreateDto) {
		System.out.println("memberȸ������ controller>>" + memberCreateDto);
		memberService.createMember(memberCreateDto);
	}
	
	/**
	 * ��� member ��ȸ.
	 *
	 * ���ٰ��� ����>> admin.
	 * 
	 * @return $count [$loginId $nickName $createDate $modifyDate].
	 * 
	 */
	@GetMapping("/member")
	@ApiOperation(value = "member", tags = "member")
	public MemberList getMemberInfo() {
		System.out.println("���member���� controller>>" + memberService.getMemberInfo());
		return memberService.getMemberInfo();
	}
	
}
