package com.smartbuy.ocb;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.smartbuy.ocb.bo.OrderCreationBatchBO;
import com.smartbuy.ocb.dao.DAOFactory;
import com.smartbuy.ocb.dto.SKUDto;



/**
 * Servlet implementation class OCBServlet
 */

public class OCBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OrderCreationBatchBO orderBo = null;
	DAOFactory dataSource;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OCBServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
     /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 int intStoreNum = 0;
		 try{
			// Rishi - Log4j
			String storeNumber = request.getParameter("storeNumber");
			if(storeNumber != null){
				
					intStoreNum = Integer.parseInt(storeNumber);
					 orderBo = new OrderCreationBatchBO();
					List<SKUDto> skus = orderBo.fetchSkuList(intStoreNum);
					if(!skus.equals(null)){
					orderBo.executeOrderCreation();
					}
				
			}
			response.getWriter().append("Served at: ").append(request.getContextPath());
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
