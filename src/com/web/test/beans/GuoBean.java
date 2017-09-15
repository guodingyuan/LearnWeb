package com.web.test.beans;

public class GuoBean {
	private int id;
	private String name;
	private int age;
	private boolean sex;
	private String createTime;
	private String updateTime;
	
	public GuoBean() {
		super();
	}

	public GuoBean(String name, int age, boolean sex) {
		super();
		this.name = name;
		this.age = age;
		this.sex = sex;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "GuoBean [id=" + id + ", name=" + name + ", age=" + age + ", sex=" + sex + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + "]";
	}

	
}
