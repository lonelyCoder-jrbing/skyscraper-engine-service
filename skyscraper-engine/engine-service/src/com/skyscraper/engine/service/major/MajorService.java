package com.skyscraper.engine.service.major;

import com.skyscraper.engine.jpa.entity.Major;
import com.skyscraper.engine.service.response.MajorResponse;
import com.skyscraper.engine.service.response.PageResponse;
import com.skyscraper.engine.service.school.dto.QueryCondition;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * create by sumerian on 2020/8/29
 * <p>
 * desc:
 **/
public interface MajorService {
    /*****
     * update or save
     * @param major
     * @return boolean
     */
    boolean genMajor(Major major);

    /*****
     * query major
     * @param condition
     * @return
     */
    PageResponse<List<Major>> getAllMajor(QueryCondition condition);

    MajorResponse getMajorBySchoolId(Long id);

}
