package com.smartbuy.ocb;


import java.util.List;

import org.apache.log4j.Logger;

import com.smarbuy.ocb.exceptions.OcbException;
import com.smartbuy.ocb.bo.OrderCreationBatchBO;
import com.smartbuy.ocb.dto.SKUDto;

public class OCBMain {
	//  Log4j implementation 
	private static Logger logger = Logger.getLogger(OCBMain.class);
	public OCBMain() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws OcbException {
	
		OrderCreationBatchBO orderBo;
		 orderBo = new OrderCreationBatchBO();
		// Rishi - can you take store from main argument array
		 List<SKUDto> skus = orderBo.fetchSkuList(501);
		
			try {
				if(!skus.equals(null)){
				orderBo.executeOrderCreation();
				}
			} catch (OcbException exe) {
				logger.error("OCB Exception ::" + exe.getMessage(),exe);
				
			}
			catch (Exception e) {
				logger.error("OCB Exception ::" + e.getMessage(),e);
				
		}
	  }		
	}


