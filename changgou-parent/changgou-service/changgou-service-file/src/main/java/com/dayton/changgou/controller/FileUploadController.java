package com.dayton.changgou.controller;

import com.dayton.changgou.file.FastDFSFile;
import com.dayton.changgou.util.FastDFSUtil;
import entity.Result;
import entity.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.csource.common.MyException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 文件上传控制类
 * @author Martin Deng
 * @since 2020-10-01 23:42
 */
@Slf4j
@RestController
@RequestMapping("/upload")
@CrossOrigin
public class FileUploadController {


	/**
	 * 上传文件
	 * @param file	文件
	 * @return entity.Result
	 * @author Martin Deng
	 * @since 2020/10/2 10:54
	 */
	@PostMapping
	public Result upload(@RequestParam(value="file") MultipartFile file) throws IOException, MyException {
		// 调用FastDFSUtil工具类将文件上传到FastDFS中
		// 文件全名
		String fileName = file.getOriginalFilename();
		// 获取文件扩展名
		String fileExt = StringUtils.getFilenameExtension(fileName);
		FastDFSFile dfsFile = FastDFSFile.builder()
				.name(fileName)
				.content(file.getBytes())
				.ext(fileExt)
				.build();
		log.info("待上传的文件信息--> {}", dfsFile.toString());
		String[] uploads = FastDFSUtil.upload(dfsFile);
		// 拼接文件访问地址
		String url = FastDFSUtil.getTrackerInfo() + "/" + uploads[0] + "/" +
				uploads[1];
		log.info("上传文件的访问url--> {}", url);
		return new Result(true, StatusCode.OK, "上传成功", url);
	}


}
