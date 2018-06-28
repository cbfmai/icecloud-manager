package com.mmtech.icecloud.manager.common.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Pager<T> implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private List<T> data = new ArrayList<>();


    /**
     * 总记录数
     */
    private long total;

    /**
     * 总页码数
     */
    private int totalPage;

    /**
     * 当前页面
     */
    private int currentPage = 1;

    private int size = 10;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}

