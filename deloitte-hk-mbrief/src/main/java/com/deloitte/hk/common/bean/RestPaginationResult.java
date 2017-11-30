package com.deloitte.hk.common.bean;

import java.util.List;

public class RestPaginationResult<T> extends RestBaseResult {
	
	private long total;
	
	private long totalPage;
	
	private List<T> rows;
	
	
//	@ApiModelProperty(value = "response message uuid", required = true)
//	private String respUUID = UUIDutil.getUUID();

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

//	public String getRespUUID() {
//		return respUUID;
//	}
//
//	public void setRespUUID(String respUUID) {
//		this.respUUID = respUUID;
//	}

	public long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}

	

}
