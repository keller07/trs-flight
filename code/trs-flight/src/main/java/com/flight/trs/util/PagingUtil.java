package com.flight.trs.util;

/**
 * 
 * @Description: TODO
 * @author le
 * @date 2016年2月5日 下午6:55:25
 * @version V1.0
 */
public class PagingUtil {

	private int pageSize;			//每页显示的记录数
	private int totalRecords;		//总共多少条记录
	private int currentPageNo;		//当前页数
	
	private int totalPages;			//总共多少页
	private int pageSizeOfLastPage;	//最后一页的记录数
	
	public PagingUtil(int pageSize, int totalRecords, int currentPageNo) {
		// TODO
		
		if (pageSize <= 0 || totalRecords < 0 || currentPageNo <= 0) {
			//throws exception
			return;
		}
		
		if (totalRecords == 0) {
			this.totalPages = 1;
			this.pageSizeOfLastPage = 0;
		}
		else {
			//前面满记录的页数
			int basePageCount = totalRecords/pageSize;
			
			//最后一页的记录数
			int pageSizeOfLastPage = totalRecords%pageSize;
			if (pageSizeOfLastPage == 0) {
				this.pageSizeOfLastPage = pageSize;
			}
			else {
				basePageCount++;
				this.pageSizeOfLastPage = pageSizeOfLastPage;
			}
			this.totalPages = basePageCount;
		}
		
		if (currentPageNo > totalPages) {
			//throws exception
			return;
		}
		
		this.pageSize = pageSize;
		this.totalRecords = totalRecords;
		this.currentPageNo = currentPageNo;
		
	}

	public PagingUtil(int pageSize, int recordCount) {
		// 默认初始化时当前页是第1页，调用nextPage后得到第1页
		this(pageSize, recordCount, 1);
	}
	
	//是否有上一页
	public boolean hasPrevPage(){
		return currentPageNo > 1;
	}
	
	//是否还有下一页
	public boolean hasNextPage(){
		return currentPageNo < totalPages;
	}
	
	//获取当前页
	public Page getCurrPage(){
		return getPage(currentPageNo);
	}
	
	//获取下一页
	public Page getNextPage(){
		//如果当前一页已是最后一页
		if ( !hasNextPage() ) {
			return null;
		}
		int pageNo_NextPage = ++currentPageNo;
		Page nextPage = getPage(pageNo_NextPage);
		
		return nextPage;
	}

	//获取上一页
	public Page getPrevPage(){
		//如果当前一页已是第一页
		if ( !hasPrevPage() ) {
			return null;
		}
		
		int pageNo_PrevPage = currentPageNo--;
		Page prevPage = getPage(pageNo_PrevPage);
		
		return prevPage;
	}
	
	//获取首页
	public Page getFirstPage(){
		return getPage(1);
	}
	
	//获取尾页
	public Page getLastPage(){
		return getPage(totalPages);
	}
	
	//查看指定页码的页是否为首页
	public boolean isFirstPage(int pageNo) {
		return pageNo == 1;
	}
	
	//查看指定页码的页是否为尾页
	public boolean isLastPage(int pageNo) {
		return pageNo == totalPages;
	}
	
	//获取指定页码的页
	public Page getPage(int pageNo){
		
		if ( pageNo < 1 || pageNo > totalPages ) {
			return null;
		}
		
		int fromIndex = pageSize * (pageNo-1);
		int recordNumber = isLastPage(pageNo) ? pageSizeOfLastPage : pageSize;
		Page page = new Page(pageNo, fromIndex, recordNumber);
		
		return page;
	}
	
	//---------------------------------------------------------------
	//------------------- getter、setters ---------------------------
	//---------------------------------------------------------------
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}

	public int getCurrentPageNo() {
		return currentPageNo;
	}
	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}
	
	public long getTotalPages(){
		return totalPages;
	}
	public void setTotalPages(int totalPages){
		this.totalPages = totalPages;
	}
	
	public int getPageSizeOfLastPage() {
		return pageSizeOfLastPage;
	}
	public void setPageSizeOfLastPage(int pageSizeOfLastPage) {
		this.pageSizeOfLastPage = pageSizeOfLastPage;
	}
	
	public class Page{
		
		private int pageNo;			//页码
		private int fromIndex;		//从第i条记录开始数(下标从0开始)
		private int recordNumber;	//数几条记录
		
		public Page(){}
		
		public Page(int pageNo, int fromIndex, int recordNumber){
			this.pageNo = pageNo;
			this.fromIndex = fromIndex;
			this.recordNumber = recordNumber;
		}
		
		public int getPageNo() {
			return pageNo;
		}

		public void setPageNo(int pageNo) {
			this.pageNo = pageNo;
		}
		
		public int getFromIndex() {
			return fromIndex;
		}
		public void setFromIndex(int fromIndex) {
			this.fromIndex = fromIndex;
		}
		
		public int getRecordNumber() {
			return recordNumber;
		}
		public void setRecordNumber(int recordNumber) {
			this.recordNumber = recordNumber;
		}

		@Override
		public String toString(){
			StringBuilder sb = new StringBuilder();
			sb.append(getClass().getName())
				.append("@")
				.append(hashCode())
				.append("[ pageNo=")
				.append(pageNo)
				.append(", fromIndex=")
				.append(fromIndex)
				.append(", recordNumber=")
				.append(recordNumber)
				.append(" ]");
			return sb.toString();
		}
		
	}
	
}
