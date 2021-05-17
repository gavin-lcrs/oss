package com.gavin.oss.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author jiwen.cao
 * @Date 2021/5/17
 * @Description
 */
@Configuration
@EnableSwagger2
public class SwaggerApp {

    public ApiInfo apiInfoRoute() {
        return  new ApiInfoBuilder()
                .title("OSS系统 API")
                .description("OSS系统")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }

    @Bean
    public Docket web_api_obj() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfoRoute())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/obj/**"))
                .build()
                .groupName("文件管理")
                .pathMapping("/");
    }

    @Bean
    public Docket web_api_upload() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfoRoute())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/upload/**"))
                .build()
                .groupName("文件上传")
                .pathMapping("/");
    }
}
