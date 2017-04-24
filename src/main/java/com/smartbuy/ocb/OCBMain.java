package com.smartbuy.ocb;

import java.util.List;

import com.smartbuy.ocb.bo.OrderCreationBatchBO;
import com.smartbuy.ocb.dto.SKUDto;

public class OCBMain {
		
	public OCBMain() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		OrderCreationBatchBO orderBo = null	;
		
		
		 orderBo = new OrderCreationBatchBO();
		List<SKUDto> skus = orderBo.fetchSkuList(501);
		
			try {
				if(!skus.equals(null)){
				orderBo.executeOrderCreation();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
	  }		
	}


