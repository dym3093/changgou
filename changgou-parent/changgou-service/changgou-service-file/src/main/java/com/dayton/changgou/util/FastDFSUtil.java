package com.dayton.changgou.util;

import com.dayton.changgou.file.FastDFSFile;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;

/**
 * 文件上传工具类<br/>
 * @author Martin Deng
 * @since 2020-10-01 23:19
 */
public class FastDFSUtil {

	/**
	 * 加载Tracker信息
	 */
	static {
		String fileName = new ClassPathResource("fdfs_client.conf").getPath();
		try {
			ClientGlobal.init(fileName);
		} catch (IOException | MyException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 文件上传
	 * @param fastDFSFile 上传的文件信息
	 * @return void
	 * @author Martin Deng
	 * @since 2020/10/1 23:24
	 */
	public static void upload(FastDFSFile fastDFSFile) throws IOException, MyException {
		// 附加参数
		NameValuePair[] metaList = new NameValuePair[1];
		metaList[0] = new NameValuePair(FastDFSFile.Fields.author,
				fastDFSFile.getAuthor());
		// 创建一个Tracker访问客户端对象TrackerClient
		TrackerClient trackerClient = new TrackerClient();
		// 通过TrackerClient访问TrackerServer，获取连接信息
		TrackerServer trackerServer = trackerClient.getConnection();
		// 通过TrackerServer的链接信息可以获取Storage的链接信息，创建StorageClient对象存储Storage的链接信息
		StorageClient storageClient = new StorageClient(trackerServer,null);
		// 通过StorageClient访问Storage，实现文件上传，并且获取文件上传后的存储信息
		/**
		 * 1、上传文件的字节数组
		 * 2、文件的扩展名 jpg
		 * 3、附加信息
		 */
		storageClient.upload_file(fastDFSFile.getContent(), fastDFSFile
				.getExt(), metaList);
	}

}
