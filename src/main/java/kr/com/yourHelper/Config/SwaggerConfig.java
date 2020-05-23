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
    
	//swagger ui�� ǥ�õ� ���� ����
	private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("youerHelper API")
                .description("youerHelper Project All API document")
                .build();
    }
	
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
        		//apiInfo�� .select()���� ���� ��ġ�ؾ� �����
        		.apiInfo(this.apiInfo())
                .select()
                //���� RequestMapping���� �Ҵ�� ��� URL ����Ʈ�� ����
                .apis(RequestHandlerSelectors.any())
                //���� /api/v1/** �� URL�鸸 ���͸�
                .paths(PathSelectors.ant("/api/v1/**"))
                .build();
    }
    
}
