package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AlunoDAO;
import entity.Aluno;
import utilidades.PDF;

@WebServlet("/gerarcarteiras")
public class GerarCarteira extends Logica{
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	protected void service(HttpServletRequest pedido, HttpServletResponse resposta)	throws ServletException, IOException {
		AlunoDAO adao = new AlunoDAO();
		List<Aluno> alunos = (List<Aluno>) pedido.getSession().getAttribute("alunos");
		
		if(alunos == null) {
			alunos = adao.getAllSemCarteira();
		}
		
		new PDF(this.getServletContext().getRealPath("")).gerarCarteira(alunos);
		
		for (Aluno a: alunos)
			adao.atualiza(a, true);
		
		pedido.setAttribute("carteiras", true);
		redirecionaIndex(pedido, resposta);
	}
}