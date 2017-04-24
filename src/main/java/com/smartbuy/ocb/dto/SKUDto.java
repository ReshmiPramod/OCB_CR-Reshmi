package com.smartbuy.ocb.dto;

public class SKUDto {

	private long skuNumber;
	
	private int storeNumber,trkDlvrDays,shelfQty,inStrQty,skuRecThres;
	private String skuDescription,skuVelocity;
	
	public SKUDto(){
		//constructor
	}
	public SKUDto(long skuNumber,int storeNumber,int trkDlvrDays,
						int shelfQty,int inStrQty,int skuRecThres,String skuVelocity){
		this.skuNumber = skuNumber;
		this.storeNumber = storeNumber;
		this.trkDlvrDays = trkDlvrDays;
		this.shelfQty = shelfQty;
		this.inStrQty = inStrQty;
		this.skuRecThres = skuRecThres;
		this.skuVelocity = skuVelocity;
		
	}
	
	public long getSkuNumber() {
		return skuNumber;
	}
	public void setSkuNumber(long skuNumber) {
		this.skuNumber = skuNumber;
	}
	public int getStoreNumber() {
		return storeNumber;
	}
	public void setStoreNumber(int storeNumber) {
		this.storeNumber = storeNumber;
	}
//	public String getSkuDescription() {
//		return skuDescription;
//	}
//	public void setSkuDescription(String skuDescription) {
//		this.skuDescription = skuDescription;
//	}
	public int getTrkDlvrDays() {
		return trkDlvrDays;
	}
	public void setTrkDlvrDays(int trkDlvrDays) {
		this.trkDlvrDays = trkDlvrDays;
	}
	public int getShelfQty() {
		return shelfQty;
	}
	public void setShelfQty(int shelfQty) {
		this.shelfQty = shelfQty;
	}
	public int getInStrQty() {
		return inStrQty;
	}
	public void setInStrQty(int inStrQty) {
		this.inStrQty = inStrQty;
	}
	public int getSkuRecThres() {
		return skuRecThres;
	}
	public void setSkuRecThres(int skuRecThres) {
		this.skuRecThres = skuRecThres;
	}
	public String getSkuVelocity() {
		return skuVelocity;
	}
	public void setSkuVelocity(String skuVelocity) {
		this.skuVelocity = skuVelocity;
	}



}
