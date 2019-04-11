package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Aluno;
import utilidades.PDF;

@WebServlet("/segviacomprovante")
public class Segviacomprovante extends Logica{
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	protected void service(HttpServletRequest pedido, HttpServletResponse resposta) throws ServletException, IOException {
		List<Aluno> alunos = (List<Aluno>) pedido.getSession().getAttribute("alunos");
		Aluno a = alunos.get(Integer.parseInt(pedido.getParameter("ialuno")));
		
		String pasta = this.getServletContext().getRealPath("");
		new PDF(pasta).comprovanteCadastro(a);
		
		pedido.setAttribute("ok", true);
		pedido.setAttribute("aluno", a);
		
		redireciona("tela2viacomprovante", pedido, resposta);
	}
}