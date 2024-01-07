package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import dao.dao;


@WebServlet(urlPatterns = "/loginuser")
public class login extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispathcher = request.getRequestDispatcher("login.jsp");
		dispathcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 	String username = request.getParameter("username");
		   
		    String password = request.getParameter("password");
		   
		    dao cDao = new dao();
		    
		    
		 if(cDao.login(username,password)==false) {
		    	request.setAttribute("error", "tài khoản hoặc mật khẩu không chính xác");
		    	RequestDispatcher dispathcher = request.getRequestDispatcher("login.jsp");
		    	dispathcher.forward(request, response);
		    }
		    	else {
		        // Passwords match, proceed with registration
		    		HttpSession session = request.getSession();
		    		session.setAttribute("username", username);
		       
		        request.setAttribute("success", "Đăng Nhập Thành Công");
		        
		        RequestDispatcher dispathcher = request.getRequestDispatcher("index.jsp");
		    	dispathcher.forward(request, response);
		    }
	}

}
