/**
* <pre>
* com.pcwk.ehr.board.domain
* Class Name : FireBoard.java
* Description:
* Author: ITSC
* Since: 2022/06/24
* Version 0.1
* Copyright (c) by H.R.KIM All right reserved.
* Modification Information
* 수정일   수정자    수정내용
*-----------------------------------------------------
*2022/06/24 최초생성
*-----------------------------------------------------
* </pre>
*/
package com.pcwk.ehr.board.domain;

import com.pcwk.ehr.cmn.DTO;

/**
 * @author ITSC
 *
 */
public class FireBoardVO extends DTO {
	
	private int    bSeq; 	  //순번
	private int    bDiv; 	  //게시판구분
	private String bTitle; 	  //게시글제목
	private String bContents; //내용
	private String pNum; 	  //이미지파일번호
	private int    bReadcnt;  //조회수
	private String regDt; 	  //등록일
	private String regId; 	  //등록자
	private String modDt; 	  //수정일
	private String modId; 	  //수정자
	
	public FireBoardVO() {}

	public FireBoardVO(int bSeq, int bDiv, String bTitle, String bContents, String pNum, int bReadcnt, String regDt,
			String regId, String modDt, String modId) {
		super();
		this.bSeq = bSeq;
		this.bDiv = bDiv;
		this.bTitle = bTitle;
		this.bContents = bContents;
		this.pNum = pNum;
		this.bReadcnt = bReadcnt;
		this.regDt = regDt;
		this.regId = regId;
		this.modDt = modDt;
		this.modId = modId;
	}

	public int getbSeq() {
		return bSeq;
	}

	public void setbSeq(int bSeq) {
		this.bSeq = bSeq;
	}

	public int getbDiv() {
		return bDiv;
	}

	public void setbDiv(int bDiv) {
		this.bDiv = bDiv;
	}

	public String getbTitle() {
		return bTitle;
	}

	public void setbTitle(String bTitle) {
		this.bTitle = bTitle;
	}

	public String getbContents() {
		return bContents;
	}

	public void setbContents(String bContents) {
		this.bContents = bContents;
	}

	public String getpNum() {
		return pNum;
	}

	public void setpNum(String pNum) {
		this.pNum = pNum;
	}

	public int getbReadcnt() {
		return bReadcnt;
	}

	public void setbReadcnt(int bReadcnt) {
		this.bReadcnt = bReadcnt;
	}

	public String getRegDt() {
		return regDt;
	}

	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public String getModDt() {
		return modDt;
	}

	public void setModDt(String modDt) {
		this.modDt = modDt;
	}

	public String getModId() {
		return modId;
	}

	public void setModId(String modId) {
		this.modId = modId;
	}

	@Override
	public String toString() {
		return "FireBoardVO [bSeq=" + bSeq + ", bDiv=" + bDiv + ", bTitle=" + bTitle + ", bContents=" + bContents
				+ ", pNum=" + pNum + ", bReadcnt=" + bReadcnt + ", regDt=" + regDt + ", regId=" + regId + ", modDt="
				+ modDt + ", modId=" + modId + ", toString()=" + super.toString() + "]";
	}
	
}
