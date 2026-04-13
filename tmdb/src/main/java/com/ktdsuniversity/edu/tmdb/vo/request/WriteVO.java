package com.ktdsuniversity.edu.tmdb.vo.request;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotEmpty;

public class WriteVO {
	
	private String movieId;
	@NotEmpty(message="포스터 url이 없습니다")
	private String posterUrl;
	@NotEmpty(message="제목이 없습니다")
	private String title;
	private String movieRating;
	private LocalDate openDate;
	private String openCountry;
	private Integer runningTime;
	private String introduce;
	@NotEmpty(message="줄거리를 입력하세요")
	private String synopsis;
	private String originalTitle;
	@NotEmpty(message="개봉상태가 필요합니다")
	private String state;
	@NotEmpty(message="언어를 적으세요")
	private String language;
	private Integer budget;
	private Integer profit;
	
	private List<MultipartFile> attachFiles;
	
	
	
	
	public List<MultipartFile> getAttachFiles() {
		return this.attachFiles;
	}
	public void setAttachFiles(List<MultipartFile> attachFiles) {
		this.attachFiles = attachFiles;
	}
	public String getMovieId() {
		return this.movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public String getPosterUrl() {
		return this.posterUrl;
	}
	public void setPosterUrl(String posterUrl) {
		this.posterUrl = posterUrl;
	}
	public String getTitle() {
		return this.title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMovieRating() {
		return this.movieRating;
	}
	public void setMovieRating(String movieRating) {
		this.movieRating = movieRating;
	}
	public LocalDate getOpenDate() {
		return this.openDate;
	}
	public void setOpenDate(LocalDate openDate) {
		this.openDate = openDate;
	}
	public String getOpenCountry() {
		return this.openCountry;
	}
	public void setOpenCountry(String openCountry) {
		this.openCountry = openCountry;
	}
	public Integer getRunningTime() {
		return this.runningTime;
	}
	public void setRunningTime(Integer runningTime) {
		this.runningTime = runningTime;
	}
	public String getIntroduce() {
		return this.introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getSynopsis() {
		return this.synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	public String getOriginalTitle() {
		return this.originalTitle;
	}
	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}
	public String getState() {
		return this.state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getLanguage() {
		return this.language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public Integer getBudget() {
		return budget;
	}
	public void setBudget(Integer budget) {
		this.budget = budget;
	}
	public Integer getProfit() {
		return profit;
	}
	public void setProfit(Integer profit) {
		this.profit = profit;
	}
	
	

}
