package fr.gemeroi.persistence.bean.generated;

public interface Language {

	public Integer getId();
	public Entityvideo getEntityvideo();
	public String getExpression();
	public Integer getTimebegin();
	public Integer getTimeend();
	public Integer getRank();
	public void setExpression(String expression);
	public void setRank(Integer rank);
	public void setTimebegin(Integer timeBegin);
	public void setTimeend(Integer timeEnd);
	public void setEntityvideo(Entityvideo entityVideo);
}
