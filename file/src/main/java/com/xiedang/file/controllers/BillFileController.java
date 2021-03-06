package com.xiedang.file.controllers;

import com.xiedang.file.model.BillFiles;
import com.xiedang.file.service.BillFileService;
import com.xiedang.file.util.CommonResult;
import com.xiedang.file.vo.FilesVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * <p></p>
 *
 * @author : 谢当
 * @date : 2018/12/28 9:10
 */
@Controller
public class BillFileController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BillFileService billFileService;

    /**
     * 附件上传
     * @param request
     * @param filesVo
     * @param file_data
     * @return
     */
    @RequestMapping(value = "/uploadFile",produces = {"application/json;charset=utf-8"})
    @ResponseBody
    public CommonResult<BillFiles> uploadFile(HttpServletRequest request, FilesVo filesVo, MultipartFile file_data) {
        CommonResult<BillFiles> result=new CommonResult<>(CommonResult.SUCCESS_CODE);
        try {
            result=billFileService.uploadFile(filesVo,file_data);
        } catch (Exception e) {
            log.error("上传附件错误,参数{}",e);
            e.printStackTrace();
            result.setSuccess(CommonResult.FAILURE_CODE);
            result.setMessage("上传失败!");
        }
        return result;
    }
}
