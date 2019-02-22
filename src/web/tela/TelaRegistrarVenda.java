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
public class TelaRegistrarVenda extends Logica{
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	protected void service(HttpServletRequest pedido, HttpServletResponse resposta) throws ServletException, IOException {
		List<Aluno> alunos = (List<Aluno>) pedido.getSession().getAttribute("alunos");
			
		if(alunos == null)
			pedido.getSession().setAttribute("alunos", new AlunoDAO().getAll());
		
		try {
			int ialuno = Integer.parseInt((String) pedido.getParameter("ialuno"));
			
			pedido.getSession().setAttribute("aluno", alunos.get(ialuno));
		} catch (Exception e) {
		}		
		
		redireciona("logado/registrarvenda.jsp", pedido, resposta);
	}
}