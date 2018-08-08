package com.smdm.bean;

import java.util.Date;

public class Comment {
    private Integer id;

    private String comment;

    private Date comTime;

    private Integer userId;

    private String replication;

    private Date replyTime;

    private Integer adminId;
    
    private User user;
    
    private Admin admin;

    public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comment(Integer id, String comment, Date comTime, Integer userId, String replication, Date replyTime,
			Integer adminId) {
		super();
		this.id = id;
		this.comment = comment;
		this.comTime = comTime;
		this.userId = userId;
		this.replication = replication;
		this.replyTime = replyTime;
		this.adminId = adminId;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Date getComTime() {
        return comTime;
    }

    public void setComTime(Date comTime) {
        this.comTime = comTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getReplication() {
        return replication;
    }

    public void setReplication(String replication) {
        this.replication = replication == null ? null : replication.trim();
    }

    public Date getReplyTime() {
        return replyTime;
    }

    public void setReplyTime(Date replyTime) {
        this.replyTime = replyTime;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
    
}