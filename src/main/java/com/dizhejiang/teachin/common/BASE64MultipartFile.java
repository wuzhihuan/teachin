package com.dizhejiang.teachin.common;

import org.springframework.web.multipart.MultipartFile;

import java.io.*;

public class BASE64MultipartFile implements MultipartFile, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7751589701929532881L;
	private final byte[] imgContent;
	private final String header;

	public BASE64MultipartFile(byte[] imgContent, String header) {
		this.imgContent = imgContent;
		this.header = header.split(";")[0];
	}

	@Override
	public String getName() {
		// TODO - implementation depends on your requirements
		return System.currentTimeMillis() + Math.random() + "." + header.split("/")[1];
	}

	@Override
	public String getOriginalFilename() {
		// TODO - implementation depends on your requirements
		if (header.contains("/")) {
			SyseUtil.systemErrOutJson(System.currentTimeMillis() + (int) Math.random() * 10000 + "." + header.split("/")[1]);
			return System.currentTimeMillis() + (int) Math.random() * 10000 + "." + header.split("/")[1];
		} else {
			return System.currentTimeMillis() + (int) Math.random() * 10000 + "";
		}
	}

	@Override
	public String getContentType() {
		// TODO - implementation depends on your requirements
		return header.split(":")[1];
	}

	@Override
	public boolean isEmpty() {
		return imgContent == null || imgContent.length == 0;
	}

	@Override
	public long getSize() {
		return imgContent.length;
	}

	@Override
	public byte[] getBytes() throws IOException {
		return imgContent;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return new ByteArrayInputStream(imgContent);
	}

	@Override
	public void transferTo(File dest) throws IOException, IllegalStateException {
		new FileOutputStream(dest).write(imgContent);
	}

}
