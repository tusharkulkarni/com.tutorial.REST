package com.tutorial.rest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.codehaus.jettison.json.JSONArray;

import com.tutorial.util.ToJSON;

public class SchemaDAO {

	public JSONArray queryReturnBrandParts(String brand) throws Exception{
		PreparedStatement query = null;
		Connection conn = null;
		ToJSON converter = new ToJSON();
		JSONArray json = new JSONArray();
		String queryString = "select PC_PARTS_PK, PC_PARTS_TITLE, PC_PARTS_CODE, PC_PARTS_MAKER, PC_PARTS_AVAIL, PC_PARTS_DESC from PC_PARTS where UPPER(PC_PARTS_MAKER) = ? ";
		try{
			conn = TestDAO.getConn();
			query = conn.prepareStatement(queryString);
			query.setString(1, brand.toUpperCase()); //protect against sql injection
			ResultSet rs = query.executeQuery();
			
			json = converter.toJSONArray(rs);
			query.close(); //close connection
		}catch(Exception e){
			e.printStackTrace();
			return json;
		}
		return json;
	}

}
