package com.skyscraper.engine.service.school;

import com.skyscraper.engine.jpa.entity.School;
import com.skyscraper.engine.jpa.entity.SchoolMajorConn;
import com.skyscraper.engine.service.school.dto.QueryCondition;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * create by sumerian on 2020/8/29
 * <p>
 * desc:
 **/
public interface SchoolService {
    /*****
     * update or save
     * @param school
     * @return
     */
    boolean genSchool(School school);

    /*****
     * query by page and sorting by properites
     * @param condition
     * @return
     */
    Page<School> getAllSchool(QueryCondition condition);

    /****
     * update or save
     * @param schoolMajorConn
     * @return
     */
    Boolean genSchoolMajor(SchoolMajorConn schoolMajorConn);

}
