package com.tutorial.rest.inventory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.json.JsonArray;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;

import com.tutorial.rest.dao.SchemaDAO;
import com.tutorial.rest.dao.TestDAO;
import com.tutorial.util.ToJSON;

@Path("/v2/inventory/")
public class V2_inventory {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnBrandParts(@QueryParam("brand") String brand) throws SQLException{
		String returnString = null;
		JSONArray jsonArray = null;
		try{
			
			if(brand == null){
				return Response.status(400).entity("Error : Please specify brand to search.").build();
			}
			SchemaDAO dao = new SchemaDAO();
			jsonArray = dao.queryReturnBrandParts(brand);
			returnString = jsonArray.toString();
			
			
		}catch(Exception e){
			e.printStackTrace();
			return Response.status(500).entity("Server unable to process request").build();
		}
		return Response.ok(returnString).build();
	}
	

	@Path("/{brand}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnBrand(
				@PathParam("brand") String brand) 
				throws Exception {
		
		String returnString = null;
		
		JSONArray json = new JSONArray();
		System.out.println("Method hit");
		try {
			
			SchemaDAO dao = new SchemaDAO();
			
			json = dao.queryReturnBrandParts(brand);
			returnString = json.toString();
		}
		catch (Exception e) {
			e.printStackTrace();
			return Response.status(500).entity("Server was not able to process your request").build();
		}
		
		return Response.ok(returnString).build();
	}
}

