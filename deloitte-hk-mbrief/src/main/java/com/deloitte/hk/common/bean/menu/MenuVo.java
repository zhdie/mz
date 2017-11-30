package com.deloitte.hk.common.bean.menu;

import java.io.Serializable;

public class MenuVo implements Serializable {

	private String menuid;
	private String parentid;
	private String title;
	private String url;
	private String applicationid;
//	private boolean clearCache = false;
//	private boolean useCache = false;
//	private boolean isIframe = false;
	private int mtype;
	private int mlevel;
	private int iconname;
//	private boolean active = false;
//	private boolean start = false;
	public String getMenuid() {
		return menuid;
	}
	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getApplicationid() {
		return applicationid;
	}
	public void setApplicationid(String applicationid) {
		this.applicationid = applicationid;
	}
	public int getMtype() {
		return mtype;
	}
	public void setMtype(int mtype) {
		this.mtype = mtype;
	}
	public int getMlevel() {
		return mlevel;
	}
	public void setMlevel(int mlevel) {
		this.mlevel = mlevel;
	}
	public int getIconname() {
		return iconname;
	}
	public void setIconname(int iconname) {
		this.iconname = iconname;
	}
	
	
	
}
