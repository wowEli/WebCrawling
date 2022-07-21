package model;

public class WebtoonVO {
	private int wid;
	private String title;
	private String author;
	private int cnt;
	
	public int getWid() {
		return wid;
	}
	public void setWid(int wid) {
		this.wid = wid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	@Override
	public String toString() {
		return "WebtoonVO [wid=" + wid + ", title=" + title + ", author=" + author + ", cnt=" + cnt + "]";
	}
}
