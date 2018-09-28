package ru.cookbook;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.google.gson.Gson;

/**
 * Servlet implementation class CookBookServlet
 */
@WebServlet("/cbs")
public class CookBookServlet extends HttpServlet {
	
	String code;
	
	
	//@Resource(lookup="java:/MySqlDS")MariaDB
	@Resource(lookup="java:/MariaDB")
	private DataSource dataSource;
	Statement st;
	ResultSet rs;
	ArrayList<RecipesJ> listRecipes;
	Gson gson;
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CookBookServlet() {
        super();
        // TODO Auto-generated constructor stub
        gson = new Gson();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf8");
		response.setCharacterEncoding("utf8");
		listRecipes = new ArrayList<>();
		System.out.print(request.getParameter("code"));
		String nameParam = request.getParameter("name");
		code = request.getParameter("code");
			
		try (Connection con = dataSource.getConnection()){
			
		//	if(addParam != null)
			//	add(addParam);
			
			st = con.createStatement();

			//Борщ с курицей
			 
			rs =st.executeQuery("SELECT * FROM recipes where name = '"+ nameParam +"'");
			
			
			System.out.println(nameParam);
			while (rs.next()) {

				RecipesJ recipes = new RecipesJ();
				recipes.name = rs.getString("name");
				recipes.recipe = rs.getString("recipe");
				recipes.energy_value = rs.getString("energy_value");
				recipes.products = rs.getString("products");
				recipes.time = rs.getString("time");
				
				listRecipes.add(recipes);
				
			}
			
		}catch (Exception e) {
	
	        e.printStackTrace();
	    }
		System.out.println("***********************************");
		System.out.println(gson.toJson(listRecipes));
		System.out.println("***********************************");
		response.getWriter().append(gson.toJson(listRecipes));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
	//	System.out.println(request.getParameter("recipes"));
		doGet(request, response);
	}
	//void add(String myRecipes) {
		
	//	System.out.println(myRecipes);
		
	//}
}
