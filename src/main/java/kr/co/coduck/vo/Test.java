package kr.co.coduck.vo;

import java.util.Date;

import org.apache.ibatis.type.Alias;


@Alias("Test")
public class Test {
	
	private int rk;
	private int no;
	private int lectNo;
	private Date testDate;	//시험일(년,월)
	private String name;
	private String ep;
	private int price;
	private int passScore;
	private int ltdTime;
	private int qtCnt;
	private int cateNo;
	private String isDisplay;
	private Date createDate;
	private int totalScore;
	
	public Test() {}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getLectNo() {
		return lectNo;
	}

	public void setLectNo(int lectNo) {
		this.lectNo = lectNo;
	}

	public Date getTestDate() {
		return testDate;
	}

	public void setTestDate(Date testDate) {
		this.testDate = testDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEp() {
		return ep;
	}

	public void setEp(String ep) {
		this.ep = ep;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getPassScore() {
		return passScore;
	}

	public void setPassScore(int passScore) {
		this.passScore = passScore;
	}

	public int getLtdTime() {
		return ltdTime;
	}

	public void setLtdTime(int ltdTime) {
		this.ltdTime = ltdTime;
	}

	public int getQtCnt() {
		return qtCnt;
	}

	public void setQtCnt(int qtCnt) {
		this.qtCnt = qtCnt;
	}

	public int getCateNo() {
		return cateNo;
	}

	public void setCateNo(int cateNo) {
		this.cateNo = cateNo;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getIsDisplay() {
		return isDisplay;
	}

	public void setIsDisplay(String isDisplay) {
		this.isDisplay = isDisplay;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

	public int getRk() {
		return rk;
	}

	public void setRk(int rk) {
		this.rk = rk;
	}

	@Override
	public String toString() {
		return "Test [rk=" + rk + ", no=" + no + ", lectNo=" + lectNo + ", testDate=" + testDate + ", name=" + name
				+ ", ep=" + ep + ", price=" + price + ", passScore=" + passScore + ", ltdTime=" + ltdTime + ", qtCnt="
				+ qtCnt + ", cateNo=" + cateNo + ", isDisplay=" + isDisplay + ", createDate=" + createDate
				+ ", totalScore=" + totalScore + "]";
	}
	
}
