package com.dizhejiang.teachin.Service.impl;

import com.alibaba.fastjson.JSON;
import com.dizhejiang.teachin.Service.CollectService;
import com.dizhejiang.teachin.Service.TeachinService;
import com.dizhejiang.teachin.common.*;
import com.dizhejiang.teachin.config.ResponseException;
import com.dizhejiang.teachin.dto.PageInputDto;
import com.dizhejiang.teachin.dto.TeachinDto;
import com.dizhejiang.teachin.enums.ResponseEnum;
import com.dizhejiang.teachin.mapper.*;
import com.dizhejiang.teachin.model.*;
import com.dizhejiang.teachin.vo.PageModel;
import com.dizhejiang.teachin.vo.ResponseVo;
import com.dizhejiang.teachin.vo.TeachinVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

/**
 * @Author wuqi
 * @Date 2019/10/21
 */
@Service
public class TeachinServiceImpl implements TeachinService {
    @Autowired
    private TeachinMapper teachinMapper;
    @Autowired
    private PositionMapper positionMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private EnrollMapper enrollMapper;
    @Autowired
    private TeachinDataMapper teachinDataMapper;
    @Autowired
    private CollectMapper collectMapper;
    @Autowired
    private CompanyAttestationMapper companyAttestationMapper;
    @Autowired
    private ActionLogMapper actionLogMapper;
    @Autowired
    private RedisUtil redisUtil;


    /**
     * 宣讲会列表
     * @param dto
     * @return
     */
    @Override
    public ResponseVo getToastmastersList(TeachinDto dto, ServletRequest request)  {

        //dateType 全部：0 // 今天：1 //明天：2   // 三天内：3  //一周内：4  //一个月内：5  //三个月内：6 //已结束：7
        String  selectDate="";
        Calendar c = Calendar.getInstance();
        int day=c.get(Calendar.DATE);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //判断参数不为空
        if(dto.getDatePhase()!=null){
            if(1==dto.getDatePhase()){ //获取今天时间
                selectDate=  DateUtil.DateToString(new Date());
                c.set(Calendar.DATE,day+1);
                dto.setNextDate( sdf.format(c.getTime()).substring(0,10) +" 00:00:00");
            }
            if(2==dto.getDatePhase()){ //明天时间
                c.set(Calendar.DATE,day+1);
                selectDate=sdf.format(c.getTime()).substring(0,10) +" 00:00:00";
                Calendar c1 = Calendar.getInstance();
                int day1=c.get(Calendar.DATE);
                c1.set(Calendar.DATE,day1+1);
                dto.setNextDate( sdf.format(c1.getTime()).substring(0,10) +" 00:00:00");
            }
            if(3==dto.getDatePhase()){ //3天时间
                c.set(Calendar.DATE,day+3);
                  selectDate=sdf.format(c.getTime());
            }
            if(4==dto.getDatePhase()){ //7天时间
                c.set(Calendar.DATE,day+7);
                 selectDate=sdf.format(c.getTime());
            }
            if(5==dto.getDatePhase()){ //30天时间
                c.set(Calendar.DATE,day+30);
                  selectDate=sdf.format(c.getTime());
            }
            if(6==dto.getDatePhase()){ //30天时间
                c.set(Calendar.DATE,day+90);
                 selectDate=sdf.format(c.getTime());
            }
        }

        String nowDate =  DateUtil.DateToString(new Date());
        dto.setNowDate(nowDate);
        List<Teachin> teachinList = teachinMapper.getToastmastersList(dto,selectDate);
        //SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MMM-dd HH:mm:ss");
        //判断是否结束
        String currentDate = DateUtil.DateToString(new Date());
        Integer userId=null;
        HttpServletRequest req = (HttpServletRequest) request;
        String token = req.getHeader("token");
        if(token != null && redisUtil.exists(token)){
            Object json = redisUtil.get(token);
            String ss = (String) json;
            String str="";
            try {
                str =   URLDecoder.decode(ss, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            userId =Integer.parseInt(str);
        }


        for(int i = 0;i<teachinList.size();i++){
            //格式化
            if(!StringUtils.isEmpty(teachinList.get(i).getHappenTime())){
                Integer f=teachinList.get(i).getHappenTime().compareTo(currentDate);
                if(f>0){
                    teachinList.get(i).setIsEnd("02"); //未结束
                    teachinList.get(i).setOrderEnd(2);
                    Teachin teachin = new Teachin();
                    teachin.setId(teachinList.get(i).getId());
                    teachin.setIsEnd("02");
                    //teachin.setOrderEnd(2);
                    teachinMapper.update(teachin);
                    //teachinList.add(teachin);
                }else {
                    teachinList.get(i).setIsEnd("01"); //已结束
                    teachinList.get(i).setOrderEnd(1);
                    Teachin teachin = new Teachin();
                    teachin.setId(teachinList.get(i).getId());
                    //teachin.setStatus("04");
                    teachin.setIsEnd("01");
                    //teachin.setOrderEnd(1);
                    teachinMapper.update(teachin);
                    //teachinList.add(teachin);
                }
            }

            //获取报名
            //是否已报名01报名，02未报名
            // private String isEnroll;

            if(userId!=null){
                Enroll enroll  = enrollMapper.selectEnrollByTeachIdAndUserId(teachinList.get(i).getId(),userId);
                if(enroll==null){
                    teachinList.get(i).setIsEnroll("02");//未报名
                }else{
                    teachinList.get(i).setIsEnroll(enroll.getStatus());//已报名
                }
            }else{
                teachinList.get(i).setIsEnroll("00");//已报名
            }
        }
       TeachinComparator comp=new TeachinComparator();
        //调用排序方法
        Collections.sort(teachinList,comp);

       // PageHelper.startPage(dto.getPageNum(),dto.getPageSize());
        List<Teachin> list= new ArrayList<>();
        //手动分页
        if(dto.getPageNum()-1*dto.getPageSize()>teachinList.size()){
            PageInfo pageInfo = new PageInfo<>(list);
            return ResponseVo.success(ClassCastUtil.pageInfoToPageOutputDto(pageInfo));
        }else {
            if(dto.getPageNum()*dto.getPageSize()<teachinList.size()){
                for(int i=(dto.getPageNum()-1)*dto.getPageSize();i<dto.getPageNum()*dto.getPageSize();i++){

                    list.add(teachinList.get(i));
                }
                PageInfo pageInfo = new PageInfo<>(list);
                return ResponseVo.success(ClassCastUtil.pageInfoToPageOutputDto(pageInfo));
            }else{
                for(int i=(dto.getPageNum()-1)*dto.getPageSize();i<teachinList.size();i++){

                    list.add(teachinList.get(i));
                }
                PageInfo pageInfo = new PageInfo<>(list);
                return ResponseVo.success(ClassCastUtil.pageInfoToPageOutputDto(pageInfo));
            }

            //return ResponseVo.success(list);
        }


       // PageInfo pageInfo = new PageInfo<>(teachinList);
        //return ResponseVo.success(ClassCastUtil.pageInfoToPageOutputDto(pageInfo));
    }

    /**
     * 详情
     * @param id
     * @return
     */
    @Override
    public ResponseVo getToastmastersDetail(Integer id,Integer userId) {
        TeachinData teachinData = teachinDataMapper.selectModelByTeachinId(id);
        if(teachinData==null){
            TeachinData teachinData1 = new TeachinData();
            teachinData1.setLookNum(1);
            teachinData1.setEnrollNum(0);
            teachinData1.setTeachinId(id);
            teachinData1.setFoocusNum(0);
            teachinData1.setLocalEnrollNum(0);
            teachinData1.setOtherEnrollNum(0);
            teachinData1.setStatus("01");
            teachinData1.setUpdateTime(DateUtil.DateToString(new Date()));
            int f= teachinDataMapper.save(teachinData1);
            if(f<1){
                return ResponseVo.success("保存宣讲会数据失败");
            }
            //保存
        }else{
                teachinData.setLookNum(teachinData.getLookNum()+1);
                //更新
            int f= teachinDataMapper.update(teachinData);
            if(f<1){
                return ResponseVo.success("更新宣讲会数据失败");
            }

        }

        //得到宣讲会详情
        //User user = userMapper.selectUserById(userId);
        Teachin teachin =  teachinMapper.getTeachinById(id);
        Collect col = collectMapper.selectCollectByTeachIdAndUserId(id,userId);
        if(col==null){
            teachin.setIsCollect("02");
        }else{
            teachin.setIsCollect(col.getStatus());
        }

       Enroll enroll= enrollMapper.selectEnrollByTeachIdAndUserId(id,userId);
        if(enroll==null){
            teachin.setIsEnroll("02");
        }else{
            teachin.setIsEnroll(enroll.getStatus());
        }

        CompanyAttestation companyAttestation = companyAttestationMapper.selectModelByUserId(userId);
        if(companyAttestation!=null){
            if(companyAttestation.getCredit().equals(teachin.getCredit())){
                teachin.setIsCancle("01");
            }
        }
        if(userId== teachin.getOpUserId()){
            teachin.setIsCancle("01");
        }else{
            teachin.setIsCancle("02");
        }
        //获取工作列表jobList
        List<Position> positionList = positionMapper.selectPositionByCompanyId(teachin.getCredit());
        //otherToastmasters,其他宣讲会（本公司 未结束 最近三场 非本场 的宣讲会）
        //参数本次宣讲会id,公司id
        List<Teachin> teachinList = teachinMapper.selectTeachinByOther(teachin.getId(),teachin.getCredit());
        Map<String ,Object> map = new HashMap<>();
        map.put("teachin" , teachin);
        map.put("jobList" ,positionList);
        map.put("otherToastmasters" ,teachinList);
        return ResponseVo.success(map);
    }

    /**
     * 获取我的发布的宣讲会详情
     * @param id
     * @param userId
     * @return
     */
    @Override
    public ResponseVo getMyToastmastersDetail(Integer id, Integer userId) {
        User user = userMapper.selectUserById(userId);
        //学校用户或者社群
        if("02".equals(user.getUserType()) || "04".equals(user.getUserType())){
            Map<String ,Object> map = new HashMap<>();
            Teachin teachin =  teachinMapper.getTeachinById(id);
            //逻辑处理是否可以取消，只有发布者和相关企业可以取消
            if(user.getCompanyId()==teachin.getCompanyId() || userId== teachin.getOpUserId()){
                teachin.setIsCancle("01");
            }else{
                teachin.setIsCancle("02");
            }
            TeachinData teachinData = teachinDataMapper.selectModelByTeachinId(id);
            map.put("teachin",teachin);
            map.put("teachinData",teachinData);
            return ResponseVo.success(map);
        }else{
            return ResponseVo.error("该账号不能有宣讲会！");
        }

    }

    /**
     * 获取我的宣讲会
     * @param userId
     * @return pageNum ,pageSize
     */
    @Override
    public ResponseVo getMyToastmastersList(TeachinDto dto,Integer userId) {
        User user = userMapper.selectUserById(userId);
        String currentDate = DateUtil.DateToString(new Date());
        List<TeachinVo>  teachinVoList = new ArrayList<>();
        List<TeachinVo>   listTeachin  = new ArrayList<>();
        List<Integer> idList  = new ArrayList<>();
        //标识是我发布的
        if(dto.getIsMy()==1){
            if(!"01".equals(user.getUserType())){
                PageHelper.startPage(dto.getPageNum(),dto.getPageSize());
                teachinVoList=teachinMapper.selectModelByUserId(userId);
            }
            PageInfo pageInfo = new PageInfo<>(teachinVoList);
            return ResponseVo.success(ClassCastUtil.pageInfoToPageOutputDto(pageInfo));
        }else{
            if("01".equals(user.getUserType())){
                teachinVoList=enrollMapper.selectEnrollByUserId(userId,dto.getHappenTime());

                for(int i = 0;i<teachinVoList.size();i++){
                    //格式化
                    if(!StringUtils.isEmpty(teachinVoList.get(i).getHappenTime())){
                        Integer f=teachinVoList.get(i).getHappenTime().compareTo(currentDate);
                        if(f>0){
                            teachinVoList.get(i).setIsEnd("02"); //未结束
                        }else {
                            teachinVoList.get(i).setIsEnd("01"); //已结束
                        }
                    }
                    //是否已报名01报名，02未报名
                    Enroll enroll =  enrollMapper.selectEnrollByTeachIdAndUserId(teachinVoList.get(i).getId(),userId);
                    if(enroll==null){
                        teachinVoList.get(i).setIsEnroll("02");
                    }else{
                        teachinVoList.get(i).setIsEnroll(enroll.getStatus());
                    }
                }
                PageInfo pageInfo = new PageInfo<>(teachinVoList);
                return ResponseVo.success(ClassCastUtil.pageInfoToPageOutputDto(pageInfo));
            }

            //学校用户,社群用户
            else if("02".equals(user.getUserType()) || "04".equals(user.getUserType())){
                dto.setUserId(userId);
                teachinVoList=teachinMapper.getTeachinBySchoolId(user.getSchoolId(),dto);
                if(teachinVoList.size()>0){
                    for(int i = 0;i<teachinVoList.size();i++){
                        idList.add(teachinVoList.get(i).getId());
                        //格式化
                        if(!StringUtils.isEmpty(teachinVoList.get(i).getHappenTime())){
                            Integer f=teachinVoList.get(i).getHappenTime().compareTo(currentDate);
                            if(f>0){
                                teachinVoList.get(i).setIsEnd("02"); //未结束
                            }else {
                                teachinVoList.get(i).setIsEnd("01"); //已结束
                            }
                        }
                        //是否已报名01报名，02未报名
                        Enroll enroll =  enrollMapper.selectEnrollByTeachIdAndUserId(teachinVoList.get(i).getId(),userId);
                        if(enroll==null){
                            teachinVoList.get(i).setIsEnroll("02");
                        }else{
                            teachinVoList.get(i).setIsEnroll(enroll.getStatus());
                        }
                    }
                }

                //====只按照happentime排序===通过id,再次请求服务器来排序==
                //-=============报名的排序=========================
                //为空说明是我的宣讲会，===日历
                if(!StringUtils.isEmpty(dto.getHappenTime())){
                    PageHelper.startPage(dto.getPageNum(),dto.getPageSize());
                    if(idList!=null && idList.size()>0){
                        listTeachin = teachinMapper.selectModelByIdList(idList);
                    }
                    if(listTeachin!=null){
                        if(listTeachin.size()>0){
                            for(int i=0;i<listTeachin.size();i++){
                                if(!StringUtils.isEmpty(listTeachin.get(i).getHappenTime())){
                                    Integer f=listTeachin.get(i).getHappenTime().compareTo(currentDate);
                                    if(f>0){
                                        listTeachin.get(i).setIsEnd("02"); //未结束
                                    }else {
                                        listTeachin.get(i).setIsEnd("01"); //已结束
                                    }
                                }
                                //是否已报名01报名，02未报名
                                Enroll enroll =  enrollMapper.selectEnrollByTeachIdAndUserId(listTeachin.get(i).getId(),userId);
                                if(enroll==null){
                                    listTeachin.get(i).setIsEnroll("02");
                                }else{
                                    listTeachin.get(i).setIsEnroll(enroll.getStatus());
                                }
                            }
                        }
                    }
                }else {
                    //我的宣讲会，排序先按照报名时间或者发布时间倒叙拍
                    //帅选出报名的和发布的数据，时间
                      List<TeachinVo> teachinList = teachinMapper.selectModelByUserId(userId);
                      List<Enroll> enrollList = enrollMapper.selectEnrollListByUserId(userId);
                      if(teachinList.size()>0 && teachinList!=null){
                          for(int j = 0;j<teachinList.size();j++){
                              //teachinids.add(teachinList.get(j).getId());
                              Teachin teachin = new Teachin();
                              teachin.setId(teachinList.get(j).getId());
                              if(StringUtils.isEmpty(teachinList.get(j).getCreateTime())){
                                  teachin.setOperateTime("2019-10-10 10:10:10");
                              }else{
                                  teachin.setOperateTime(teachinList.get(j).getCreateTime());
                              }
                              teachinMapper.update(teachin);
                          }
                      }
                     if(enrollList.size()>0){
                         for(int k=0;k<enrollList.size();k++){
                             //teachinids.add(enrollList.get(k).getTeachinId());
                             Teachin teachin = new Teachin();
                             teachin.setId(enrollList.get(k).getTeachinId());
                             if(StringUtils.isEmpty(enrollList.get(k).getCreateTime())){
                                 teachin.setOperateTime("2019-10-10 10:10:10");
                             }else{
                                 teachin.setOperateTime(enrollList.get(k).getCreateTime());
                             }
                             teachinMapper.update(teachin);
                         }
                     }
                     if(idList!=null){
                         if(idList.size()>0){
                             PageHelper.startPage(dto.getPageNum(),dto.getPageSize());
                             listTeachin = teachinMapper.selectMyModelByIdList(idList,userId);
                         }
                     }
                     if(listTeachin!=null){
                         if(listTeachin.size()>0){
                             for(int i=0;i<listTeachin.size();i++){
                                 if(!StringUtils.isEmpty(listTeachin.get(i).getHappenTime())){
                                     Integer f=listTeachin.get(i).getHappenTime().compareTo(currentDate);
                                     if(f>0){
                                         listTeachin.get(i).setIsEnd("02"); //未结束
                                     }else {
                                         listTeachin.get(i).setIsEnd("01"); //已结束
                                     }
                                 }
                                 //是否已报名01报名，02未报名
                                 Enroll enroll =  enrollMapper.selectEnrollByTeachIdAndUserId(listTeachin.get(i).getId(),userId);
                                 if(enroll==null){
                                     listTeachin.get(i).setIsEnroll("02");
                                 }else{
                                     listTeachin.get(i).setIsEnroll(enroll.getStatus());
                                 }
                             }
                         }
                     }
                }
                PageInfo pageInfo = new PageInfo<>(listTeachin);
                return ResponseVo.success(ClassCastUtil.pageInfoToPageOutputDto(pageInfo));

            }
            //企业用户,唯一性不要通过CompanyId判断，应该通过credit营业执照号判断
            else if("03".equals(user.getUserType())){
                dto.setUserId(userId);
                teachinVoList=teachinMapper.getTeachinByCompanyId(user.getCompanyId(),dto);
                if(teachinVoList.size()>0){
                    for(int i = 0;i<teachinVoList.size();i++){
                        idList.add(teachinVoList.get(i).getId());
                        //格式化
                        if(!StringUtils.isEmpty(teachinVoList.get(i).getHappenTime())){
                            Integer f=teachinVoList.get(i).getHappenTime().compareTo(currentDate);
                            if(f>0){
                                teachinVoList.get(i).setIsEnd("02"); //未结束
                            }else {
                                teachinVoList.get(i).setIsEnd("01"); //已结束
                            }
                        }
                        //是否已报名01报名，02未报名
                        Enroll enroll =  enrollMapper.selectEnrollByTeachIdAndUserId(teachinVoList.get(i).getId(),userId);
                        if(enroll==null){
                            teachinVoList.get(i).setIsEnroll("02");
                        }else{
                            teachinVoList.get(i).setIsEnroll(enroll.getStatus());
                        }
                    }
                }

                //====只按照happentime排序===通过id,再次请求服务器来排序==
                //-=============报名的排序=========================
                //为空说明是我的宣讲会，===日历
                if(!StringUtils.isEmpty(dto.getHappenTime())){
                    PageHelper.startPage(dto.getPageNum(),dto.getPageSize());
                    if(idList!=null ){
                        if(idList.size()>0){
                            listTeachin = teachinMapper.selectModelByIdList(idList);
                        }
                    }
                    if(listTeachin !=null){
                        if(listTeachin.size()>0){
                            for(int i=0;i<listTeachin.size();i++){
                                if(!StringUtils.isEmpty(listTeachin.get(i).getHappenTime())){
                                    Integer f=listTeachin.get(i).getHappenTime().compareTo(currentDate);
                                    if(f>0){
                                        listTeachin.get(i).setIsEnd("02"); //未结束
                                    }else {
                                        listTeachin.get(i).setIsEnd("01"); //已结束
                                    }
                                }
                                //是否已报名01报名，02未报名
                                Enroll enroll =  enrollMapper.selectEnrollByTeachIdAndUserId(listTeachin.get(i).getId(),userId);
                                if(enroll==null){
                                    listTeachin.get(i).setIsEnroll("02");
                                }else{
                                    listTeachin.get(i).setIsEnroll(enroll.getStatus());
                                }
                            }
                        }
                    }
                }else {
                    //我的宣讲会，排序先按照报名时间倒叙拍
                    List<TeachinVo> teachinList = teachinMapper.selectModelByUserId(userId);
                    List<Enroll> enrollList = enrollMapper.selectEnrollListByUserId(userId);
                    if(teachinList.size()>0){
                        for(int j = 0;j<teachinList.size();j++){
                            //teachinids.add(teachinList.get(j).getId());
                            Teachin teachin = new Teachin();
                            teachin.setId(teachinList.get(j).getId());
                            if(StringUtils.isEmpty(teachinList.get(j).getCreateTime())){
                                teachin.setOperateTime("2019-10-10 10:10:10");
                            }else{
                                teachin.setOperateTime(teachinList.get(j).getCreateTime());
                            }
                            teachinMapper.update(teachin);
                        }
                    }
                    if(enrollList.size()>0){
                        for(int k=0;k<enrollList.size();k++){
                            //teachinids.add(enrollList.get(k).getTeachinId());
                            Teachin teachin = new Teachin();
                            teachin.setId(enrollList.get(k).getTeachinId());
                            if(StringUtils.isEmpty(enrollList.get(k).getCreateTime())){
                                teachin.setOperateTime("2019-10-10 10:10:10");
                            }else{
                                teachin.setOperateTime(enrollList.get(k).getCreateTime());
                            }
                            teachinMapper.update(teachin);
                        }
                        PageHelper.startPage(dto.getPageNum(),dto.getPageSize());
                        if(idList.size()>0){
                            listTeachin = teachinMapper.selectMyModelByIdList(idList,userId);
                        }
                        if(listTeachin!=null){
                            if(listTeachin.size()>0){
                                for(int i=0;i<listTeachin.size();i++){
                                    if(!StringUtils.isEmpty(listTeachin.get(i).getHappenTime())){
                                        Integer f=listTeachin.get(i).getHappenTime().compareTo(currentDate);
                                        if(f>0){
                                            listTeachin.get(i).setIsEnd("02"); //未结束
                                        }else {
                                            listTeachin.get(i).setIsEnd("01"); //已结束
                                        }
                                    }
                                    //是否已报名01报名，02未报名
                                    Enroll enroll =  enrollMapper.selectEnrollByTeachIdAndUserId(listTeachin.get(i).getId(),userId);
                                    if(enroll==null){
                                        listTeachin.get(i).setIsEnroll("02");
                                    }else{
                                        listTeachin.get(i).setIsEnroll(enroll.getStatus());
                                    }
                                }
                            }
                        }

                    }
                    if(idList!=null){
                        if(idList.size()>0){
                            PageHelper.startPage(dto.getPageNum(),dto.getPageSize());
                            listTeachin = teachinMapper.selectMyModelByIdList(idList,userId);
                        }
                    }
                }
                PageInfo pageInfo = new PageInfo<>(listTeachin);
                return ResponseVo.success(ClassCastUtil.pageInfoToPageOutputDto(pageInfo));
            }else{
                return ResponseVo.error("没有该用户类型");
            }

        }
    }

    /**
     * 我的宣讲会日历,
     * 返回日期的号数
     * @param dto
     * @return
     */
    @Override
    public ResponseVo getMyMonthTeachin(TeachinDto dto) {
        User user = userMapper.selectUserById(dto.getUserId());
        //解析时间
        int year= Integer.parseInt(dto.getHappenTime().substring(0,4));
        String ss =dto.getHappenTime().substring(dto.getHappenTime().indexOf("-")+1);
        int month= Integer.parseInt(ss);
        dto.setStartTime(DateUtil.getFirstDayOfMonth(year,month));
        dto.setEndTime(DateUtil.getLastDayOfMonth(year,month));
        //普户01，校户02，企户03,学校社群用户04
        //普通用户
        List<TeachinVo>  teachinVoList = new ArrayList<>();
        List<String> list = new ArrayList<>();
        if("01".equals(user.getUserType())){
            teachinVoList=enrollMapper.selectEnrollByUserIdAndTime(dto);
            for(int i = 0;i<teachinVoList.size();i++){
                list.add(teachinVoList.get(i).getHappenTime().substring(8,10));
            }
            LinkedHashSet<String> hashSet =new LinkedHashSet<>(list);
            ArrayList<String> listWithoutDuplicates =new ArrayList<>(hashSet);

            return ResponseVo.success(listWithoutDuplicates);
        }
        //学校用户
        else if("02".equals(user.getUserType()) || "04".equals(user.getUserType())){
            teachinVoList=teachinMapper.getTeachinBySchoolId(user.getSchoolId(),dto);
            for(int i = 0;i<teachinVoList.size();i++){
                list.add(teachinVoList.get(i).getHappenTime().substring(8,10));
            }
            LinkedHashSet<String> hashSet =new LinkedHashSet<>(list);
            ArrayList<String> listWithoutDuplicates =new ArrayList<>(hashSet);

            return ResponseVo.success(listWithoutDuplicates);
        }
        else if("03".equals(user.getUserType())){
            teachinVoList=teachinMapper.getTeachinByCompanyId(user.getCompanyId(),dto);
            for(int i = 0;i<teachinVoList.size();i++){
                list.add(teachinVoList.get(i).getHappenTime().substring(8,10));
            }
            LinkedHashSet<String> hashSet =new LinkedHashSet<>(list);
            ArrayList<String> listWithoutDuplicates =new ArrayList<>(hashSet);
            return ResponseVo.success(listWithoutDuplicates);
        }/*else{
            teachinVoList=teachinMapper.getTeachinByGroupIdAndTime(dto);
            for(int i = 0;i<teachinVoList.size();i++){
                list.add(teachinVoList.get(i).getHappenTime().substring(8,10));
            }
            LinkedHashSet<String> hashSet =new LinkedHashSet<>(list);
            ArrayList<String> listWithoutDuplicates =new ArrayList<>(hashSet);
            return ResponseVo.success(listWithoutDuplicates);
        }*/
        return ResponseVo.success("没有该用户类型");
    }

    /**
     * 发布宣讲会
     *  //判断时间地点是否有重复的
     *         //判断是否从草稿箱中弄过来的
     *         //就社群和学校用户能发
     *         //企业能看到委托学校发的宣讲会
     * @param teachin
     * @param userId
     * @return
     */
    @Transactional(rollbackFor=Exception.class)
    @Override
    public ResponseVo releaseToastmasters(Teachin teachin, Integer userId) {
        //用户类型，普户01，校户02，企户03,学校社群用户04
        User user = userMapper.selectUserById(userId);
        if("01".equals(user.getUserType())){
            return ResponseVo.error("普通用户不能发布宣讲会！");
        }
        //验证
        if("02".equals(user.getUserType()) || "04".equals(user.getUserType())){
            if(StringUtils.isEmpty(teachin.getCredit())){
                return ResponseVo.error("营业执照不能为空！");
            }
            if(StringUtils.isEmpty(teachin.getCompanyName())){
                return ResponseVo.error("公司名字不能为空！");
            }
            teachin.setSchoolId(user.getSchoolId());
            teachin.setSchoolName(user.getSchoolName());
        }
        //企业用户，自动获取公司名和营业执照
        if("03".equals(user.getUserType())){
            CompanyAttestation companyAttestation = companyAttestationMapper.selectModelByUserId(userId);
            teachin.setCompanyName(companyAttestation.getCompanyName());
            teachin.setCredit(companyAttestation.getCredit());
            teachin.setTitle(companyAttestation.getCompanyName());
            if(teachin.getId()!=null){
                teachinMapper.update(teachin);
            }
        }

        if(StringUtils.isEmpty(teachin.getHappenTime())){
            return ResponseVo.error("宣讲会时间不能为空！");
        }
        if(StringUtils.isEmpty(teachin.getIntroduction())){
            return ResponseVo.error("宣讲会描述不能为空！");
        }
        if(StringUtils.isEmpty(teachin.getPlace())){
            return ResponseVo.error("宣讲会地点不能为空！");
        }
      /*  if(StringUtils.isEmpty(teachin.getTitle())){
            return ResponseVo.error("宣讲会标题不能为空！");
        }*/
        if(StringUtils.isEmpty(teachin.getIndustryId())){
            return ResponseVo.error("行业id不能为空不能为空！");
        }
        //发布时间

        //普通用户不能发布宣讲会
        if(!"01".equals(user.getUserType()) ){
            //发生时间地点不为空
            if(!StringUtils.isEmpty(teachin.getHappenTime()) && !StringUtils.isEmpty(teachin.getPlace())){
               List<Teachin>  teachinList = teachinMapper.selectModelByTimeAndPlace(teachin);
               if(teachinList.size()>0){
                   return ResponseVo.error("宣讲会时间地点重复，不能重复发布！");
               }
            }
            if(teachin.getId()!=null){
                //teachin.setStatus("03");//已发布--审核取消就直接发布
                String status="03";
                teachinMapper.updateStatus(status,teachin.getId());
            }else{
                teachin.setStatus("03");//已发布--审核取消就直接发布
                teachin.setOpUserId(userId);
                if(!"03".equals(user.getUserType())){
                    teachin.setSchoolId(user.getSchoolId());
                    teachin.setSchoolName(user.getSchoolName());
                }
                teachin.setIsPublic("01");
                teachin.setCreateTime(DateUtil.DateToString(new Date()));
                teachinMapper.save(teachin);

            }
            List<Teachin> list1 =teachinMapper.selectModelByMax();
            TeachinData teachinData = new TeachinData();
            teachinData.setEnrollNum(0);
            teachinData.setFoocusNum(0);
            if(list1.size()>0){
                teachinData.setTeachinId(list1.get(0).getId());
            }
            teachinData.setLookNum(0);
            teachinData.setLocalEnrollNum(0);
            teachinData.setOtherEnrollNum(0);
            teachinData.setStatus("01");
            teachinData.setUpdateTime(DateUtil.DateToString(new Date()));
            teachinDataMapper.save(teachinData);
            ActionLog actionLog = new ActionLog();
            actionLog.setUserId(userId);
            actionLog.setLogType("40");
            actionLog.setCreateTime(DateUtil.DateToString(new Date()));
            actionLog.setOperate("发布宣讲会");
            actionLog.setSource(list1.get(0).getId());
            actionLogMapper.save(actionLog);
            return ResponseVo.success("发布成功！");
        }

        else{
            return ResponseVo.error("该账号不能发布宣讲会！");
        }

    }

    /**
     * 保存
     * @param teachin
     * @param userId
     * @return
     */
    @Override
    public ResponseVo saveToastmastersDrafts(Teachin teachin, Integer userId) {
        //联系人，联系人电话从企业认证获取
        //非空验证
        if(userId==null){
            return ResponseVo.error("未登录或者登录超时！");
        }
        User user = userMapper.selectUserById(userId);
        if(user.getUserType().equals("03")){
            CompanyAttestation companyAttestation = companyAttestationMapper.selectModelByUserId(userId);
            teachin.setCompanyName(companyAttestation.getCompanyName());
            teachin.setCredit(companyAttestation.getCredit());
            teachin.setTitle(companyAttestation.getCompanyName());
            if(teachin.getId()!=null){
                teachinMapper.update(teachin);
            }
        }else{
            teachin.setSchoolName(user.getSchoolName());
            teachin.setSchoolId(user.getSchoolId());
        }
        //学校用户或者社群
        if(!"01".equals(user.getUserType())){
            //发生时间地点不为空
            if(!StringUtils.isEmpty(teachin.getHappenTime()) && !StringUtils.isEmpty(teachin.getPlace())){
                List<Teachin>  teachinList = teachinMapper.selectModelByTimeAndPlace(teachin);
                if(teachinList.size()>0){
                    return ResponseVo.error("宣讲会时间地点重复，不能保存！");
                }
            }
            teachin.setStatus("01");//待审--审核取消就直接发布
            teachin.setOpUserId(userId);
            if(StringUtils.isEmpty(teachin.getIsPublic())){
                teachin.setIsPublic("01");
            }
            int f =teachinMapper.save(teachin);
            teachinMapper.selectModelByMax();
            if(f<1){
                return ResponseVo.error("保存失败！");
            }else{
                return ResponseVo.success(teachinMapper.selectModelByMax().get(0).getId());
            }

        }else{
            return ResponseVo.error("该账号不能发布宣讲会！");
        }

    }

    /**
     * 修改
     * @param teachin
     * @return
     */
    @Override
    public ResponseVo updateToastmasters(Teachin teachin,Integer userId) {
        User user = userMapper.selectUserById(userId);
        //学校用户或者社群
        if("02".equals(user.getUserType()) || "04".equals(user.getUserType())){
            //发生时间地点不为空
            if(!StringUtils.isEmpty(teachin.getHappenTime()) && !StringUtils.isEmpty(teachin.getPlace())){
                List<Teachin>  teachinList = teachinMapper.selectModelByTimeAndPlace(teachin);
                if(teachinList.size()>1){
                    return ResponseVo.error("宣讲会时间地点重复，不能保存！");
                }
            }
           // teachin.setStatus("01");//待审--审核取消就直接发布
            //teachin.setOpUserId(userId);
            teachinMapper.update(teachin);
            return ResponseVo.success("保存成功！");
        }else{
            return ResponseVo.error("该账号不能发布宣讲会！");
        }
    }

    /**
     * 删除宣讲会
     * @param teachin
     * @param userId
     * @return
     */
    @Override
    public ResponseVo deleteToastmastersDrafts(Teachin teachin, Integer userId) {
        User user = userMapper.selectUserById(userId);
        //学校用户或者社群
        if("02".equals(user.getUserType()) || "04".equals(user.getUserType())){
            //发生时间地点不为空
            teachinMapper.delete(teachin.getId());
            ActionLog actionLog = new ActionLog();
            actionLog.setUserId(userId);
            actionLog.setLogType("40");
            actionLog.setCreateTime(DateUtil.DateToString(new Date()));
            actionLog.setOperate("删除宣讲会");
            actionLog.setSource(teachin.getId());
            actionLogMapper.save(actionLog);
            return ResponseVo.success("删除成功！");
        }else{
            return ResponseVo.error("该账号不能删除宣讲会！");
        }
    }

    /**
     * 获取我的草稿
     * @param userId
     * @return
     */
    @Override
    public ResponseVo getToastmastersDraftsList(PageModel pageModel, Integer userId) {
        PageHelper.startPage(pageModel.getPageNum(),pageModel.getPageSize());
        String status = "01";//草稿
        List<Teachin> teachinList = teachinMapper.selectModelByStatus(status,userId);
        PageInfo pageInfo = new PageInfo<>(teachinList);
        return ResponseVo.success(ClassCastUtil.pageInfoToPageOutputDto(pageInfo));
    }

    /**
     * 取消宣讲会
     * @param id
     * @param userId
     * @return
     */
    @Transactional(rollbackFor=Exception.class)
    @Override
    public ResponseVo cancelToastmasters(Integer id, Integer userId) {
        User user = userMapper.selectUserById(userId);
        //学校用户或者社群
        if(!"01".equals(user.getUserType())){
            //发生时间地点不为空
            String status = "05";//取消
            teachinMapper.updateStatus(status,id);
            ActionLog actionLog = new ActionLog();
            actionLog.setUserId(userId);
            actionLog.setLogType("40");
            actionLog.setCreateTime(DateUtil.DateToString(new Date()));
            actionLog.setOperate("取消发布宣讲会");
            actionLog.setSource(id);
            actionLogMapper.save(actionLog);
            return ResponseVo.success("取消成功！");
        }else{
            return ResponseVo.error("该账号不能取消宣讲会！");
        }
    }
}
