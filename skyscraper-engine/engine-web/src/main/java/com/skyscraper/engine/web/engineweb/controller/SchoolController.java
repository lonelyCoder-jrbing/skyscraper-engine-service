package com.skyscraper.engine.web.engineweb.controller;

import com.skyscraper.engine.jpa.entity.Major;
import com.skyscraper.engine.jpa.entity.SchoolMajorConn;
import com.skyscraper.engine.service.major.MajorService;
import com.skyscraper.engine.service.response.PageResponse;
import com.skyscraper.engine.service.school.dto.QueryCondition;
import com.skyscraper.engine.service.request.MajorRequest;
import com.skyscraper.engine.service.request.QueryConditionRequest;
import com.skyscraper.engine.service.request.SchoolMajorConnRequest;
import com.skyscraper.engine.service.request.SchoolRequest;
import com.skyscraper.engine.service.common.PaasResponnse;
import com.skyscraper.engine.jpa.entity.School;
import com.skyscraper.engine.service.response.MajorResponse;
import com.skyscraper.engine.web.engineweb.utils.IsNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import com.skyscraper.engine.service.school.SchoolService;
import java.util.List;

/**
 * create by sumerian on 2020/8/29
 * <p>
 * desc:学校以及专业的列表展示
 **/
@RestController
@RequestMapping(value = "/runtime/school")
@Slf4j
public class SchoolController {

    @Autowired
    private SchoolService schoolService;
    @Autowired
    private MajorService majorService;


    @PostMapping(path = "/genSchool")
    public PaasResponnse<Boolean> genSchool(@RequestBody SchoolRequest schoolRequest) {
        List<String> strings = IsNull.validateProperty(
                schoolRequest,
                "id",
                "createAt",
                "getCreateBy",
                "updateAt",
                "updateBy");

        System.out.println("school:" + schoolRequest);
        if (strings.size() > 0) {
            return PaasResponnse.fail(false, "" + strings);
        }
        School schools = new School();

        BeanUtils.copyProperties(schoolRequest, schools);
        log.info("schools===========:{}", schools);
        boolean b = schoolService.genSchool(schools);
        return PaasResponnse.success(b);
    }

    @PostMapping(path = "/getAllSchool")
    public PaasResponnse<Page<School>> getAllSchool(@RequestBody QueryConditionRequest request) {
        QueryCondition condition = new QueryCondition();
        BeanUtils.copyProperties(request, condition);
        return PaasResponnse.success(schoolService.getAllSchool(condition));
    }


    @PostMapping(path = "/genSchoolMajor")
    public PaasResponnse<Boolean> genSchoolMajor(@RequestBody SchoolMajorConnRequest request) {
        List<String> strings = IsNull.validateProperty(
                request,
                "id",
                "createAt",
                "getCreateBy",
                "updateAt",
                "updateBy");
        log.info("SchoolMajorConnRequest==:{}", request);
        if (strings.size() > 0) {
            return PaasResponnse.fail(false, "" + strings);
        }
        SchoolMajorConn schoolMajorConn = new SchoolMajorConn();
        BeanUtils.copyProperties(request, schoolMajorConn);
        return PaasResponnse.success(schoolService.genSchoolMajor(schoolMajorConn));
    }

    @PostMapping(path = "/genMajor")
    public PaasResponnse<Boolean> genSchool(@RequestBody MajorRequest majorRequest) {
        List<String> strings = IsNull.validateProperty(
                majorRequest,
                "majorId",
                "createAt",
                "getCreateBy",
                "updateAt",
                "updateBy");
        if (strings.size() > 0) {
            return PaasResponnse.fail(false, "" + strings);
        }
        Major major = new Major();
        BeanUtils.copyProperties(majorRequest, major);
        log.info("schools===========:{}", major);
        return PaasResponnse.success(majorService.genMajor(major));
    }

    @PostMapping(path = "/getAllMajor")
    public PaasResponnse<PageResponse<List<Major>>> getAllMajor(@RequestBody QueryConditionRequest request) {
        QueryCondition condition = new QueryCondition();
        BeanUtils.copyProperties(request, condition);
        return PaasResponnse.success(majorService.getAllMajor(condition));
    }

    @GetMapping(path = "/getMajorBySchoolId")
    public PaasResponnse<MajorResponse> getMajorBySchoolId(@RequestBody Long id) {
        return PaasResponnse.success(majorService.getMajorBySchoolId(id));
    }


}

