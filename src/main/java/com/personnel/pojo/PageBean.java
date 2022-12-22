package com.personnel.pojo;

import java.util.List;

/**
 * 分页 封装
 */
public class PageBean<T> {
    //总记录数
    private Integer totalCount;
    //可存储实体数据
    private List<T> rows;

    public PageBean() {
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "totalCount=" + totalCount +
                ", rows=" + rows +
                '}';
    }
}
