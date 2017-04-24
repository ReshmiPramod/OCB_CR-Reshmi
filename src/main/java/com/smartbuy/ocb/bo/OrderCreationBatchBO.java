package com.smartbuy.ocb.bo;


import java.util.ArrayList;
import java.util.List;

import com.smartbuy.ocb.dao.DAOFactory;
import com.smartbuy.ocb.dao.IOrderCreationDAO;
import com.smartbuy.ocb.dto.OrderDTO;
import com.smartbuy.ocb.dto.SKUDto;

public class OrderCreationBatchBO {
//	private IOrderCreationDAO dao;
	List<SKUDto> skuList = new ArrayList<SKUDto>();
	OrderDTO dto = new OrderDTO();
//	OrderCreationDaoImpl dao = new OrderCreationDaoImpl() ;
//	private OrderCreationDaoImpl dao;
	int poNum = 0;

	public OrderCreationBatchBO() {
		
		}

//	public void setDao(OrderCreationDaoImpl dao) {
//		this.dao = dao;
//	}
//	public void setDao(IOrderCreationDAO dao) {
//		this.dao = dao;
//	}
	public List<SKUDto> fetchSkuList(int intStoreNum) {
		
		try {
			DAOFactory dB = DAOFactory.getInstance();
			IOrderCreationDAO dao = dB.getOrderCreation();
			skuList = dao.getSkusFromStore(intStoreNum);
			if(intStoreNum != 0){
				poNum = dao.getPurchaseOrderNum();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return skuList;
		
	}
	
	public void executeOrderCreation() throws Exception {
		OrderDTO dto = new OrderDTO();
		DAOFactory dB = DAOFactory.getInstance();
		IOrderCreationDAO dao = dB.getOrderCreation();
		
		for (SKUDto sList : skuList) {
			System.out.println("List of Skudetails :" + sList.getSkuNumber() + " " + sList.getShelfQty() + " "
					+ sList.getInStrQty() + " " + sList.getSkuRecThres());

			int qty = sList.getShelfQty() + sList.getInStrQty();
			System.out.println("Total Quantity :" + qty);

			if (qty < sList.getSkuRecThres()) {
				// //order quantity calc
				int skuVel = Integer.parseInt(sList.getSkuVelocity());
				int orderQty = skuVel * sList.getTrkDlvrDays();
				dto.setSkuDto(sList);
				dto.setOrderQty(orderQty);
				
				boolean value =	dao.updateOrderCreation(sList,orderQty,poNum);
					if(!value){
						break;
					}
			}
			
		}

	}

}
