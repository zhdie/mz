package com.deloitte.hk.common.bean.menu;

import java.io.Serializable;

public class NodeItem implements Serializable {

	private boolean start;
	private boolean active;
	private String title;
	private String menuid;
	private String url;
	private boolean clearCache;
	private boolean useCache;
	private boolean isIframe;
	private int mtype;
	private int mlevel;
	public boolean isStart() {
		return start;
	}
	public void setStart(boolean start) {
		this.start = start;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMenuid() {
		return menuid;
	}
	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public boolean isClearCache() {
		return clearCache;
	}
	public void setClearCache(boolean clearCache) {
		this.clearCache = clearCache;
	}
	public boolean isUseCache() {
		return useCache;
	}
	public void setUseCache(boolean useCache) {
		this.useCache = useCache;
	}
	public boolean isIsIframe() {
		return isIframe;
	}
	public void setIsIframe(boolean isIframe) {
		this.isIframe = isIframe;
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
	
}
