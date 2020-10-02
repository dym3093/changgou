package com.dayton.changgou.util;

import com.dayton.changgou.file.FastDFSFile;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.springframework.core.io.ClassPathResource;

import java.io.*;

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
	 * @param dfsFile 上传的文件信息
	 * @return void
	 * @author Martin Deng
	 * @since 2020/10/1 23:24
	 */
	public static String[] upload(FastDFSFile dfsFile) throws IOException, MyException {
		// 附加参数
		NameValuePair[] metaList = new NameValuePair[1];
		metaList[0] = new NameValuePair(FastDFSFile.Fields.author,
				dfsFile.getAuthor());
		// 通过TrackerClient访问TrackerServer，获取连接信息
		TrackerServer trackerServer = getTrackerServer();
		// 通过TrackerServer的链接信息可以获取Storage的链接信息，创建StorageClient对象存储Storage的链接信息
		StorageClient storageClient = getStorageClient(trackerServer);
		// 通过StorageClient访问Storage，实现文件上传，并且获取文件上传后的存储信息
		/* 1、上传文件的字节数组
		 * 2、文件的扩展名 jpg
		 * 3、附加信息
		 * 上传后返回的数组信息：
		 * 1、 uploads[0]: 文件上传所存储的Storage的组名
		 * 2、 uploads[1]: 文件存储到Storage上的文件名
		 */
		return storageClient.upload_file(dfsFile.getContent(),
				dfsFile.getExt(), metaList);
	}

	/**
	 * 获取文件信息
	 * @param groupName	        文件组名 group1
	 * @param remoteFileName    文件存储的路径名
	 * @return FileInfo
	 * @author Martin Deng
	 * @since 2020/10/2 11:24
	 */
	public static FileInfo getFile(String groupName, String remoteFileName) throws
			IOException, MyException {
		// 通过TrackerClient获取TrackerServer的链接对象
		TrackerServer trackerServer = getTrackerServer();
		// 通过TrackerServer获取Storage信息，创建Storage对象，存储Storage信息
		StorageClient storageClient = getStorageClient(trackerServer);
		// 获取文件信息
		return storageClient.get_file_info(groupName, remoteFileName);
	}

	/**
	 * 下载文件
	 * @param groupName	        文件组名 group1
	 * @param remoteFileName    文件存储的路径名
	 * @return InputStream      文件输入流
	 * @author Martin Deng
	 * @since 2020/10/2 11:43
	 */
	public static InputStream download(String groupName, String remoteFileName)
			throws IOException, MyException {
		// 通过TrackerClient获取TrackerServer的链接对象
		TrackerServer trackerServer = getTrackerServer();
		// 通过TrackerServer获取Storage信息，创建Storage对象，存储Storage信息
		StorageClient storageClient = getStorageClient(trackerServer);
		// 下载文件
		byte[] buffer = storageClient.download_file(groupName, remoteFileName);
		return new ByteArrayInputStream(buffer);
	}

	/**
	 * 删除
	 * @param groupName	        文件组名 group1
	 * @param remoteFileName    文件存储的路径名
	 * @return int              0: success，其他值: failure(失败码)
	 * @author Martin Deng
	 * @since 2020/10/2 14:59
	 */
	public static int delete(String groupName, String remoteFileName) throws IOException, MyException {
		TrackerServer trackerServer = getTrackerServer();
		// 通过TrackerServer获取Storage信息，创建Storage对象，存储Storage信息
		StorageClient storageClient = getStorageClient(trackerServer);
		return storageClient.delete_file(groupName, remoteFileName);
	}

	/**
	 * 获取 Storage 的信息
	 * @return org.csource.fastdfs.StorageServer
	 * @author Martin Deng
	 * @since 2020/10/2 15:04
	 */
	public static StorageServer getStorage() throws IOException {
		// 创建一个TrackerClient对象，通过TrackerClient对象访问TrackerServer
		TrackerClient trackerClient = new TrackerClient();
		// 通过TrackerClient获取TrackerServer的链接对象
		TrackerServer trackerServer = trackerClient.getConnection();
		return trackerClient.getStoreStorage(trackerServer);
	}

	/**
	 * 获取 Tracker 信息
	 * @return java.lang.String
	 * @author Martin Deng
	 * @since 2020/10/2 15:24
	 */
	public static String getTrackerInfo() throws IOException {
		TrackerServer trackerServer = getTrackerServer();
		// 获取Tracker的IP，HTTP端口
		String ip = trackerServer.getInetSocketAddress().getHostString();
		int port = ClientGlobal.getG_tracker_http_port();
		return "http://" + ip + ":" + port;
	}

	/**
	 * 获取Storage的IP和端口号信息
	 * @param groupName	        文件组名 group1
	 * @param remoteFileName    文件存储的路径名
	 * @return org.csource.fastdfs.ServerInfo[]
	 * @author Martin Deng
	 * @since 2020/10/2 15:13
	 */
	public static ServerInfo[] getServerInfo(String groupName, String remoteFileName) throws IOException {
		// 创建一个TrackerClient对象，通过TrackerClient对象访问TrackerServer
		TrackerClient trackerClient = new TrackerClient();
		// 通过TrackerClient获取TrackerServer的链接对象
		TrackerServer trackerServer = trackerClient.getConnection();
		// 获取Storage的IP和端口信息
		return trackerClient.getFetchStorages(trackerServer, groupName, remoteFileName);
	}

	/**
	 * 获取 Tracker
	 * @return org.csource.fastdfs.TrackerServer
	 * @author Martin Deng
	 * @since 2020/10/2 15:29
	 */
	public static TrackerServer getTrackerServer() throws IOException {
		// 创建一个TrackerClient对象，通过TrackerClient对象访问TrackerServer
		TrackerClient trackerClient = new TrackerClient();
		// 通过TrackerClient获取TrackerServer的链接对象
		return trackerClient.getConnection();
	}

	/**
	 * 获取 StorageClient
	 * @return void
	 * @author Martin Deng
	 * @since 2020/10/2 15:32
	 */
	public static StorageClient getStorageClient(TrackerServer trackerServer) throws IOException {
		// 通过TrackerServer获取Storage信息，创建Storage对象，存储Storage信息
		return new StorageClient(trackerServer, null);
	}

	/**
	 * 下载文件测试
	 * @return void
	 * @author Martin Deng
	 * @since 2020/10/2 11:51
	 */
	public static void testDownload() throws IOException, MyException {
		InputStream is = FastDFSUtil.download("", "");
		// 将文件写入本地磁盘
		FileOutputStream fos = new FileOutputStream("E:/test.jpg");
		// 定义缓冲区
		byte[] buffer = new byte[1024];
		while (is.read(buffer) != -1){
			fos.write(buffer);
		}
		fos.flush();
		fos.close();
		is.close();
	}

	public static void main(String[] args) {

	}
}
