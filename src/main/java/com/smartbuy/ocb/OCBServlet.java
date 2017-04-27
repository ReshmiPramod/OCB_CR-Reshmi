package com.smartbuy.ocb;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import com.smarbuy.ocb.exceptions.OcbException;
import com.smartbuy.ocb.bo.OrderCreationBatchBO;
import com.smartbuy.ocb.dto.SKUDto;

/**
 * Servlet implementation class OCBServlet
 */

public class OCBServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderCreationBatchBO orderBo;
	private static Logger logger = Logger.getLogger(OCBServlet.class);

	public OCBServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int intStoreNum = 0;
		int param = 1;
		PrintWriter writer = response.getWriter();
		try {		
				String storeNumber = request.getParameter("storeNumber");
				logger.debug("Store Number parameter value ::" + storeNumber);
				response.setContentType("text/HTML");
				
				if (storeNumber != null) {
					intStoreNum = Integer.parseInt(storeNumber);
					orderBo = new OrderCreationBatchBO(param);
					List<SKUDto> skus = orderBo.fetchSkuList(intStoreNum);
						if (skus != null && skus.size() > 0) {
							orderBo.executeOrderCreation(skus);
						   }
					}
			} catch (OcbException exe) {
				logger.error("OCB Exception ::" + exe.getMessage(), exe);
				writer.println("OCB Exception ::" + exe.getMessage());
				}

				catch (Exception e) {
			// TODO: handle exception
						logger.error("OCB Exception ::" + e.getMessage(), e);
						writer.println("OCB Exception ::" + e.getMessage());
						}
						response.flushBuffer();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
