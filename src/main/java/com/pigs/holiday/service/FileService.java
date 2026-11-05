package com.pigs.holiday.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

/**
 * 업로드 파일을 서버 로컬 디스크(file.upload-dir)에 저장하고,
 * {file.public-url}/files/{dirName}{uuid}.{ext} 형태의 URL을 돌려준다.
 * 저장된 파일은 WebConfig의 /files/** 리소스 핸들러로 제공된다.
 */
@Service
public class FileService {

    public static final String URL_PATH = "/files/";

    // 이미지 업로드만 허용 (html 등 업로드 후 같은 도메인에서 실행되는 것을 막는다)
    private static final Set<String> ALLOWED_EXTENSIONS = Set.of("jpg", "jpeg", "png", "gif", "webp", "bmp", "heic", "heif");

    private final Path uploadRoot;
    private final String publicUrl;

    public FileService(@Value("${file.upload-dir}") String uploadDir,
                       @Value("${file.public-url:}") String publicUrl) {
        this.uploadRoot = Paths.get(uploadDir).toAbsolutePath().normalize();
        this.publicUrl = publicUrl.endsWith("/") ? publicUrl.substring(0, publicUrl.length() - 1) : publicUrl;
    }

    public String uploadFile(MultipartFile file, String dirName) throws IOException {
        String extension = extractExtension(file.getOriginalFilename());

        Path dir = uploadRoot.resolve(dirName).normalize();
        if (!dir.startsWith(uploadRoot)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid upload directory");
        }
        Files.createDirectories(dir);

        String fileName = UUID.randomUUID() + "." + extension;
        try (InputStream in = file.getInputStream()) {
            Files.copy(in, dir.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
        }

        String relative = uploadRoot.relativize(dir.resolve(fileName)).toString().replace('\\', '/');
        return publicUrl + URL_PATH + relative;
    }

    public List<String> uploadFiles(List<MultipartFile> files, String dirName) throws IOException {
        List<String> urls = new ArrayList<>();

        if (files != null && !files.isEmpty()) {
            for (MultipartFile file : files) {
                if (!file.isEmpty()) {
                    urls.add(uploadFile(file, dirName));
                }
            }
        }
        return urls;
    }

    public Path getUploadRoot() {
        return uploadRoot;
    }

    private String extractExtension(String originalFilename) {
        if (originalFilename == null || originalFilename.lastIndexOf('.') < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "File extension is required");
        }
        String extension = originalFilename.substring(originalFilename.lastIndexOf('.') + 1).toLowerCase(Locale.ROOT);
        if (!ALLOWED_EXTENSIONS.contains(extension)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Only image files are allowed");
        }
        return extension;
    }
}
