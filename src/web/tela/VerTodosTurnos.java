package web.tela;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TurnoDAO;
import web.Logica;

@WebServlet("/vertodosturnos")
public class VerTodosTurnos extends Logica{
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest pedido, HttpServletResponse resposta) throws ServletException, IOException {
		TurnoDAO tdao = new TurnoDAO();
		
		pedido.getSession().setAttribute("turnos", tdao.getAll());
		
		redireciona("/logado/verturnos.jsp", pedido, resposta);
	}
}