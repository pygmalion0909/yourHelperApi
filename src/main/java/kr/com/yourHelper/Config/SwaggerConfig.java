package kr.com.yourHelper.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    
	//swagger ui에 표시될 정보 설정
	private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("youerHelper API")
                .description("youerHelper Project All API document")
                .build();
    }
	
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
        		//apiInfo는 .select()보다 위에 위치해야 적용됨
        		.apiInfo(this.apiInfo())
                .select()
                //현재 RequestMapping으로 할당된 모든 URL 리스트를 추출
                .apis(RequestHandlerSelectors.any())
                //그중 /api/v1/** 인 URL들만 필터링
                .paths(PathSelectors.ant("/api/v1/**"))
                .build();
    }
    
}
