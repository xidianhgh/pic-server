package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by MI on 2019/4/29.
 */
@Configuration
@EnableSwagger2
@ComponentScan("com.example.demo")
public class SwaggerConfig {
    @Bean
    public Docket docket() {
//        List<ResponseMessage> responseMessages = new ArrayList<ResponseMessage>();
//        responseMessages.add(new ResponseMessageBuilder().code(HttpServletResponse.SC_OK).message("操作成功").build());
//        responseMessages.add(new ResponseMessageBuilder().code(HttpServletResponse.SC_NOT_FOUND).message("资源不存在").build());
//        responseMessages.add(new ResponseMessageBuilder().code(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).message("服务器异常").build());

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("demo接口")
                .apiInfo(apiInfo())
//                .useDefaultResponseMessages(false)
//                .globalResponseMessage(RequestMethod.GET, responseMessages)
//                .globalResponseMessage(RequestMethod.POST, responseMessages)
//                .globalResponseMessage(RequestMethod.DELETE, responseMessages)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.demo"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("测试项目")
                .version("1.0.0")
                .description("这是项目描述").build();
    }



}
