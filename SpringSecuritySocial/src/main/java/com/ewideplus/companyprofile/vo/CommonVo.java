package com.ewideplus.companyprofile.vo;

public class CommonVo{

	private String seq;
	private String regId;
	private String regIp;
	private String regDate;
	private String modId;
	private String modIp;
	private String modDate;
	private String delYn;
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getRegId() {
		return regId;
	}
	public void setRegId(String regId) {
		this.regId = regId;
	}
	public String getRegIp() {
		return regIp;
	}
	public void setRegIp(String regIp) {
		this.regIp = regIp;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	public String getModId() {
		return modId;
	}
	public void setModId(String modId) {
		this.modId = modId;
	}
	public String getModIp() {
		return modIp;
	}
	public void setModIp(String modIp) {
		this.modIp = modIp;
	}
	public String getModDate() {
		return modDate;
	}
	public void setModDate(String modDate) {
		this.modDate = modDate;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
	
	public String toString() {
        return "Common [seq=" + seq 
        		+ ", regId=" + regId + ", regIp=" + regIp + ", regDate=" + regDate  
        		+ ", modId=" + modId + ", modIp=" + modIp + ", modDate=" + modDate  
        		+ ", delYn=" + delYn + "]";
	}

}
