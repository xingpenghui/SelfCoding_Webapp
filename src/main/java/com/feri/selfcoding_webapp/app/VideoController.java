package com.feri.selfcoding_webapp.app;

import com.feri.common.vo.ResultVO;
import com.feri.domain.video.Video;
import com.feri.domain.video.Videocourse;
import com.feri.service.video.VideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *@Author feri
 *@Date Created in 2019/1/21 16:47
 */
@Api(value = "视频相关操作",tags = "操作视频")
@RestController
public class VideoController {
    @Autowired
    private VideoService videoService;

    @ApiOperation(value = "新增视频",notes = "添加视频，注意视频上传请单独调用接口，只做数据新增")
    @PostMapping("videosave.do")
    public ResultVO save(Video video){
        return videoService.saveVideo(video);
    }
    @ApiOperation(value = "新增视频专题",notes = "添加视频专题")
    @PostMapping("videocoursesave.do")
    public ResultVO savevc(Videocourse videocourse){
        return videoService.saveCourse(videocourse);
    }




}
