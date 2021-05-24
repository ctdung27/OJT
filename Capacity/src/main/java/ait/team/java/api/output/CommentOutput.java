package ait.team.java.api.output;

import java.util.ArrayList;
import java.util.List;

import ait.team.java.dto.CommentDTO;

public class CommentOutput {
	private int page;
	private int totalPage;
	private List<CommentDTO> listResult = new ArrayList<>();
	
	public int getPage() {
		return page;
	}
	
	public void setPage(int page) {
		this.page = page;
	}
	
	public int getTotalPage() {
		return totalPage;
	}
	
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<CommentDTO> getListResult() {
		return listResult;
	}

	public void setListResult(List<CommentDTO> listResult) {
		this.listResult = listResult;
	}

	
}
