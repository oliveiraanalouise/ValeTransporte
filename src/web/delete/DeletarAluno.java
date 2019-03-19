package web.delete;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AlunoDAO;
import entity.Aluno;
import web.Logica;

@WebServlet("/deletaraluno")
public class DeletarAluno extends Logica{
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest pedido, HttpServletResponse resposta)	throws ServletException, IOException {
		@SuppressWarnings("unchecked")
		List<Aluno> alunos = (List<Aluno>) pedido.getSession().getAttribute("alunos");
		
		Aluno a = alunos.get(Integer.parseInt(pedido.getParameter("ialuno")));
		
		new AlunoDAO().delete(a);
		
		redireciona("telaeditaraluno", pedido, resposta);
	}
}