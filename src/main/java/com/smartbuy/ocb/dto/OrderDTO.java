package com.smartbuy.ocb.dto;

public class OrderDTO {
	
	public OrderDTO() {
		// TODO Auto-generated constructor stub
	}

	private SKUDto skuDto;
	private int orderQty;
	private int PONumber;
	
	
	public SKUDto getSkuDto() {
		return skuDto;
	}
	public void setSkuDto(SKUDto skuDto) {
		this.skuDto = skuDto;
	}
	public int getOrderQty() {
		return orderQty;
	}
	public void setOrderQty(int orderQty) {
		this.orderQty = orderQty;
	}
	public int getPONumber() {
		return PONumber;
	}
	public void setPONumber(int pONumber) {
		PONumber = pONumber;
	}
	
}



