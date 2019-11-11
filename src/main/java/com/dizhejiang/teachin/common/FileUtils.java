package com.dizhejiang.teachin.common;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class FileUtils {

	public static byte[] getBytesFromFile(File f) {
		if (f == null) {
			return null;
		}
		try {
			FileInputStream stream = new FileInputStream(f);
			ByteArrayOutputStream out = new ByteArrayOutputStream(1000);
			byte[] b = new byte[1000];
			int n;
			while ((n = stream.read(b)) != -1)
				out.write(b, 0, n);
			stream.close();
			out.close();
			return out.toByteArray();
		} catch (IOException e) {
		}
		return null;
	}

	public static File getFileFromBytes(byte[] b, String outputFile) {
		BufferedOutputStream stream = null;
		File file = null;
		try {
			file = new File(outputFile);
			FileOutputStream fstream = new FileOutputStream(file);
			stream = new BufferedOutputStream(fstream);
			stream.write(b);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		}
		return file;
	}

	/**
	 * 生成本地文件
	 *
	 * @param
	 * @param localPath
	 * @param blobSource
	 * @throws Exception
	 */
	public static void createFileLocal(String localPath, byte[] blobSource) throws Exception {

		File file = new File(localPath);
		if (file.exists()) {
			file.delete();
		}
		File dir = new File(file.getParent());
		if (!dir.exists())
			dir.mkdirs();
		if (file.createNewFile()) {
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(blobSource);
			fos.close();
		}
		// return file;
	}

	/**
	 * 仅生成本地文件夹
	 *
	 * @param localPath
	 * @throws Exception
	 */
	public static void createFileLocal(String localPath) throws Exception {

		File file = new File(localPath);
		if (file.exists()) {
			file.delete();
		}
		File dir = new File(file.getParent());
		if (!dir.exists())
			dir.mkdirs();

		// return file;
	}

	/**
	 * 根据文件名获取后缀，默认jpg
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getFileExtension(String fileName) {
		String result = "jpg";
		if (StringUtils.isBlank(fileName)) {
			return result;
		}
		int idx = fileName.lastIndexOf(".");
		// 文件后缀
		result = fileName.substring(idx + 1);
		return result;
	}

	public static Set<String> allowedExtensionSet() {
		Set<String> result = new HashSet<String>();
		result.add("jpg");
		result.add("jpeg");
		result.add("png");
		result.add("gif");
		result.add("JPG");
		result.add("JPEG");
		result.add("PNG");
		result.add("GIF");
		return result;

	}
	
	public static String createUploadFileNameQiuniu(String oriFileName, Integer itype) {
		// String suffix = oriFileName.substring(oriFileName.lastIndexOf("."));
		Date date = new Date();
		String typeString = "";
		if (itype == 1) {
			typeString = "avatars";
		} else if (itype == 2) {
			typeString = "projects";
		} else if (itype == 3) {
			typeString = "posts";
		} else if (itype == 4) {
			return oriFileName;
		}
		return typeString + date.getTime() + RandomUtil.produceNumber(6);
	}
}
