package com.ljb.controller;

import com.ljb.Base.BaseController;
import com.ljb.config.UploadConfig;
import com.ljb.utils.DateUtils;
import com.ljb.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("upload")
public class UploadController extends BaseController {

    private final String basePath="static"+File.separator+"file"+File.separator;

    @Autowired
    private UploadConfig uploadConfig;

    @RequestMapping("/image")
    public R image(@RequestParam("file") List<MultipartFile> images) {
        String path = DateUtils.getStringToday().substring(0, 8);
        List<String> srcs = new ArrayList<>();
        for (MultipartFile image : images) {
            if (image.getSize() <= 10) {
                return R.error(-1, "文件错误");
            }
            String origname = image.getOriginalFilename();
            String fileName = getUploadNmae() + "." + getExtension(origname);
            String uploadPath = basePath + "image" + File.separator + path + File.separator + fileName;
            saveFile(uploadPath, image);
            String imagePath = "/" + uploadPath.replace(File.separator, "/");
            srcs.add(imagePath);
        }
        return R.ok().put("data", srcs);
    }

    @RequestMapping("/video")
    public R video(@RequestParam("file") List<MultipartFile> images) {
        List<String> srcs = new ArrayList<>();
        String path = DateUtils.getStringToday().substring(0, 8);
        for (MultipartFile image : images) {
            if (image.getSize() <= 10) {
                return R.error(-1, "文件错误");
            }
            String origname = image.getOriginalFilename();
            String fileName = origname.substring(0,origname.lastIndexOf(".")) + "." + getExtension(origname);
            String uploadPath = basePath + "video" + File.separator + path + File.separator + fileName;
            File file=new File(uploadConfig.getLocation()+uploadPath);
            int i=1;
            while (file.exists()){
                fileName = origname.substring(0,origname.lastIndexOf("."))+String.valueOf (i++)+ "." + getExtension(origname);
                uploadPath = basePath + "file" + File.separator + path + File.separator + fileName;
                file=new File(uploadConfig.getLocation()+uploadPath);
            }
            saveFile(uploadPath, image);
            String imagePath = "/" + uploadPath.replace(File.separator, "/");
            srcs.add(imagePath);
        }
        return R.ok().put("data", srcs);
    }

    @RequestMapping("/file")
    public R file(@RequestParam("file") List<MultipartFile> images) {
        List<String> srcs = new ArrayList<>();
        String path = DateUtils.getStringToday().substring(0, 8);
        for (MultipartFile image : images) {
            if (image.getSize() <= 10) {
                return R.error(-1, "文件错误");
            }
            String origname = image.getOriginalFilename();
            String fileName = origname.substring(0,origname.lastIndexOf(".")) + "." + getExtension(origname);
            String uploadPath = basePath + "file" + File.separator + path + File.separator + fileName;
            File file=new File(uploadConfig.getLocation()+uploadPath);
            int i=1;
            while (file.exists()){
                fileName = origname.substring(0,origname.lastIndexOf("."))+String.valueOf (i++)+ "." + getExtension(origname);
                uploadPath =basePath + "file" + File.separator + path + File.separator + fileName;
                file=new File(uploadConfig.getLocation()+uploadPath);
            }
            saveFile(uploadPath, image);
            String imagePath = "/" + uploadPath.replace(File.separator, "/");
            srcs.add(imagePath);
        }
        return R.ok().put("data", srcs);
    }

    @RequestMapping("/filelist")
    public R fileList(@RequestParam(required = false) Integer start,@RequestParam(required = false) Integer size) {
        List<String> srcs = new ArrayList<>();
        start=start==null?0:start;
        size=size==null?20:size;
        String path = DateUtils.getStringToday().substring(0, 8);
        String uploadPath = basePath + "file" + File.separator + path;
        File file=new File(uploadConfig.getLocation()+uploadPath);
        if(!file.exists()){
            file.mkdirs();
        }
        File[] files=file.listFiles();
        for(int i=start;i<size;i++){
            if(files[start].exists()){
              String imagePath="/"+ uploadPath.replace(File.separator, "/")+"/"+files[i].getName();
              srcs.add(imagePath);
            }else{
                break;
            }
        }
        return R.ok().put("data", srcs);
    }

    @RequestMapping("/imagelist")
    public R imageList(@RequestParam(required = false) Integer start,@RequestParam(required = false) Integer size) {
        List<String> srcs = new ArrayList<>();
        start=start==null?0:start;
        size=size==null?20:size;
        String path = DateUtils.getStringToday().substring(0, 8);
        String uploadPath =basePath + "image" + File.separator + path;
        File file=new File(uploadConfig.getLocation()+uploadPath);
        if(!file.exists()){
            file.mkdirs();
        }
        File[] files=file.listFiles();
        if(files.length>start) {
            for (int i = start; i < size; i++) {
                if (files[start].exists()) {
                    String imagePath = "/" + uploadPath.replace(File.separator, "/") + "/" + files[i].getName();
                    srcs.add(imagePath);
                } else {
                    break;
                }
            }
        }
        return R.ok().put("data", srcs);
    }


    @RequestMapping("deleteimage")
    public R deleteImage(@RequestParam("path") String path, HttpServletRequest request) {
        if (path.length() <= 0) {
            return R.error("文件路径出错,无法删除");
        }
        String serverPath = request.getSession().getServletContext().getRealPath(path);
        File file = new File(serverPath);
        if (file.exists()) {
            file.delete();
        }
        return R.ok("删除成功");
    }

    public String getUploadNmae() {
        Random random = new Random();
        return DateUtils.getNo(4);
    }

    public String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length()).toLowerCase();
    }

    public void saveFile(String targetPath, MultipartFile file) {
        try {
            File targetFile = new File(uploadConfig.getLocation() + targetPath);
            if (!targetFile.getParentFile().exists()) {
                targetFile.getParentFile().mkdirs();
            }
            targetFile.createNewFile();
            OutputStream os = new FileOutputStream(targetFile);
            os.write(file.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
