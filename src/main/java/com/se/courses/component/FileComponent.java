package com.se.courses.component;

import com.se.courses.exception.CourseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.FileSystemUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Component
@Slf4j
public class FileComponent {
    @Value("${my.uploaddirectory}")
    private String ROOT;

    public void createExpFile(String dir, String fileName, byte[] bytes) {
        try {
            Path expDir = Paths.get(ROOT + dir);
            Files.createDirectories(expDir);
            Files.write(Paths.get(expDir + "/" + fileName), bytes);
        } catch (IOException e) {
            throw new CourseException("文件保存至本地时失败；" + e.getMessage());
        }
    }

    public void deleteRecursively(String path) {
        try {
            FileSystemUtils.deleteRecursively(Path.of(ROOT + path));
        } catch (IOException e) {
            throw new CourseException("文件删除错误；" + e.getMessage());
        }
    }


    public ResponseEntity<byte[]> toResponseEntity(String fileName) {
        ResponseEntity<byte[]> entity = null;
        try {
            Path path = Paths.get(ROOT + "/" + fileName);
            if (Files.notExists(path)) {
                throw new CourseException("文件不存在！");
            }
            byte[] bytes = Files.readAllBytes(path);
            fileName = fileName.substring(fileName.lastIndexOf("/") + 1);
            fileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment", fileName);
            headers.set("filename", fileName);
            entity = new ResponseEntity<byte[]>(bytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entity;
    }

    public void writeToOutputStream(HttpServletResponse response, String directoryPath) {
        Path directory = Paths.get(ROOT + directoryPath);

        if (!Files.isDirectory(directory)) {
            throw new CourseException("目录不存在或为空，无法下载");
        }
        try (OutputStream os = response.getOutputStream();
             ZipOutputStream zipOut = new ZipOutputStream(os)) {
            ByteBuffer buffer = ByteBuffer.allocate(5 * 1024 * 100);
            Files.list(directory).forEach(p -> {
                try (FileChannel fileChannel = FileChannel.open(p, StandardOpenOption.READ)) {
                    zipOut.putNextEntry(new ZipEntry(p.getFileName().toString()));
                    int len;
                    while ((len = fileChannel.read(buffer)) != -1) {
                        buffer.flip();
                        zipOut.write(buffer.array(), 0, len);
                        buffer.clear();
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                    throw new CourseException("读取文件或创建压缩文件错误");
                }
            });
            zipOut.closeEntry();
        } catch (Exception e) {
            // TODO: handle exception
            throw new CourseException("读取文件或创建压缩文件时错误");
        }
    }
}
