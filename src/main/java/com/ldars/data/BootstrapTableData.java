package com.ldars.data;

public class BootstrapTableData {
    private Object rows;

    private Long total; //总记录数

    private Integer page; //页数
    
    private Integer pageSize; //页数
    
    

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Object getRows() {
		return rows;
	}

	public void setRows(Object rows) {
		this.rows = rows;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}
    
    
  
}