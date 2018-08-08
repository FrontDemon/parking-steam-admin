package com.smdm.bean;

import java.util.Date;

public class Orders {
    private Integer id;

    private Integer userId;

    private Integer lotId;

    private Date orderTime;

    private Double price;

    private Double total;

    private Integer status;

    private User user;
    
    private Lot lot;
    
    public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Orders(Integer id, Integer userId, Integer lotId, Date orderTime, Double price, Double total,
			Integer status) {
		super();
		this.id = id;
		this.userId = userId;
		this.lotId = lotId;
		this.orderTime = orderTime;
		this.price = price;
		this.total = total;
		this.status = status;
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

    public Integer getLotId() {
        return lotId;
    }

    public void setLotId(Integer lotId) {
        this.lotId = lotId;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Lot getLot() {
		return lot;
	}

	public void setLot(Lot lot) {
		this.lot = lot;
	}
    
}