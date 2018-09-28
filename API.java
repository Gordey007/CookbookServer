package ru.cookbook;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/cb")
public class API {

	@Consumes("application/json")
	@Produces({"application/json"})
	@POST
	String doPost() {
		System.out.println("doPost work");
		return "hello";
	}
}
