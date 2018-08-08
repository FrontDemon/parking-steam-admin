package com.smdm.bean;

public class Lot {
    private Integer id;

    private String number;

    private Double price;

    private String address;

    private Integer status;

    public Lot() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Lot(Integer id, String number, Double price, String address, Integer status) {
		super();
		this.id = id;
		this.number = number;
		this.price = price;
		this.address = address;
		this.status = status;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number == null ? null : number.trim();
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}