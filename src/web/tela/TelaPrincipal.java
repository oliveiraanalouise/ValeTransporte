package web.tela;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("telaprincipal")
public class TelaPrincipal extends HttpServlet{
	private static final long serialVersionUID = -6083945672894221969L;
	
	@Override
	protected void service(HttpServletRequest pedido, HttpServletResponse resposta) throws ServletException, IOException {
		pedido.getRequestDispatcher("logado/index.jsp").forward(pedido, resposta);
	}
}
