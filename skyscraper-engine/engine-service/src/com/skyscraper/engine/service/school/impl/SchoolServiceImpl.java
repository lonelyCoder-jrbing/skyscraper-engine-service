package com.skyscraper.engine.service.school.impl;

import com.skyscraper.engine.jpa.entity.SchoolMajorConn;
import com.skyscraper.engine.jpa.repository.MajorSchoolConnRepository;
import com.skyscraper.engine.service.school.SchoolService;
import com.skyscraper.engine.jpa.entity.School;
import com.skyscraper.engine.service.school.dto.QueryCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.skyscraper.engine.jpa.repository.SchoolRepository;
/**
 * create by sumerian on 2020/8/29
 * <p>
 * desc:
 **/
@Service
public class SchoolServiceImpl implements SchoolService {
    @Autowired
    private SchoolRepository schoolRepository;
    @Autowired
    MajorSchoolConnRepository majorSchoolConnRepository;

    @Override
    public boolean genSchool(School school) {
        School save = schoolRepository.save(school);
        return save != null ? true : false;
    }

    @Override
    public Page<School> getAllSchool(QueryCondition request) {
        Sort sortBy = null;
        if (request.getProperties() == null) {
            sortBy = Sort.by(Sort.Direction.ASC, "id");
        } else {
            sortBy = Sort.by(Sort.Direction.ASC, request.getProperties());

        }
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize(), sortBy);
        return schoolRepository.findAll(pageable);
    }

    @Override
    public Boolean genSchoolMajor(SchoolMajorConn schoolMajorConn) {
        return majorSchoolConnRepository.save(schoolMajorConn) != null ? true : false;
    }
}
