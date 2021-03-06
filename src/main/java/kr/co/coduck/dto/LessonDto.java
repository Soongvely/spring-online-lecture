package kr.co.coduck.dto;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class LessonDto {

	private int lectNo;
	private int lastTime;
	private int percent;		// 진행률
	private int totalTime;		// 총 레슨 시간
	private int totalCount;		// 총 레슨 개수
	private int viewCount;		// 수강생이 끝까지본 레슨의(is_watched = 'Y' 인) 총 개수 
	private int totalAccumulate;// 수강생이 시청이력이 있는 레슨들의 총 누적시간
	private String newTotalTime;
	private int userNo;
	private int accumulateTime;
	private String lectTitle;
	private String imagePath;
	private Date lectCreateDate;
	
	public LessonDto() {}
	
	public int getTotalCount() {
		return totalCount;
	}
	
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
	public int getTotalTime() {
		return totalTime;
	}
	
	public void setTotalTime(int totalTime) {
		this.totalTime = totalTime;
	}
	
	public int getLectNo() {
		return lectNo;
	}
	
	public void setLectNo(int lectNo) {
		this.lectNo = lectNo;
	}
	
	public int getLastTime() {
		return lastTime;
	}

	public void setLastTime(int lastTime) {
		this.lastTime = lastTime;
	}

	public int getPercent() {
		return percent;
	}
	
	public void setPercent(int percent) {
		this.percent = percent;
	}
	
	public int getViewCount() {
		return viewCount;
	}
	
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
	
	public int getTotalAccumulate() {
		return totalAccumulate;
	}
	
	public void setTotalAccumulate(int totalAccumulate) {
		this.totalAccumulate = totalAccumulate;
	}
	
	public String getNewTotalAccumulate() {
		
		int needMinutes = totalAccumulate;
		long hour = TimeUnit.MINUTES.toHours(needMinutes); 
		long minutes = TimeUnit.MINUTES.toMinutes(needMinutes) - TimeUnit.HOURS.toMinutes(hour); 
		
		return hour + ":" + minutes;
	}
	
	public String getNewTotalTime() {
		return newTotalTime;
	}

	public void setNewTotalTime(String newTotalTime) {
		this.newTotalTime = newTotalTime;
	}

	public long getHourByTotalAccumulate() {
		
		int needMinutes = totalAccumulate;
		long hour = TimeUnit.MINUTES.toHours(needMinutes); 
		return hour;
	}
	
	public long getMinutesTotalAccumulate() {	
		int needMinutes = totalAccumulate;
		long hour = TimeUnit.MINUTES.toHours(needMinutes); 
		long minutes = TimeUnit.MINUTES.toMinutes(needMinutes) - TimeUnit.HOURS.toMinutes(hour); 
		
		return minutes;
	}
	
	
	@Override
	public String toString() {
		return "LessonDto [lectNo=" + lectNo + ", percent=" + percent + ", totalCount=" + totalCount + ", totalTime="
				+ totalTime + ", viewCount=" + viewCount + ", totalAccumulate=" + totalAccumulate + "]";
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public int getAccumulateTime() {
		return accumulateTime;
	}

	public void setAccumulateTime(int accumulateTime) {
		this.accumulateTime = accumulateTime;
	}

	public String getLectTitle() {
		return lectTitle;
	}

	public void setLectTitle(String lectTitle) {
		this.lectTitle = lectTitle;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Date getLectCreateDate() {
		return lectCreateDate;
	}

	public void setLectCreateDate(Date lectCreateDate) {
		this.lectCreateDate = lectCreateDate;
	}
	
	
}
