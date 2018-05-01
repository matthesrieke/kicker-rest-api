package org.n52.spare.kicker.model;

import java.util.List;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonView;

public class PageableResponse<T> {
	
	@JsonView(Views.Basic.class)
	private Integer total;
	
	@JsonView(Views.Basic.class)
	private Integer page;
	
	@JsonView(Views.Basic.class)
	private Integer size;
	
	@JsonView(Views.Basic.class)
	private List<T> data;

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public static <T> PageableResponse<T> from(Page<T> pageable) {
		PageableResponse<T> response = new PageableResponse<T>();
		response.setData(pageable.getContent());
		response.setPage(pageable.getNumber());
		response.setSize(pageable.getSize());
		response.setTotal(pageable.getTotalPages() * pageable.getSize());
		return response;
	}
	
}
