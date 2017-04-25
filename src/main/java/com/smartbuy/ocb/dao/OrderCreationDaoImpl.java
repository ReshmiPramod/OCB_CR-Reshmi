package com.smartbuy.ocb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.smarbuy.ocb.exceptions.OcbException;
import com.smartbuy.ocb.dto.OrderDTO;
import com.smartbuy.ocb.dto.SKUDto;

public class OrderCreationDaoImpl implements IOrderCreationDAO {
	
	private DAOFactory daoFactory;
	Connection con;
	List<SKUDto> skuList = new ArrayList<SKUDto>();

	OrderCreationDaoImpl(DAOFactory daoFactory) {
		// TODO Auto-generated constructor stub
		this.daoFactory = daoFactory;
	}


	public List<SKUDto> getSkusFromStore(int storeNumber) throws OcbException {
		PreparedStatement ps = null;
	try {
		
		// Prepare Statement
		con = daoFactory.getConnection();
		ps = con.prepareStatement(getSkusfromStore);
		ps.setInt(1, storeNumber);
		ResultSet rs = ps.executeQuery();
		int i;

		while (rs.next()) {
			i=0;
			SKUDto skus = new SKUDto();
			skus.setSkuNumber(rs.getLong("SKU_NUMBER"));	
			skus.setStoreNumber(rs.getInt("STORE_NUM"));
			skus.setSkuVelocity(rs.getString("SKU_VELOCITY"));
			skus.setTrkDlvrDays(rs.getInt("TRK_DLVR_TIME_DAYS"));
			skus.setShelfQty(rs.getInt("SHELF_QTY"));
			skus.setInStrQty(rs.getInt("IN_STR_QTY"));
			skus.setSkuRecThres(rs.getInt("SKU_RCMD_THRD"));
			skuList.add(i, skus);
			i++;
		//	skuList.add(skus);
		}
	} catch (Exception e) {
		throw new OcbException(e.getMessage(), e);
				}
		finally{
			daoFactory.closeConnection(con);
				}

	return skuList;
	}

	public int getPurchaseOrderNum() throws OcbException {
		OrderDTO PONum = new OrderDTO();
		PreparedStatement psSelect = null;
		PreparedStatement psUpdate = null;
		ResultSet rs = null;
		int poNum =0;
		try{
	//		con = dB.getConnection();
			con = daoFactory.getConnection();
		    con.setAutoCommit(false);
		
		psSelect = con.prepareStatement(getPONumber);
		rs = psSelect.executeQuery();
		while(rs.next()){
			PONum.setPONumber(rs.getInt("LAST_PO_NUM"));
//			PONum.getPONumber() = PONum.getPONumber() + 1;
			poNum = PONum.getPONumber();
			poNum = poNum + 1;
		}
		if(!PONum.equals(null)){
			psUpdate = con.prepareStatement(updatePONumber);
			psUpdate.setInt(1,poNum);
			psUpdate.setInt(2, PONum.getPONumber());
			
			psUpdate.executeUpdate();
			System.out.println(poNum);
			con.commit();
		}
		}catch (Exception e) {
			throw new OcbException(e.getMessage(), e);
					}
			finally{
				daoFactory.closeConnection(con);
					}
		return poNum;
	}

	public boolean updateOrderCreation(SKUDto list, int orderQty, int poNum) throws OcbException {
		try{

//			con = dB.getConnection();
			con = daoFactory.getConnection();
			PreparedStatement psInsert = null;
			psInsert = con.prepareStatement(insertValues);
			String poValue = Integer.toString(poNum);
			psInsert.setString(1, poValue);
			psInsert.setLong(2, list.getSkuNumber());
			psInsert.setInt(3, list.getStoreNumber());
			psInsert.setInt(4, orderQty);
			psInsert.setInt(5, 0);
			
			psInsert.executeUpdate();
			psInsert.close();
			
		}catch (Exception e) {
			throw new OcbException(e.getMessage(), e);
		}
			finally{
				daoFactory.closeConnection(con);
					}
		
		return true;
	}

}
