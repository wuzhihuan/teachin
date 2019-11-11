package com.dizhejiang.teachin.controller;


import com.dizhejiang.teachin.common.DateUtil;
import com.dizhejiang.teachin.common.DateUtils;
import com.dizhejiang.teachin.common.FileUtils;
import com.dizhejiang.teachin.common.QiniuUtil;
import com.dizhejiang.teachin.config.ResponseException;
import com.dizhejiang.teachin.vo.ResponseVo;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/api/upload")
public class UploadController extends BaseController {
	private static Logger log = LoggerFactory.getLogger(UploadController.class);

	@Value("${qiniu.picUrl}")
	private String picUrl;

	@ResponseBody
	@RequestMapping(value = "/idcard", method = { RequestMethod.POST, RequestMethod.GET }, consumes = "multipart/form-data")
		public ResponseVo uploadIdCard(@RequestParam(required = false) MultipartFile upfile) throws Exception, IOException {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String picUrlIdCard = picUrl + "/upload/Idcard/";
		if (null == upfile) {
			throw new ResponseException("上传图片不能为空!");
		}
		// 大于10M
		if (upfile.getSize() >= 5 * 1024 * 1024) {
			throw new ResponseException("单张图片大于5M");
		}
		// 保存图片到
		String name = UUID.randomUUID().toString().replaceAll("-", "");
		name = DateUtils.getCurrentTimeSS();
		// jpg
		String ext = FilenameUtils.getExtension(upfile.getOriginalFilename());
		if (!FileUtils.allowedExtensionSet().contains(ext)) {
			throw new ResponseException("此文件后缀为" + ext + ",请更换图片后缀或者联系客服!");
		}
		// upfile.transferTo(new File("D:\\opt\\file\\upload\\Idcard\\" + name +"." + ext));
		// 进行压缩 大于3m 进行压缩
		String fileName = "idcard" + name + "." + ext;
		// FileUtils.createFileLocal(fileName, upfile.getBytes());

		String picPath = QiniuUtil.uploadStream(upfile.getInputStream(), fileName);
		resMap.put("picPath", picPath);

		return ResponseVo.success(resMap);
	}

	@ResponseBody
	@RequestMapping(value = "/avatars", method = { RequestMethod.POST, RequestMethod.GET }, consumes = "multipart/form-data")
	public ResponseVo uploadAvatars(@RequestParam(value = "upfile",required = false) MultipartFile upfile) throws Exception, IOException {
		Map<String, Object> resMap = new HashMap<String, Object>();

		String picUrlAvatars = picUrl + "/upload/avatars/";
		if (null == upfile) {
			throw new ResponseException("上传图片不能为空!");
		}
		// 大于10M
		if (upfile.getSize() >= 5 * 1024 * 1024) {
			throw new ResponseException("单张图片大于5M");
		}
		// 保存图片到
		String name = UUID.randomUUID().toString().replaceAll("-", "");
		name = DateUtils.getCurrentTimeSS();
		// jpg
		String ext = FilenameUtils.getExtension(upfile.getOriginalFilename());
		if (!FileUtils.allowedExtensionSet().contains(ext)) {
			throw new ResponseException("此文件后缀为" + ext + ",请更换图片后缀或者联系客服!");
		}

		// String fileName = picUrlAvatars + DateUtil.getCurrentYearMonth() + "/" + name + "." +
		// ext;
		// FileUtils.createFileLocal(fileName, upfile.getBytes());

		String fName = "avatars" + name + "." + ext;
		String picPath = QiniuUtil.uploadStream(upfile.getInputStream(),fName);

		resMap.put("picPath", picPath);
		System.out.println(picPath+"---------------");
		return ResponseVo.success(resMap);
	}

	@ResponseBody
	@RequestMapping(value = "/uploadImages", method = { RequestMethod.POST, RequestMethod.GET }, consumes = "multipart/form-data")
	public ResponseVo uploadImages(@RequestParam(value = "upfile",required = false) MultipartFile[] upfile) throws Exception, IOException {
		Map<String, Object> resMap = new HashMap<String, Object>();
		String picUrlAvatars = picUrl + "/upload/avatars/";
		List<String> picPaths =  new ArrayList<>();
		for(MultipartFile u : upfile){
			if (null == u) {
				throw new ResponseException("上传图片不能为空!");
			}
			// 大于10M
			if (u.getSize() >= 5 * 1024 * 1024) {
				throw new ResponseException("单张图片大于5M");
			}
			// 保存图片到
			String name = UUID.randomUUID().toString().replaceAll("-", "");
			name = DateUtils.getCurrentTimeSS();
			// jpg
			String ext = FilenameUtils.getExtension(u.getOriginalFilename());
			if (!FileUtils.allowedExtensionSet().contains(ext)) {
				throw new ResponseException("此文件后缀为" + ext + ",请更换图片后缀或者联系客服!");
			}

			// String fileName = picUrlAvatars + DateUtil.getCurrentYearMonth() + "/" + name + "." +
			// ext;
			// FileUtils.createFileLocal(fileName, upfile.getBytes());

			String fName = "avatars" + name + "." + ext;
			String picPath = QiniuUtil.uploadStream(u.getInputStream(), fName);

			picPaths.add(picPath);
		}
		resMap.put("picPath", picPaths);
		return ResponseVo.success(resMap);
	}

	@ResponseBody
	@RequestMapping(value = "/postPic", method = { RequestMethod.POST, RequestMethod.GET })
	public ResponseVo uploadPostPic(@RequestParam(value = "upfile", required = false) MultipartFile upfile) throws Exception, IOException { ;
		Map<String, Object> resMap = new HashMap<String, Object>();
		String picUrlPostPic = picUrl + "/upload/postPic/";
		log.info("图片进入接口!++++++++++++++++++++++");
		// 保存图片到
		if (null == upfile) {
			throw new ResponseException("上传图片不能为空!");
		}
		// 大于10M
		if (upfile.getSize() >= 5 * 1024 * 1024) {
			throw new ResponseException("单张图片大于5M");
		}
		String name = UUID.randomUUID().toString().replaceAll("-", "");
		name = DateUtils.getCurrentTimeSS();
		// jpg
		String ext = FilenameUtils.getExtension(upfile.getOriginalFilename());
		if (!FileUtils.allowedExtensionSet().contains(ext)) {
			throw new ResponseException("此文件后缀为" + ext + ",请更换图片后缀或者联系客服!");
		}

		// String fileName = picUrlPostPic + DateUtil.getCurrentYearMonth() + "/" + name + "." +
		// ext;
		// FileUtils.createFileLocal(fileName, upfile.getBytes());

		String fName = "posts" + name + "." + ext;
		String picPath = QiniuUtil.uploadStream(upfile.getInputStream(), fName);

		resMap.put("picPath", fName);
		log.info("图片存入成功!++++++++++++++++++++++");
		log.info(name + "." + ext);
		return ResponseVo.success(resMap);
	}

	@ResponseBody
	@RequestMapping(value = "/postPicsf", method = { RequestMethod.POST, RequestMethod.GET }, consumes = "multipart/form-data")
	public ResponseVo uploadPostPicsf(@RequestParam(value = "upfile", required = false) MultipartFile upfile, HttpServletRequest request,
                                      HttpServletResponse response) throws Exception, IOException {
		Map<String, Object> resMap = new HashMap<String, Object>();
		response.setContentType("text/html;charset=gbk");
		log.info("postPicsf图片进入接口!++++++++++++++++++++++");
		// 保存图片到
		String picUrlPostPicsf = picUrl + "/upload/postPic/";
		if (null == upfile) {
			throw new ResponseException("上传图片不能为空!");
		}
		// 大于10M
		if (upfile.getSize() >= 5 * 1024 * 1024) {
			throw new ResponseException("单张图片大于5M");
		}
		String name = UUID.randomUUID().toString().replaceAll("-", "");
		name = DateUtils.getCurrentTimeSS();
		// jpg
		String ext = FilenameUtils.getExtension(upfile.getOriginalFilename());
		if (!FileUtils.allowedExtensionSet().contains(ext)) {
			throw new ResponseException("此文件后缀为" + ext + ",请更换图片后缀或者联系客服!");
		}

		String fName = "posts" + name + "." + ext;
		String picPath = QiniuUtil.uploadStream(upfile.getInputStream(), fName);

		resMap.put("file_path", picPath);
		resMap.put("success", "true");
		// String file_Name = ipPicUrl + "/postPic/" + DateUtil.getCurrentYearMonth() + "/" + name +
		// "." + ext;
		log.info("postPicsf图片存入成功!++++++++++++++++++++++");
		resMap.put("msg","{\"success\":\"" + true + "\",\"file_path\":\"" + picPath + "\"}");
		return ResponseVo.success(resMap);
	}

	@ResponseBody
	@RequestMapping(value = "/authentication", method = { RequestMethod.POST, RequestMethod.GET }, consumes = "multipart/form-data")
	public ResponseVo uploadAuthentication(@RequestParam(required = false) MultipartFile upfile) throws Exception, IOException {
		Map<String, Object> resMap = new HashMap<String, Object>();
		log.info("authentication图片进入接口!++++++++++++++++++++++");

		String picUrlAuthentication = picUrl + "/upload/authentication/";
		// 保存图片到
		// 保存图片到
		if (null == upfile) {
			throw new ResponseException("上传图片不能为空!");
		}
		// 大于10M
		if (upfile.getSize() >= 5 * 1024 * 1024) {
			throw new ResponseException("单张图片大于5M");
		}
		String name = UUID.randomUUID().toString().replaceAll("-", "");
		name = DateUtils.getCurrentTimeSS();
		// jpg
		String ext = FilenameUtils.getExtension(upfile.getOriginalFilename());
		if (!FileUtils.allowedExtensionSet().contains(ext)) {
			throw new ResponseException("此文件后缀为" + ext + ",请更换图片后缀或者联系客服!");
		}

		String fName = "auth" + name + "." + ext;
		String picPath = QiniuUtil.uploadStream(upfile.getInputStream(), fName);

		log.info("authentication +++++++图片存入成功!++++++++++++++++++++++");
		log.info(name + "." + ext);
		resMap.put("picPath", picPath);
		return ResponseVo.success(resMap);
	}
}
