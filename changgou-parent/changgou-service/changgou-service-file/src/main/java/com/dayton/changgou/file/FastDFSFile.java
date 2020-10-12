package com.dayton.changgou.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.Tolerate;

import java.io.Serializable;

/**
 * 封装文件上传的信息<br/>
 * @author Martin Deng
 * @since 2020-10-01 23:06
 */
@Data
@Builder
@AllArgsConstructor
@FieldNameConstants
public class FastDFSFile implements Serializable{

	private static final long serialVersionUID = 8108590976524912375L;

	/** 文件名 */
	private String name;
	/** 文件内容 */
	private byte[] content;
	/** 文件扩展名 jpg png gif 之类（不包括点号）*/
	private String ext;
	/** 文件MD5摘要 */
	private String md5;
	/** 文件作者 */
	private String author;

	@Tolerate
	public FastDFSFile() {
	}

}
