package com.angulatpac;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/StopFetchingData")
public class StopFetchingData extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public StopFetchingData() {super();}
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HelperClass.setToOne();
	}
}
