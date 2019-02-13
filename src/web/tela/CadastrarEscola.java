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

@WebServlet("/telacadastrarescola")
public class CadastrarEscola extends Logica{
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest pedido, HttpServletResponse resposta) throws ServletException, IOException {
		@SuppressWarnings("unchecked")
		List<Escola> escolas = (List<Escola>) pedido.getSession().getAttribute("escolas");
		
		if(escolas == null) {
			escolas = new EscolaDAO().getAll();
			pedido.getSession().setAttribute("escolas", escolas);
		}
		
		redireciona("logado/cadastrarescola.jsp", pedido, resposta);
	}
}