package com.wangyuhang.dreamboot.controller;

import com.wangyuhang.dreamboot.service.FileUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/new")
public class UploadController {


    @Autowired
    private FileUploadService fileUploadService;

    /*
     * 文件上传api
     */
    @PostMapping("/upload")
    public Map<String,Object> upload(@RequestParam(value = "file",required = false) MultipartFile file) {
        Map<String,Object> map = new HashMap<>();
        if (file != null) {
            String returnFileUrl = fileUploadService.upload(file);
            if (returnFileUrl.equals("error")) {
                map.put("error", "文件上传失败！");
                return map;
            }
            map.put("success", "文件上传成功！");
            map.put("returnFileUrl", returnFileUrl);
            return map;
        } else {
            map.put("error", "失败");
            return map;
        }
    }


}
