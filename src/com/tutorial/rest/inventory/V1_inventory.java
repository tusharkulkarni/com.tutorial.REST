package com.tutorial.rest.inventory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONArray;

import com.tutorial.rest.dao.TestDAO;
import com.tutorial.util.ToJSON;

@Path("/v1/inventory/")
public class V1_inventory {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String returnAllPcParts() throws SQLException{
		PreparedStatement query = null;
		Connection conn = null;
		String retstr = null;
		
		try{
			conn =TestDAO.getConn();
			System.out.println("*************************method invoked*************************");
			query = conn.prepareStatement("select * from PC_PARTS");
			ResultSet rs = query.executeQuery();
			String str = "";
			
			ToJSON converter = new ToJSON();
			JSONArray jsonArray = new JSONArray();
			jsonArray = converter.toJSONArray(rs);
			System.out.println(jsonArray.toString());
			retstr = jsonArray.toString();
			//retstr = str;
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return retstr;
		
	}
}
