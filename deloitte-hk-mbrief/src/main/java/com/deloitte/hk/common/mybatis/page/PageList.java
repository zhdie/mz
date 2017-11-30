package com.deloitte.hk.common.mybatis.page;

import java.util.ArrayList;
import java.util.Collection;


public class PageList<E> extends ArrayList {

    /** 分页参数，必须命名为page，勿修改 */
    private PageParameter page;

    /**
     * 业务需要传递的扩展信息
     */
    private Object extension;

    public PageList() {
        super();
        // page = new PageParameter();
    }

    public PageParameter getPage() {
        return page;
    }

    public void setPage(PageParameter page) {
        this.page = page;
    }

    public Object getExtension() {
        return extension;
    }

    public void setExtension(Object extension) {
        this.extension = extension;
    }

    public void addAll(Collection<? extends E> e, PageParameter page) {
        super.addAll(e);
        this.page = page;
    }

}
