package com.deloitte.hk.common.mybatis.page;

import java.io.Serializable;

/**
 * 分页参数类
 * 
 */
public class PageParameter implements Serializable {

    private static final long serialVersionUID = 8683452581122892189L;

    public static final int DEFAULT_PAGE_SIZE = 10;

    private int pageSize;
    private long currentPage;
    private long prePage;
    private long nextPage;
    private long totalPage;
    private long totalCount;

    public PageParameter() {
        this.currentPage = 1;
        this.pageSize = DEFAULT_PAGE_SIZE;
        this.nextPage = currentPage + 1;
        this.prePage = currentPage - 1;
    }

    /**
     * 
     * @param currentPage
     * @param pageSize
     */
    public PageParameter(int currentPage, int pageSize) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.nextPage = currentPage + 1;
        this.prePage = currentPage - 1;
    }

    

    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
        this.nextPage = currentPage + 1;
        this.prePage = currentPage - 1;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

	public long getPrePage() {
		return prePage;
	}

	public void setPrePage(long prePage) {
		this.prePage = prePage;
	}

	public long getNextPage() {
		return nextPage;
	}

	public void setNextPage(long nextPage) {
		this.nextPage = nextPage;
	}

	public long getCurrentPage() {
		return currentPage;
	}

	public long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public static int getDefaultPageSize() {
		return DEFAULT_PAGE_SIZE;
	}

   

}
