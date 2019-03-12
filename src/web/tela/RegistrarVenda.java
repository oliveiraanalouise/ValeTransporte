package web.tela;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AlunoDAO;
import entity.Aluno;
import web.Logica;

@WebServlet("/telaregistrarvenda")
public class RegistrarVenda extends Logica{
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest pedido, HttpServletResponse resposta) throws ServletException, IOException {
		List<Aluno> alunos = new AlunoDAO().getAll();
			
		pedido.getSession().setAttribute("alunos", alunos);
		
		try {
			int ialuno = Integer.parseInt((String) pedido.getParameter("ialuno"));
			
			pedido.setAttribute("aluno", alunos.get(ialuno));
			pedido.setAttribute("ialuno", ialuno);
		} catch (Exception e) {}		
		
		redireciona("logado/registrarvenda.jsp", pedido, resposta);
	}
}