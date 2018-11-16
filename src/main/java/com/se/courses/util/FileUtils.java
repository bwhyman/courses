package com.se.courses.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.se.courses.exception.CourseException;
import org.springframework.stereotype.Component;

public class FileUtils {
	private static String ROOT = setUploadDirectory();
	
	public static String getFileName(String expName, String userName, String userNumber, String extension) {
		String name = expName + "-" + userName + "-" + userNumber + "." + extension;
		return name;
	} 
	
	public static String getCourseDir(long courseId, String courseName) {
		String courseDirectory = courseId + "-" + courseName;
		return courseDirectory;
	}
	
	public static String getExpDir( long expId, String expName) {
		String expDirectory = expId + "-" + expName;
		return expDirectory;
	}
	
	public static ResponseEntity<byte[]> getFile(String courseDir, String expDir, String expFileName) {
		ResponseEntity<byte[]> entity = null;
		Path path = Paths.get(ROOT + courseDir + "/" + expDir + "/" + expFileName);
		if (Files.notExists(path)) {
			throw new CourseException("文件不存在！");
		}
		try {
			entity = toResponseEntity(expFileName, Files.readAllBytes(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new CourseException("文件下载失败；" + e.getMessage());
		}
		return entity;
	}
	
	public static void createExpFile(String courseDir, String expDir, String fileName, byte[] bytes) {
		String dir = courseDir + "/" + expDir;
		Path directoryP = Paths.get(ROOT + dir);
		if (Files.notExists(directoryP)) {
			try {
				Files.createDirectories(directoryP);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				throw new CourseException("创建文件夹时错误；" + e.getMessage());
			}
		}
		Path fileP = Paths.get(directoryP + "/" + fileName);
		try {
			Files.write(fileP, bytes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new CourseException("文件保存至本地时失败；" + e.getMessage());
		}
	}

	/**
	 * 删除目录，及目录下的所有文件
	 * 
	 * @param directory
	 */
	public static void deleteDirectory(String directory) {
		try {
			Path path = Paths.get(ROOT + directory);
			if (Files.notExists(path)) {
				throw new CourseException("文件目录不存在！");
			}
			org.apache.commons.io.FileUtils.deleteDirectory(path.toFile());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new CourseException("文件删除错误");
		}
	}

	/**
	 * 基于文件目录，文件名称，删除文件
	 * 
	 * @param directory
	 * @param FileName
	 */
	public static void deleteFileTaskFile(String directory, String FileName) {
		File file = new File(ROOT + directory + "/" + FileName);
		if (file.exists() && file.isFile()) {
			file.delete();
		} else {
			throw new CourseException("文件已不存在");
		}
	}

	/**
	 * 获取工程upload绝对路径地址
	 * 
	 * @return
	 */
	private static String setUploadDirectory() {
		String webapp = System.getProperty("COURSES.root");
		String uploadDirectory = webapp + "/WEB-INF/jsp/upload/";
		Path dPath = Paths.get(uploadDirectory);
		if (Files.notExists(dPath)) {
			try {
				Files.createDirectories(dPath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				throw new CourseException("初始化上传文件目录错误！");
			}
		}
		return uploadDirectory;
	}


	/**
	 * 全局File封装为ResponseEntity<byte[]>，抽象下载实现
	 * 
	 * @param file
	 * @return
	 */
	public static ResponseEntity<byte[]> toResponseEntity(File file) {
		ResponseEntity<byte[]> entity = null;
		try {
			String fileName = URLEncoder.encode(file.getName(), "UTF-8");
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.setContentDispositionFormData("attachment", fileName);
			entity = new ResponseEntity<byte[]>(org.apache.commons.io.FileUtils.readFileToByteArray(file), headers, HttpStatus.OK);
			return entity;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new CourseException("文件加载失败；" + e.getMessage());
		}
	}

	/**
	 * 全局基于文件名称封装为ResponseEntity<byte[]>，抽象下载实现
	 * 
	 * @param fileName
	 * @param datas
	 * @return
	 */
	public static ResponseEntity<byte[]> toResponseEntity(String fileName, byte[] datas) {
		ResponseEntity<byte[]> entity = null;
		try {
			fileName = URLEncoder.encode(fileName, "UTF-8");
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.setContentDispositionFormData("attachment", fileName);
			entity = new ResponseEntity<byte[]>(datas, headers, HttpStatus.OK);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			throw new CourseException("文件下载失败；" + e.getMessage());
		}
		return entity;
	}
	
	/**
	 * 20-Web开发技术.zip<br>
	 * 基于文件任务文件夹压缩全部文件<br>
	 * 返回压缩文件字节数组
	 * 
	 * @param directoryPath
	 * @return
	 */
	@Deprecated
	public static byte[] zipDirectory(String directoryPath) {
		byte[] datas = null;
		File directory = new File(ROOT + directoryPath);
		File[] files = directory.listFiles();
		if (files == null || files.length == 0) {
			throw new CourseException("目录为空，无法下载");
		}
		try (ByteArrayOutputStream os = new ByteArrayOutputStream();
				ZipOutputStream zipOut = new ZipOutputStream(os)) {	
			for (int i = 0; i < files.length; ++i) {
				try(InputStream input = new FileInputStream(files[i])) {
					zipOut.putNextEntry(new ZipEntry(files[i].getName()));
					int temp = 0;
					while ((temp = input.read()) != -1) {
						zipOut.write(temp);
					}
				} 
			}
			zipOut.closeEntry();
			datas = os.toByteArray();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new CourseException("压缩文件时错误！");
		}
		return datas;

	}
	
	/**
	 * 
	 * @param response
	 * @param directoryPath
	 */
	public static void writeToOutputStream(HttpServletResponse response, String directoryPath) {
		Path directory = Paths.get(ROOT + directoryPath);
		
		if (!Files.isDirectory(directory)) {
			throw new CourseException("目录不存在或为空，无法下载");
		}
		try (OutputStream os =response.getOutputStream();
				ZipOutputStream zipOut = new ZipOutputStream(os)) {
			ByteBuffer buffer = ByteBuffer.allocate(1024 * 5);
			
			Files.list(directory).forEach(p -> {
				try (FileChannel fileChannel = FileChannel.open(p, StandardOpenOption.READ)) {	
					zipOut.putNextEntry(new ZipEntry(p.getFileName().toString()));
					while (fileChannel.read(buffer) != -1) {
						buffer.flip();
						zipOut.write(buffer.array());
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
