package com.mycom.springbootjpacrudfind.config;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.info.Info;
@Configuration
public class SwaggerConfig {
	// DI
	@Bean
	OpenAPI openApi() {
		return new OpenAPI()
			.components(new Components())
			.info(apiInfo());
	}

	private Info apiInfo() {
		return new Info()
			.title("학생관리 API")
			.description("REST API 로 구현된 학생관리 기능을 테스트합니다.")
			.version("v0.9");
	}

	@Bean
	OpenApiCustomizer getEndpointsCustomizer() {
		return openApi -> {
			Paths paths = new Paths();

			// 원래 기본적으로 openApi 가 가지고 있는 path(PathItem) 들 중 우리가 원하는 path(PathItem) 만 paths 에 담는다.
			openApi.getPaths().forEach( (path, pathItem) -> {
				PathItem newPathItem = new PathItem();

				// get 에 해당하는 PathItem 확인
				if(pathItem.getGet() != null ) {
					newPathItem.setGet(pathItem.getGet());
					paths.addPathItem(path, newPathItem);
				}
			});

			openApi.setPaths(paths);
		};
	}
}