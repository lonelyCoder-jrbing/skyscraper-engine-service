package com.skyscraper.engine.service.major.impl;

import com.skyscraper.engine.jpa.entity.Major;
import com.skyscraper.engine.jpa.entity.School;
import com.skyscraper.engine.jpa.entity.SchoolMajorConn;
import com.skyscraper.engine.jpa.repository.MajorRepository;
import com.skyscraper.engine.jpa.repository.MajorSchoolConnRepository;
import com.skyscraper.engine.jpa.repository.SchoolRepository;
import com.skyscraper.engine.service.common.PaasResponnse;
import com.skyscraper.engine.service.major.MajorService;
import com.skyscraper.engine.service.response.MajorResponse;
import com.skyscraper.engine.service.response.PageResponse;
import com.skyscraper.engine.service.school.dto.QueryCondition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * create by sumerian on 2020/8/29
 * <p>
 * desc:
 **/
@Service
@Slf4j
public class MajorServiceImpl implements MajorService {
    @Autowired
    MajorRepository majorRepository;
    @Autowired
    private SchoolRepository schoolRepository;
    @Autowired
    MajorSchoolConnRepository majorSchoolConnRepository;

    @Override
    public boolean genMajor(Major major) {
        return majorRepository.save(major) == null ? Boolean.FALSE : Boolean.TRUE;
    }

    @Override
    public PageResponse<List<Major>> getAllMajor(QueryCondition condition) {
        Sort sortBy = null;
        if (condition.getProperties() == null) {
            sortBy = Sort.by(Sort.Direction.ASC, "majorId");
        } else {
            sortBy = Sort.by(Sort.Direction.ASC, condition.getProperties());
        }
        Pageable pageable = PageRequest.of(condition.getPage(), condition.getSize(), sortBy);
        Page<Major> all = majorRepository.findAll(pageable);

        List<Major> majors = all.filter(el ->
                Objects.nonNull(condition.getIsLiteratureArts()) ?
                        el.getIsLiteratureArts().equals(condition.getIsLiteratureArts()) : true
                        && Objects.nonNull(condition.getHotLevel()) ?
                        el.getHotLevel().equals(condition.getHotLevel()) : true
                        && Objects.nonNull(condition.getMajorName()) ?
                        fuzzyMatching(el.getMajorName(), condition.getMajorName()) : true
        ).toList();
        return PageResponse.of(all.getNumber(), all.getSize(), majors);
    }

    @Override
    public MajorResponse getMajorBySchoolId(Long id) {
        SchoolMajorConn schoolMajorConn = new SchoolMajorConn();
        schoolMajorConn.setId(id);
        SchoolMajorConn schoolMajorConn1 = majorSchoolConnRepository
                .findOne(Example.of(schoolMajorConn))
                .get();
        Major major = new Major();
        major.setMajorId(schoolMajorConn1.getMajorId());
        Major major1 = majorRepository.findOne(Example.of(major)).get();
        return MajorResponse.builder()
                .emplyeeRate(schoolMajorConn1.getEmplyeeRate())
                .hotLevelInSchool(schoolMajorConn1.getHotLevel())
                .hotLevel(major1.getHotLevel())
                .majorName(major1.getMajorName())
                .isLiteratureArts(major1.getIsLiteratureArts())
                .build();
    }

    private Boolean fuzzyMatching(String var1, String var2) {
        return var2.contains(var1);
    }


}
