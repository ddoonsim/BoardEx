package com.login.dto;

public class PageDto {
	
	// 페이지블럭을 생성하기 위해 필요한 번수
   	int startNo;
   	int endNo;
   	int realEnd;
	boolean prev = false ;
	boolean next = true ;
	
	// 페이지 블럭을 생성하기 위해 필요한 변수에 값을 넣기 위해 필요한 값
	int totalCnt ;
	Criteria cri ;             // 페이지 번호, 페이지 당 게시물 개수
	int blockAmount = 10 ;     // 하나의 페이지 블럭에서 보여줄 페이지의 개수
	
	/**
	 * 생성자
	 * @param totalCnt
	 * @param cri
	 */
	public PageDto(int totalCnt, Criteria cri) {
		this.totalCnt = totalCnt;
		this.cri = cri;
		
		endNo = (int)(Math.ceil(cri.getPageNo()/(blockAmount * 1.0)) * blockAmount) ;
		startNo = endNo - (blockAmount - 1) ;
		
		realEnd = (int)(Math.ceil(totalCnt / (cri.getAmount() * 1.0))) ;
		
		// 게시글 66건일 경우 끝페이지 번호는 7페이지
		// 하지만 페이지블럭의 끝번호는 10으로 계산이 되므로 끝 번호를 다시 설정할 필요가 있음
		endNo = (endNo > realEnd) ? realEnd : endNo ; 
		
		// 페이지 블럭 이동 버튼 활성화 여부
		prev = (startNo > 1) ? true : false ;
		next = (endNo < realEnd) ? true : false ;
		
	}
	public PageDto(int totalCnt, Criteria cri, String blockAmount) {
		// 생성자 호출
		this(totalCnt, cri) ;
		if(blockAmount != null && !"".equals(blockAmount)) {
			this.blockAmount = Integer.parseInt(blockAmount);
		}
	}
	
	
	public int getStartNo() {
		return startNo;
	}
	public void setStartNo(int startNo) {
		this.startNo = startNo;
	}
	public int getEndNo() {
		return endNo;
	}
	public void setEndNo(int endNo) {
		this.endNo = endNo;
	}
	public int getRealEnd() {
		return realEnd;
	}
	public void setRealEnd(int realEnd) {
		this.realEnd = realEnd;
	}
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public int getTotalCnt() {
		return totalCnt;
	}
	public void setTotalCnt(int totalCnt) {
		this.totalCnt = totalCnt;
	}
	public Criteria getCri() {
		return cri;
	}
	public void setCri(Criteria cri) {
		this.cri = cri;
	}
	public int getBlockAmount() {
		return blockAmount;
	}
	public void setBlockAmount(int blockAmount) {
		this.blockAmount = blockAmount;
	}

}
