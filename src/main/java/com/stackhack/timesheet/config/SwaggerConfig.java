package com.stackhack.timesheet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String CONTROLLERS_PACKAGE =
            "com.stackhack.timesheet.api";

    private Docket docket(String pkg, String paths, String groupName) {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(ObjectUtils.isEmpty(groupName) ? paths : groupName)
                .select()
                .apis(RequestHandlerSelectors.basePackage(pkg))
                .paths(regex(paths))
                .build()
                .apiInfo(metadata());
    }

    private ApiInfo metadata() {
        return new ApiInfoBuilder()
                .title("Time sheet Application Swagger UI")
                .contact(new Contact("Vikram", "https://sarvika.com", "vikram.singh@sarvika.com"))
                .build();
    }

    @Bean
    public Docket projectApiInfo() {
        return docket(CONTROLLERS_PACKAGE, "/project.*", "Project API");
    }


    @Bean
    public Docket costHeadApiInfo() {
        return docket(CONTROLLERS_PACKAGE, "/costjead.*", "Cost Head API");
    }

    @Bean
    public Docket userApiInfo() {
        return docket(CONTROLLERS_PACKAGE, "/user.*", "User API");
    }

    @Bean
    public Docket teamApiInfo() {
        return docket(CONTROLLERS_PACKAGE, "/team.*", "Team API");
    }

    @Bean
    public Docket timeApiInfo() {
        return docket(CONTROLLERS_PACKAGE, "/time.*", "Time Log API");
    }

}
