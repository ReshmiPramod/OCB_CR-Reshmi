package com.smartbuy.ocb.bo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.smarbuy.ocb.exceptions.OcbException;
import com.smartbuy.ocb.OCBServlet;
import com.smartbuy.ocb.dao.DAOFactory;
import com.smartbuy.ocb.dao.IOrderCreationDAO;
import com.smartbuy.ocb.dto.OrderDTO;
import com.smartbuy.ocb.dto.SKUDto;

public class OrderCreationBatchBO {
	private IOrderCreationDAO dao;
	private DAOFactory dB;
	private static Logger logger = Logger.getLogger(OrderCreationBatchBO.class);

	
	// Rishi - is it thread safe to keep skuList as instance variable. Can we
	// make it local variable instead?
	List<SKUDto> skuList = new ArrayList<SKUDto>();
	OrderDTO dto = new OrderDTO();

	int poNum = 0;

	public OrderCreationBatchBO() throws OcbException {
		try {
			dB = DAOFactory.getInstance();
		} catch (Exception e) {
			throw new OcbException(e.getMessage(), e);
		}
	}

	public void setDao() {
		this.dao = dao;
	}

	public List<SKUDto> fetchSkuList(int intStoreNum) throws OcbException {

		try {
			dao = dB.getOrderCreation();
			skuList = dao.getSkusFromStore(intStoreNum);
			if (intStoreNum != 0 && skuList != null) {
				poNum = dao.getPurchaseOrderNum();
			}
		} catch (Exception e) {

			throw new OcbException(e.getMessage(), e);
		}

		return skuList;

	}

	public void executeOrderCreation() throws OcbException {
		OrderDTO dto = new OrderDTO();
		// DAOFactory dB = DAOFactory.getInstance();
		int skuVel = 0;
		dao = dB.getOrderCreation();
		// Rishi - can we take skuList as method argument than instance variable
		for (SKUDto sList : skuList) {

			logger.debug("List of Skudetails :" + sList.getSkuNumber() + " " + sList.getShelfQty() + " "
					+ sList.getInStrQty() + " " + sList.getSkuRecThres());

			int qty = sList.getShelfQty() + sList.getInStrQty();
			System.out.println("Total Quantity :" + qty);

			if (qty < sList.getSkuRecThres()) {

				try {
					skuVel = Integer.parseInt(sList.getSkuVelocity());
				} catch (NumberFormatException e) {
					logger.error("Number format Exception" + e);
				}
				int orderQty = skuVel * sList.getTrkDlvrDays();
				dto.setSkuDto(sList);
				dto.setOrderQty(orderQty);

				boolean value;
				try {
					value = dao.updateOrderCreation(sList, orderQty, poNum);
				} catch (Exception e) {
					throw new OcbException(e.getMessage(), e);
				}
				if (!value) {
					break; // Rishi - what happens when break executes.
				}
			}

		}

	}

}
