/*Redireciona para página de erro "falta de acesso"*/
package web.erro;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/erro403")
public class Erro403 extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest pedido, HttpServletResponse resposta)	throws ServletException, IOException {
		pedido.getRequestDispatcher("errosPag/403.jsp").forward(pedido, resposta);
	}
}
