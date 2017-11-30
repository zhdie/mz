package com.deloitte.hk.common.bean.menu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MenuPo implements Serializable {
	private String menuid;
	private String parentid;
	private String title;
	private String url;
	private String applicationid;
	private int righttype;
	private int mtype;
	private int orderno;
	private int mlevel;
	private String descmemo;
	private String icon;
	private String pagetype;
	private boolean active = false;
	private boolean start = false;
	private boolean clearCache = false;
	private boolean useCache = false;
	
	
	private List<MenuPo> children = new ArrayList<MenuPo>();
	
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public String getApplicationid() {
		return applicationid;
	}
	public void setApplicationid(String applicationid) {
		this.applicationid = applicationid;
	}
	public int getRighttype() {
		return righttype;
	}
	public void setRighttype(int righttype) {
		this.righttype = righttype;
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
	public String getDescmemo() {
		return descmemo;
	}
	public void setDescmemo(String descmemo) {
		this.descmemo = descmemo;
	}
	
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
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
	public boolean isStart() {
		return start;
	}
	public void setStart(boolean start) {
		this.start = start;
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
//	public boolean isIsIframe() {
//		return isIframe;
//	}
//	public void setIsIframe(boolean isIframe) {
//		this.isIframe = isIframe;
//	}
	public String getPagetype() {
		return pagetype;
	}
	public void setPagetype(String pagetype) {
		this.pagetype = pagetype;
	}
	
	public List<MenuPo> getChildren() {
		return children;
	}	
	
	public void setChildren(List<MenuPo> children) {
		this.children = children;
	}
	public int getOrderno() {
		return orderno;
	}
	public void setOrderno(int orderno) {
		this.orderno = orderno;
	}

}
