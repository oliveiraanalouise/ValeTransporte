package web.tela;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EscolaDAO;
import entity.Escola;
import web.Logica;

@WebServlet("/telacadastraraluno")
public class CadastrarAluno extends Logica{
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	protected void service(HttpServletRequest pedido, HttpServletResponse resposta) throws ServletException, IOException {
		List<Escola> le = (List<Escola>) pedido.getSession().getAttribute("escolas");
		
		if(le == null) {
			pedido.getSession().setAttribute("escolas", new EscolaDAO().getAll());
		}
		
		redireciona("logado/cadastraraluno.jsp", pedido, resposta);
	}
}