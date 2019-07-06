package com.mouse.bms.customer.config.config;

import com.google.common.collect.Lists;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * CopyRight(C),mouse
 *
 * @author : mouse
 * @fileName : SwaggerConfig
 * @date : 2019/3/19 14:17
 * @description :
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket buildDocket() {
        //header中增加 token_id参数，没有可以去掉
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        List<Parameter> parameters = Lists.newArrayList();
        Parameter
            build =
            parameterBuilder.name("token_id").description("use token").modelRef(new ModelRef("string"))
                .parameterType("header").required(false).build();
        parameters.add(build);
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(buildApiInfo())
            .select()
            // 指定controller存放的目录路径
            .apis(RequestHandlerSelectors.basePackage("com.mouse.bms.customer.web.controller"))
            .paths(PathSelectors.any())
            .build()
            .globalOperationParameters(parameters);
    }

    private ApiInfo buildApiInfo() {
        return new ApiInfoBuilder()
            .title("用户中心API")
            .description("提供给前端界面或其他中心调用")
            .termsOfServiceUrl("http://127.0.0.1:8888")
            .contact(new Contact(
                "MOUSE",
                "http://127.0.0.1:8888",
                "1379202076@qq.com"))
            .version("1.0")
            .build();
    }

}
