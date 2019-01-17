package com.feri.selfcoding_webapp.app;

import com.feri.common.util.ResultUtil;
import com.feri.common.vo.ResultVO;
import com.feri.selfcoding_webapp.util.FileUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *@Author feri
 *@Date Created in 2019/1/17 10:08
 */
@Api(value = "资源上传")
@RestController
public class FileUploadController {

    //上传文件或图片
    @ApiOperation(value = "文件或图片上传",notes = "支持多文件上传")
    @PostMapping("fileupload.do")
    public ResultVO upload(@RequestParam("file") MultipartFile[] files, HttpServletRequest request) throws IOException {
        if(files!=null && files.length>0){
            List<String> paths=new ArrayList<>();
            for(MultipartFile f:files){
                //获取上传的文件名称
                String fn=FileUtil.renameFile(f.getOriginalFilename());
                //获取存储的文件夹
                File dir=FileUtil.createDir(new File(request.getServletContext().getRealPath("/")).getParent()+
                        File.separator+"selfresource");
                File desFile=new File(dir,fn);
                f.transferTo(desFile);
                paths.add("/selfresource"+File.separator+dir.getName()+File.separator+fn);
            }
            return  ResultUtil.execOK(paths);
        }
        return ResultUtil.execERROR();
    }
    //上传视频
    @ApiOperation(value = "视频上传",notes = "支持视频上传，不要太大")
    @PostMapping("videoupload.do")
    public ResultVO uploadVideo(@RequestParam("video") MultipartFile vedio, HttpServletRequest request) throws IOException {
        if(vedio!=null ){
            StringBuffer buffer=new StringBuffer();
                //获取上传的文件名称
                String fn=FileUtil.renameFile(vedio.getOriginalFilename());
                //获取存储的文件夹
                File dir=FileUtil.createDir(new File(request.getServletContext().getRealPath("/")).getParent()+
                        File.separator+"selfvideos");
                buffer.append("/selfvideos"+File.separator);
                buffer.append(dir.getName()+File.separator);
                buffer.append(fn);
                vedio.transferTo(new File(dir,fn));
            return  ResultUtil.execOK(buffer.toString());
        }
        return ResultUtil.execERROR();
    }
}
