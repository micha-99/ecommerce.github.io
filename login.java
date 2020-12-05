package ecommerce;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class login extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("username");
		response.sendRedirect("login.html");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if(username.equals("alaya") && password.equals("alaya")) {
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			response.sendRedirect("login.html");
			Cookie ck = new Cookie ("name", username);			
			response.addCookie(ck);
			
		}else {
			request.setAttribute("message", "Username and password is invalid");
			request.getRequestDispatcher("index.html").forward(request, response);
		}
	}
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) response;

        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
        response.setHeader("Pragma", "no-cache"); 
        response.setDateHeader("Expires", 0); 

        chain.doFilter(request, response);
    }
	
}