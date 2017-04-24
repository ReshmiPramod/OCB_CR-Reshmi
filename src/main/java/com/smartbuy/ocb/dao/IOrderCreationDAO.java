package com.smartbuy.ocb.dao;

import java.sql.Connection;
import java.util.List;

import com.smartbuy.ocb.dto.SKUDto;


public interface IOrderCreationDAO {
	
	String getSkusfromStore = ""
			+ "SELECT `sku_store`.`SKU_NUMBER`, "
			+ "    `sku_store`.`STORE_NUM`, "
			+ "    `sku_store`.`SKU_VELOCITY`, "
			+ "    `sku_store`.`TRK_DLVR_TIME_DAYS`, "
			+ "    `sku_store`.`SHELF_QTY`, "
			+ "    `sku_store`.`IN_STR_QTY`, "
			+ "    `sar_parm`.`SKU_RCMD_THRD` "
			+ "FROM `retail`.`sku_store`,`retail`.`sar_parm` "
			+ "where `sku_store`.`SKU_NUMBER` = `sar_parm`.`SKU_NUMBER` "
			+ "and `sku_store`.`STORE_NUM` = ?;";
	
	String getPONumber = ""
			+ "SELECT `po_number`.`LAST_PO_NUM` "
			+ "FROM `retail`.`po_number`;";
	
	String updatePONumber = ""
			+ "UPDATE `retail`.`po_number` "
			+ "SET `LAST_PO_NUM` = ? "
			+ "WHERE `LAST_PO_NUM` = ?";

	String insertValues = ""
			+ "INSERT INTO `retail`.`sar_po` "
			+ "(`PO_NUMBER`, "
			+ "`SKU_NUMBER`, "
			+ "`STR_NUMBER`, "
			+ "`ORDR_QTY`, "
			+ "`IS_APPROVED`) "
			+ "VALUES (?,?,?,?,?);";

	
	public List<SKUDto> getSkusFromStore(int storeNumber) throws Exception;

	public int getPurchaseOrderNum() throws Exception;
 
	public boolean updateOrderCreation(SKUDto list,int orderQty,int poNum) throws Exception;

		
	
}
