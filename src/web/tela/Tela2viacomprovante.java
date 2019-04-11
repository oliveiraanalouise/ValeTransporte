package web.tela;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AlunoDAO;
import web.Logica;

@WebServlet("/tela2viacomprovante")
public class Tela2viacomprovante extends Logica{
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest pedido, HttpServletResponse resposta) throws ServletException, IOException {
		pedido.getSession().setAttribute("alunos", new AlunoDAO().getAll());
		redireciona("logado/2aviacomprovante.jsp", pedido, resposta);	
	}
}