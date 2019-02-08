package web.log;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.Logica;

@WebServlet("/logout")
public class Logout extends Logica{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest pedido, HttpServletResponse resposta)
			throws ServletException, IOException {
		pedido.getSession().invalidate();
		redireciona("telalogin", pedido, resposta);
	}

}
