package com.pigs.holiday.config;

import com.pigs.holiday.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.concurrent.TimeUnit;

/**
 * 로컬 디스크에 저장된 업로드 파일을 /files/** 로 제공한다.
 * 파일 이름이 UUID라 내용이 바뀌지 않으므로 길게 캐시한다.
 */
@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {

    private final FileService fileService;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String location = fileService.getUploadRoot().toUri().toString();
        if (!location.endsWith("/")) {
            location += "/";
        }

        registry.addResourceHandler(FileService.URL_PATH + "**")
                .addResourceLocations(location)
                .setCacheControl(CacheControl.maxAge(30, TimeUnit.DAYS).cachePublic());
    }
}
