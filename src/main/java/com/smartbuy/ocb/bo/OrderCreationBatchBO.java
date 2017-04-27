package com.smartbuy.ocb.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.smarbuy.ocb.exceptions.OcbException;
import com.smartbuy.ocb.dao.IOrderCreationDAO;
import com.smartbuy.ocb.dao.OrderCreationDaoImpl;
import com.smartbuy.ocb.dto.OrderDTO;
import com.smartbuy.ocb.dto.SKUDto;

public class OrderCreationBatchBO {
	private IOrderCreationDAO dao;
	private static Logger logger = Logger.getLogger(OrderCreationBatchBO.class);
	int poNum = 0;

	public OrderCreationBatchBO(int param){
		dao = new OrderCreationDaoImpl(param);
		}
	
	//get the list of skus by store number
	public List<SKUDto> fetchSkuList(int intStoreNum) throws OcbException {
		List<SKUDto> skuList = new ArrayList<SKUDto>();
		
		try {
			if(dao != null){
				skuList = dao.getSkusFromStore(intStoreNum);
					if (intStoreNum != 0 && skuList != null) {
						poNum = dao.getPurchaseOrderNum();  //get the last PO number
					  }
			  } 
			}catch (Exception e) {
				throw new OcbException(e.getMessage(), e);
			  }

		return skuList;

	}

	public void executeOrderCreation(List<SKUDto> skuList) throws OcbException {
		OrderDTO dto = new OrderDTO();
		int skuVel = 0;
		for (SKUDto sList : skuList) {

			logger.debug("List of Skudetails :" + sList.getSkuNumber() + " " + sList.getShelfQty() + " "
					+ sList.getInStrQty() + " " + sList.getSkuRecThres());

			int qty = sList.getShelfQty() + sList.getInStrQty();
			logger.debug("Total Quantity :" + qty);

			if (qty < sList.getSkuRecThres()) {

				try {
					skuVel = Integer.parseInt(sList.getSkuVelocity());
				} catch (NumberFormatException e) {
					logger.error("Number format Exception", e);
					throw new OcbException(e.getMessage());
				}
				int orderQty = skuVel * sList.getTrkDlvrDays();
				dto.setSkuDto(sList);
				dto.setOrderQty(orderQty);

				try {
					dao.updateOrderCreation(sList, orderQty, poNum);
				} catch (Exception e) {
					throw new OcbException(e.getMessage(), e);
				}
			}

		}

	}

}
