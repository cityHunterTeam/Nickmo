package tourPackage;

import java.sql.Timestamp;

public class tourVO {

	private String id;
	private int num;
	private String image;
	private String title;
	private String content;
	private Timestamp date;
	private int pos;
	private int dept;
	private int readcount;

	public tourVO(String id, String title, String content) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
	}



	public tourVO(String id, String image, String title, String content) {
		super();
		this.id = id;
		this.image = image;
		this.title = title;
		this.content = content;
	}

	public tourVO() {
	}



	public int getReadcount() {
		return readcount;
	}

	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public int getDept() {
		return dept;
	}
	
	public void setDept(int dept) {
		this.dept = dept;
	}
}
