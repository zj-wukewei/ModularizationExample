package com.wkw.commonbusiness.entity;

public class AbstractQry {
    public static final int DEFAULT_PAGENUM = 0;
    public static final int DEFAULT_PAGESIZE = 20;

    private int pageNum = DEFAULT_PAGENUM;// 第几页，首页为1
    private int pageSize = DEFAULT_PAGESIZE;// 每页记录条数


    public AbstractQry(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
