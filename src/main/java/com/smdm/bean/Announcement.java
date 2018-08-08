package com.smdm.bean;

import java.util.Date;

public class Announcement {
    private Integer id;

    private String title;

    private String context;

    private Date createTime;

    private Integer adminId;
    
    private Admin admin;//管理员实体
    
    public Announcement() {
		super();
	}

	public Announcement(Integer id, String title, String context, Date createTime, Integer adminId) {
		super();
		this.id = id;
		this.title = title;
		this.context = context;
		this.createTime = createTime;
		this.adminId = adminId;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context == null ? null : context.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getAdminId() {
        return adminId;
    }

    public void setAdminId(Integer adminId) {
        this.adminId = adminId;
    }
}