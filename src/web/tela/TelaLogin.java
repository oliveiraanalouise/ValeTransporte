package web.tela;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/telalogin")
public class TelaLogin extends HttpServlet{
	private static final long serialVersionUID = 4555805421904310654L;

	@Override
	protected void service(HttpServletRequest pedido, HttpServletResponse resposta)	throws ServletException, IOException {
		pedido.getRequestDispatcher("index.jsp").forward(pedido, resposta);
	}
}
