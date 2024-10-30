package com.sky.controller.admin;


import com.sky.constant.MessageConstant;
import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

/**
 * 通用接口
 */
@RestController
@RequestMapping("/admin/common")
@Slf4j
@Api(tags = "通用接口")
public class CommonController {

    @PostMapping("/upload")
    @ApiOperation("文件上传（无阿里云）controller接口")
    public Result<String> upload(MultipartFile file) {
        log.info("文件上传，{}：",file);
        String originalFilename = file.getOriginalFilename();
        try {
            if (originalFilename != null) {
                // 利用UUID构造新的文件名称
                String objectName = UUID.randomUUID().toString() + originalFilename;
                // 文件的请求路径
                String filePath = "E:\\JavaProjects\\sky-take-out\\sky-server\\src\\main\\resources\\static\\imgs\\" + objectName;
                String returnImagePate = "http://127.0.0.1:8080/images/" + objectName;
                file.transferTo(new File(filePath));
                return Result.success(returnImagePate);
            } else {
                throw new FileNotFoundException(MessageConstant.FILE_NAME_IS_NULL);
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.error("文件上传失败:{}", e);
        }
        return Result.error(MessageConstant.UPLOAD_FAILED);
    }

}
