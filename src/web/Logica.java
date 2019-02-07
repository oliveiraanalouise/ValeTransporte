package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Logica extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected abstract void service(HttpServletRequest pedido, HttpServletResponse resposta) throws ServletException, IOException;
	
	protected void redireciona(String page, HttpServletRequest pedido, HttpServletResponse resposta) throws ServletException, IOException {
		pedido.getRequestDispatcher(page).forward(pedido, resposta);
	}
}
