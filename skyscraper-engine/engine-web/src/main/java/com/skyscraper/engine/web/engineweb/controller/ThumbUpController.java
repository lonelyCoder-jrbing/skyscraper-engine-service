package com.skyscraper.engine.web.engineweb.controller;

import com.skyscraper.engine.jpa.entity.ThumbUp;
import com.skyscraper.engine.service.common.PaasResponnse;
import com.skyscraper.engine.service.request.ThumbUpRequest;
import com.skyscraper.engine.service.thumbupservice.ThumbUpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * create by sumerian on 2020/9/6
 * <p>
 * desc:
 **/
@RestController
@RequestMapping(value = "/runtime/school")
@Slf4j
public class ThumbUpController {


    @Autowired
    ThumbUpService thumbUpService;

    @PostMapping(path = "/genThumbUp")
    public PaasResponnse<Boolean> genThumbUp(@RequestBody ThumbUpRequest request) {
        ThumbUp thumbUp = new ThumbUp();
        BeanUtils.copyProperties(request, thumbUp);
        return PaasResponnse.success(thumbUpService.genThumbUp(thumbUp));
    }

    /*****
     * 查看文章下点赞的用户列表
     * @param paperId
     * @return
     */
    @PostMapping(path = "/getUserListByPaperId")
    public PaasResponnse<List<Long>> getUserListByPaperId(@RequestBody long paperId) {
        return PaasResponnse.success(thumbUpService.getUserListByPaperId(paperId));
    }

    /*****
     * 查看用户点赞的文章列表
     * @param userId
     * @return
     */
    @PostMapping(path = "/getPaperListByUserId")
    public PaasResponnse<List<Long>> getPaperListByUserId(@RequestBody long userId) {
        return PaasResponnse.success(thumbUpService.getPaperListByUserId(userId));
    }


}
