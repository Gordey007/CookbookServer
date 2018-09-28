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
 * Servlet implementation class AddRecipe
 */
@WebServlet("/add")
public class AddRecipe extends HttpServlet {
	
	String code;

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
    public AddRecipe() {
        super();
        // TODO Auto-generated constructor stub
        gson = new Gson();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf8");
		response.setCharacterEncoding("utf8");
		
		listRecipes = new ArrayList<>();
		String addParam = request.getParameter("add");
		String writeMyRecipe = request.getParameter("recipe");

		System.out.println(request.getParameter("recipe"));
		
		RecipesJ add = gson.fromJson(writeMyRecipe, RecipesJ.class);
		
        add.recipe = add.recipe.replace('+', ' ');
        add.name = add.name.replace('+', ' ');

		try (Connection con = dataSource.getConnection()){
			st = con.createStatement();
			String sql = "insert into recipes (name, recipe) value (\""+ add.name + "\", \"" + add.recipe+"\" )";
			System.out.println((sql));
			st.executeUpdate(sql);
		}catch(Exception e) {
			e.printStackTrace();
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
