package com.ybkj.gun.controller;

import com.ybkj.common.constant.StatusCodeEnum;
import com.ybkj.common.model.BaseModel;
import com.ybkj.gun.service.SoftwareVersionSerivce;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 *@Description:  功能描述（app的版本更新功能）
 *@Author:       刘家义
 *@CreateDate:   2018/8/21 10:38
 *@UpdateUser:   刘家义
 *@UpdateDate:   2018/8/21 10:38
 *@UpdateRemark: 修改内容
 *@Version:      1.0
*/
@SuppressWarnings("all")
@Api(value = "/",description = "在线升级APP的功能")
@RestController
@RequestMapping("/softwareVersion")
public class SoftwareVersionController {

    @Autowired
    private SoftwareVersionSerivce softwareVersionSerivce;

    /**
     *
     * @Description  判断当前版本号是否是最新的
     * @param versionNum  当前版本号
     * @param type 0:安卓端1:ios
     * @return
     * @author 刘家义
     * @date 2018年8月21日
     */

    //@AuthorityAop(module = EnumOperationModule.No305, type = ConstantsCommon.FUN_SEARCH)
    //@LogAop(module = EnumOperationModule.No305, type = ConstantsCommon.FUN_SEARCH,isApp="0")
    @ApiOperation(value = "判断当前版本号是否是最新的",httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "versionNum", value = "当前版本号", required = false, paramType = "query"),
            @ApiImplicitParam(name = "type", value = "0:安卓端1:ios", required = true, paramType = "query")})
    @RequestMapping(value = "/checkVersionApp",method = RequestMethod.GET)
    public BaseModel checkVersionApp(String versionNum, Integer type) throws Exception {
        BaseModel baseModel=new BaseModel();
        baseModel = softwareVersionSerivce.checkVersionApp(versionNum, type);
        return baseModel;
    }


    /**
     * app apk下载
     * @param res
     */
    @RequestMapping(value = "/appApkDownload", method = RequestMethod.GET)
    public BaseModel appApkDownload(HttpServletResponse res) {
        BaseModel baseModel=new BaseModel();
        String fileName = "1.jpg";
        res.setHeader("content-type", "application/octet-stream");
        res.setContentType("application/octet-stream");
        res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            //获取跟目录
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            if(!path.exists()) path = new File("");
            System.out.println("path:"+path.getAbsolutePath());

//如果上传目录为/static/images/upload/，则可以如下获取：
            File upload = new File(path.getAbsolutePath(),"static/apk/");
            if(!upload.exists()) upload.mkdirs();
            System.out.println("upload url:"+upload.getAbsolutePath());
//在开发测试模式时，得到的地址为：{项目跟目录}/target/static/images/upload/
//在打包成jar正式发布时，得到的地址为：{发布jar包目录}/static/images/upload/

            os = res.getOutputStream();
            bis = new BufferedInputStream(new FileInputStream(new File(path.getAbsolutePath(),"static/apk/"+fileName)));
            int i = bis.read(buff);
            while (i != -1) {
                os.write(buff, 0, buff.length);
                os.flush();
                i = bis.read(buff);
            }
        } catch (IOException e) {
            e.printStackTrace();
            baseModel.setStatus(StatusCodeEnum.Fail.getStatusCode());
            baseModel.setErrorMessage("下载失败");
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        baseModel.setStatus(StatusCodeEnum.SUCCESS.getStatusCode());
        baseModel.setErrorMessage("下载成功");
        return baseModel;
    }
}
