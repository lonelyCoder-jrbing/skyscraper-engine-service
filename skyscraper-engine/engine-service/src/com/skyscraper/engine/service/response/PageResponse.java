package com.skyscraper.engine.service.response;

import lombok.Data;

/**
 * create by sumerian on 2020/8/30
 * <p>
 * desc:
 **/
@Data
public class PageResponse<T> {
    T data;
    int num = 0;
    int size = 0;

    public PageResponse() {
    }

    public PageResponse(int num, int size, T data) {
        this.num = num;
        this.size = size;
        this.data = data;
    }


    public static <T> PageResponse<T> of(int num, int size, T data) {
        return new PageResponse<>(num, size, data);
    }

}
