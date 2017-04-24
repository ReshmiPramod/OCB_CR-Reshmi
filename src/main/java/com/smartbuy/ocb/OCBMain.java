package com.smartbuy.ocb;

import java.util.List;

import com.smartbuy.ocb.bo.OrderCreationBatchBO;
import com.smartbuy.ocb.dto.SKUDto;

public class OCBMain {
	// Rishi - Log4j implementation missing
	public OCBMain() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws Exception {
		// Rishi - Why initialization with null?
		OrderCreationBatchBO orderBo = null	;
		 orderBo = new OrderCreationBatchBO();
		// Rishi - can you take store from main argument array
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


