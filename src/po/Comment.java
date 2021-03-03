package po;

import java.util.ArrayList;

public class Comment {

	private Integer id;
	private Integer userId;
	private Integer commodityId;
	private String message;
	private String commentPicture1;
	private String commentPicture2;
	private String commentPicture3;
	private String commentPicture4;
	private String commentPicture5;
	private String commentGrade;
	private ArrayList<String> commentPictures;
	
	public void initCommentPictures() {
		ArrayList<String> commentPictures = new ArrayList<String>();
		commentPictures.add(commentPicture1);
		commentPictures.add(commentPicture2);
		commentPictures.add(commentPicture3);
		commentPictures.add(commentPicture4);
		commentPictures.add(commentPicture5);
		this.commentPictures = new ArrayList<String>();
		for (String commentPicture : commentPictures) {
			if(commentPicture != null) {
				this.commentPictures.add(commentPicture);
			}
		}
	}
	
	public Comment() {
	}

	public Comment(Integer id, Integer userId, Integer commodityId, String message, String commentPicture1,
			String commentPicture2, String commentPicture3, String commentPicture4, String commentPicture5,
			String commentGrade) {
		super();
		this.id = id;
		this.userId = userId;
		this.commodityId = commodityId;
		this.message = message;
		this.commentPicture1 = commentPicture1;
		this.commentPicture2 = commentPicture2;
		this.commentPicture3 = commentPicture3;
		this.commentPicture4 = commentPicture4;
		this.commentPicture5 = commentPicture5;
		this.commentGrade = commentGrade;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCommentPicture1() {
		return commentPicture1;
	}

	public void setCommentPicture1(String commentPicture1) {
		this.commentPicture1 = commentPicture1;
	}

	public String getCommentPicture2() {
		return commentPicture2;
	}

	public void setCommentPicture2(String commentPicture2) {
		this.commentPicture2 = commentPicture2;
	}

	public String getCommentPicture3() {
		return commentPicture3;
	}

	public void setCommentPicture3(String commentPicture3) {
		this.commentPicture3 = commentPicture3;
	}

	public String getCommentPicture4() {
		return commentPicture4;
	}

	public void setCommentPicture4(String commentPicture4) {
		this.commentPicture4 = commentPicture4;
	}

	public String getCommentPicture5() {
		return commentPicture5;
	}

	public void setCommentPicture5(String commentPicture5) {
		this.commentPicture5 = commentPicture5;
	}

	public String getCommentGrade() {
		return commentGrade;
	}

	public void setCommentGrade(String commentGrade) {
		this.commentGrade = commentGrade;
	}

	public ArrayList<String> getCommentPictures() {
		return commentPictures;
	}

	public void setCommentPictures(ArrayList<String> commentPictures) {
		this.commentPictures = commentPictures;
	}

}
