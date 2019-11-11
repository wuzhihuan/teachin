package com.dizhejiang.teachin.Service.impl;

import com.dizhejiang.teachin.Service.VitaeService;
import com.dizhejiang.teachin.common.AgeUlit;
import com.dizhejiang.teachin.common.DateUtil;
import com.dizhejiang.teachin.mapper.ItemExperienceMapper;
import com.dizhejiang.teachin.mapper.VitaeMapper;
import com.dizhejiang.teachin.mapper.WorkExperienceMapper;
import com.dizhejiang.teachin.model.ItemExperience;
import com.dizhejiang.teachin.model.Vitae;
import com.dizhejiang.teachin.model.WorkExperience;
import com.dizhejiang.teachin.vo.ResponseVo;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.Version;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author wuqi
 * @Date 2019/10/22
 */
@Service
public class VitaeServiceImpl implements VitaeService {
    @Autowired
    private VitaeMapper vitaeMapper;
    @Autowired
    private WorkExperienceMapper workExperienceMapper;
    @Autowired
    private ItemExperienceMapper itemExperienceMapper;

    /**
     * 保存我的简历
     * @param vitae
     * @param userId
     * @return
     */
    @Override
    public ResponseVo saveMyResumeInfo(Vitae vitae, Integer userId) {
        //Vitae vitae1 = vitaeMapper.selectVitaeByUserId(userId);
        if(userId==null){
            return ResponseVo.error("登录超时，或者未登录");
        }
        /*if(StringUtils.isEmpty(vitae.getSex())){
            return ResponseVo.error("性别不能为空");
        }*/
        if(StringUtils.isEmpty(vitae.getBirthday())){
            return ResponseVo.error("生日不能为空");
        }
        if(StringUtils.isEmpty(vitae.getName())){
            return ResponseVo.error("姓名不能为空");
        }
        if(StringUtils.isEmpty(vitae.getEducation())){
            return ResponseVo.error("最高学历不能为空");
        }
        if(StringUtils.isEmpty(vitae.getDiscipline())){
            return ResponseVo.error("专业不能为空");
        }
        if(vitae.getSchoolId()==null){
            return ResponseVo.error("学校id不能为空");
        }
        if(StringUtils.isEmpty(vitae.getSchoolName())){
            return ResponseVo.error("学校不能为空");
        }
        if(StringUtils.isEmpty(vitae.getPhone())){
            return ResponseVo.error("手机号码不能为空");
        }
        if(StringUtils.isEmpty(vitae.getEnterDate())){
            return ResponseVo.error("入学时间不能为空");
        }
        if(StringUtils.isEmpty(vitae.getLeaveDate())){
            return ResponseVo.error("毕业时间不能为空");
        }
      /*  if(StringUtils.isEmpty(vitae.getDetail())){
            return ResponseVo.error("个人描述不能为空");
        }*/
        Integer i=vitae.getEnterDate().compareTo(vitae.getLeaveDate());
        if(i>0){
            return ResponseVo.error("开始时间不能大于结束时间");
        }
        if(vitae.getId()!=null){
            vitae.setUpdateTime(DateUtil.DateToString(new Date()));
            vitae.setStatus("01");
            vitaeMapper.update(vitae,userId);
            return ResponseVo.success("新增成功！");
        }else{
            vitae.setCreateTime(DateUtil.DateToString(new Date()));
            vitae.setStatus("01");
            vitaeMapper.save(vitae,userId);
            return ResponseVo.success("新增成功");
        }
    }

    /**
     * 我的简历
     * @param userId
     * @return
     */
    @Override
    public ResponseVo getMyResumeInfo(Integer userId) {
        //我的简历
        Vitae vitae = vitaeMapper.selectVitaeByUserId(userId);
        if(vitae==null){
           return ResponseVo.error("没有简历");
        }
        SimpleDateFormat sdf  = new SimpleDateFormat("yyyy-MM");
        Date dd = null;
        try {
          dd =   sdf.parse(vitae.getBirthday());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Integer age=0;
        //计算年龄
        try {
          age =   AgeUlit.getAgeByBirth(dd);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        vitae.setAge(age);
        //工作经历
        List<WorkExperience> workExperienceList = workExperienceMapper.selectWorkExperienceListByUserId(userId);
        //项目经历
        List<ItemExperience> itemExperienceList = itemExperienceMapper.selectItemExperienceListByUserId(userId);
        Map<String,Object> map = new HashMap<>();
        map.put("vitae",vitae);
        map.put("workExperienceList",workExperienceList);
        map.put("itemExperienceList",itemExperienceList);
        return ResponseVo.success(map);
    }

    @Override
    public ResponseVo getWord() {


        Map<String,Object> dataMap = new HashMap<String, Object>();
        try {
           //===============个人信息=========
           //姓名
            dataMap.put("name","张飒");
           //性别
            dataMap.put("sex","男");
            //手机号
            dataMap.put("phone","13044912398");
            //年龄
            dataMap.put("age","25");
            //邮箱
            dataMap.put("email","98909888@qq.com");
            //教育程度
            dataMap.put("education","本科");
            //所在地
            dataMap.put("address","浙江省杭州市滨江区阿里中心2号楼");

            //=================教育经历===============================
            //学校名称
            dataMap.put("school_name","浙江大学");
            //入学时间
            dataMap.put("enter_date","2012-09");
            //毕业时间
            dataMap.put("leave_date","2016-06");
            //专业
            dataMap.put("discipline","计算机");
            dataMap.put("education","本科");

            //============工作经历==============================
            //入职时间
            dataMap.put("work_enter","2016-08");
            //离职时间
            dataMap.put("work_end","2019-08");
            //公司名称
            dataMap.put("company_name","杭州阿里巴巴集团有限公司");
            //职务
            dataMap.put("position","java开发");
            //描述
            dataMap.put("detail","java开发工程师");

            //==============项目经历======================
            //项目开始时间
            dataMap.put("project_start","2016-08");
            //项目结束时间
            dataMap.put("project_end","2019-08");
            //项目名称
            dataMap.put("project_name","支付宝");
            //职责
            dataMap.put("duty","支付宝支付功能");
            //项目描述
            dataMap.put("description","支付宝是用来支付的");
            //自我评价
            dataMap.put("self","我感觉我是可以的。。。");

            //Configuration 用于读取ftl文件
            Configuration configuration = new Configuration();
            configuration.setDefaultEncoding("utf-8");

            /**
             * 以下是两种指定ftl文件所在目录路径的方式，注意这两种方式都是
             * 指定ftl文件所在目录的路径，而不是ftl文件的路径
             */
            //指定路径的第一种方式（根据某个类的相对路径指定）
//                configuration.setClassForTemplateLoading(this.getClass(), "");

            //指定路径的第二种方式，我的路径是C：/a.ftl   C:\work\git_code
            configuration.setDirectoryForTemplateLoading(new File("c:/work/git_code"));

            //输出文档路径及名称
            File outFile = new File("C:/work/log4j/个人简历.doc");

            //以utf-8的编码读取ftl文件
            Template template = configuration.getTemplate("tt.ftl", "utf-8");
            Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"), 10240);
            template.process(dataMap, out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseVo.success("导出简历成功");

    }
}
