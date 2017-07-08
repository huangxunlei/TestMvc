package com.seereals.beidou.controller;

import com.seereals.beidou.entity.FileModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;
import java.util.Date;


/**
 * 文件上传
 * Created by Administrator on 2017/7/7 0007.
 */
@Controller
public class UploadController {
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> addUser(MultipartFile files) {
        Map<String, String> map = new HashMap<>();

        if (!files.isEmpty()) {
            int per = (int) System.currentTimeMillis();
            try {
                ////拿到输出流，同时重命名上传的文件
                FileOutputStream os = new FileOutputStream("D:/" + new Date().getTime() + files.getOriginalFilename());
                //拿到上传文件的输入流
                FileInputStream in = (FileInputStream) files.getInputStream();
                //以写字节的方式写文件
                int b = 0;
                while ((b = in.read()) != -1) {
                    os.write(b);
                }
                os.flush();
                os.close();
                in.close();
                map.put("result", "success");
                int finaltime = (int) System.currentTimeMillis();
                //System.out.println(finaltime - pre);
            } catch (Exception e) {
                e.printStackTrace();
                map.put("result", "fail");
                System.out.println("上传出错");
            }
        }

        return map;
    }

    @RequestMapping("/upload2")
    @ResponseBody
    public static String upload2(HttpServletRequest request, HttpServletResponse response) throws IllegalStateException, IOException {
        //创建一个通用的多部分解析器
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        //判断 request 是否有文件上传,即多部分请求
        if (multipartResolver.isMultipart(request)) {
            //转换成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            //取得request中的所有文件名
            Iterator<String> iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                //记录上传过程起始时的时间，用来计算上传时间
                int pre = (int) System.currentTimeMillis();
                //取得上传文件
                MultipartFile file = multiRequest.getFile(iter.next());
                if (file != null) {
                    //取得当前上传文件的文件名称
                    String myFileName = file.getOriginalFilename();
                    //如果名称不为“”,说明该文件存在，否则说明该文件不存在
                    if (myFileName.trim() != "") {
                        System.out.println(myFileName);
                        //重命名上传后的文件名
                        String fileName = "demoUpload" + file.getOriginalFilename();
                        //定义上传路径
                        String path = "D:/" + fileName;
                        File localFile = new File(path);
                        file.transferTo(localFile);
                    }
                }
                //记录上传该文件后的时间
                int finaltime = (int) System.currentTimeMillis();
                System.out.println(finaltime - pre);
            }

        }
        return "/success";
    }

    @RequestMapping(value = "upload1", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> upload1(HttpServletRequest request,
                                       HttpServletResponse response, @RequestParam MultipartFile file) throws Exception {
        if (request instanceof MultipartHttpServletRequest) {
            Map<String, Object> jsonMap = new HashMap<String, Object>();
            if (file.isEmpty()) {
                jsonMap.put("status", -1);
                jsonMap.put("result", "【文件为空！】");
                System.out.println("【文件为空！】");
                return jsonMap;
            }
            String fileName = file.getOriginalFilename();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String uploadPath = request.getSession().getServletContext().getRealPath("E:/" + sdf.format(new Date()));
            System.out.println(uploadPath);
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            File uploadFile = new File(uploadPath + "/" + fileName);
            file.transferTo(uploadFile);//上传
            System.out.println("上传成功！");
            jsonMap.put("status", 1);
            jsonMap.put("result", "【上传成功！】");
            return jsonMap;
        } else {
            return null;
        }
    }

    @RequestMapping("/toupload")
    public String toUpload() {

        return "upload";
    }

    @RequestMapping(value = "upload4", method = RequestMethod.POST)
    @ResponseBody
    private String upload4(@RequestParam(value = "file", required = false) MultipartFile file,
                           HttpServletRequest request) throws Exception {
        //基本表单
        String logoname = "失败";
        System.out.println("你好");
        MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
        MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
        MultipartFile multipartFile = multipartRequest.getFile("file");
        //获得物理路径webapp所在路径
        String pathRoot = request.getSession().getServletContext().getRealPath("/");
        pathRoot = pathRoot.substring(0, pathRoot.indexOf("out"));
        System.out.println(pathRoot);
        String path = "";

        if (multipartFile != null && !multipartFile.isEmpty()) {
            //生成uuid作为文件名称
            System.out.println(multipartFile.getName());
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            //获得文件类型（可以判断如果不是图片，禁止上传）
            String contentType = multipartFile.getContentType();
            //获得文件后缀名称
            String imageName = contentType.substring(contentType.indexOf("/") + 1);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

            path = "web/WEB-INF/static/images/" + sdf.format(new Date()) + "/";
             /* 构建文件目录 */
            File fileDir = new File(pathRoot + path);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
            multipartFile.transferTo(new File(pathRoot + path + uuid + "." + imageName));
            logoname = request.getRequestURL().toString().replace(request.getRequestURI(),"")+"/static/images/" + sdf.format(new Date()) + "/" + uuid + "." + imageName;
        }
        System.out.println(path);
        request.setAttribute("imagesPath", path);
        return logoname;
    }

    @Autowired
    ServletContext context;

    @RequestMapping(value = "/fileUploadPage", method = RequestMethod.POST)
    public String fileUpload(@Validated FileModel file, BindingResult result, ModelMap model) throws IOException {
        if (result.hasErrors()) {
            System.out.println("validation errors");
            return "fileUploadPage";
        } else {
            System.out.println("Fetching file");
            MultipartFile multipartFile = file.getFile();
            String uploadPath = context.getRealPath("") + File.separator + "temp" + File.separator;
            //Now do something with file...
            FileCopyUtils.copy(file.getFile().getBytes(), new File(uploadPath + file.getFile().getOriginalFilename()));
            String fileName = multipartFile.getOriginalFilename();
             /* 构建文件目录 */
//            File fileDir = new File(saveFilePath);
//            if (!fileDir.exists()) {
//                fileDir.mkdirs();
//            }
            model.addAttribute("fileName", fileName);
            return fileName;
        }
    }

    @RequestMapping(value = "/fileUploadPage", method = RequestMethod.GET)
    public ModelAndView fileUploadPage() {
        FileModel file = new FileModel();
        ModelAndView modelAndView = new ModelAndView("upload", "command", file);
        return modelAndView;
    }
}
