package com.deloitte.hk.common.bean.menu;

import java.io.Serializable;
import java.util.List;

public class MenuItem implements Serializable {

	private String title;
	private int mtype;
	private int mlevel;
	private String icon;
	private List<NodeItem> children;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public List<NodeItem> getChildren() {
		return children;
	}
	public void setChildren(List<NodeItem> children) {
		this.children = children;
	}
	
	
}
