package web.tela;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AlunoDAO;
import dao.EscolaDAO;
import web.Logica;

@WebServlet("/telaeditaraluno")
public class EditarAluno extends Logica{
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest pedido, HttpServletResponse resposta) throws ServletException, IOException {
		pedido.getSession().setAttribute("alunos", new AlunoDAO().getAll());
		pedido.getSession().setAttribute("escolas", new EscolaDAO().getAll());
		
		redireciona("logado/listaeditaraluno.jsp", pedido, resposta);
	}
}