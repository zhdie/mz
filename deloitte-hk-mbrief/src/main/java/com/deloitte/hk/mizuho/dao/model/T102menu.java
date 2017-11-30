package com.deloitte.hk.mizuho.dao.model;

public class T102menu {
    private Integer id;

    private String menuid;

    private String parentid;

    private String title;

    private String url;

    private String applicationid;

    private Integer righttype;

    private Integer mtype;

    private Integer orderno;

    private Integer mlevel;

    private String descmemo;

    private String icon;

    private String pagetype;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMenuid() {
        return menuid;
    }

    public void setMenuid(String menuid) {
        this.menuid = menuid == null ? null : menuid.trim();
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid == null ? null : parentid.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getApplicationid() {
        return applicationid;
    }

    public void setApplicationid(String applicationid) {
        this.applicationid = applicationid == null ? null : applicationid.trim();
    }

    public Integer getRighttype() {
        return righttype;
    }

    public void setRighttype(Integer righttype) {
        this.righttype = righttype;
    }

    public Integer getMtype() {
        return mtype;
    }

    public void setMtype(Integer mtype) {
        this.mtype = mtype;
    }

    public Integer getOrderno() {
        return orderno;
    }

    public void setOrderno(Integer orderno) {
        this.orderno = orderno;
    }

    public Integer getMlevel() {
        return mlevel;
    }

    public void setMlevel(Integer mlevel) {
        this.mlevel = mlevel;
    }

    public String getDescmemo() {
        return descmemo;
    }

    public void setDescmemo(String descmemo) {
        this.descmemo = descmemo == null ? null : descmemo.trim();
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon == null ? null : icon.trim();
    }

    public String getPagetype() {
        return pagetype;
    }

    public void setPagetype(String pagetype) {
        this.pagetype = pagetype == null ? null : pagetype.trim();
    }
}