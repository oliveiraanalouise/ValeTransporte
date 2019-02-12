package web.cadastrar;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EscolaDAO;
import entity.Escola;
import web.Logica;

@WebServlet("/cadastrarescola")
public class CadastrarEscola extends Logica{
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest pedido, HttpServletResponse resposta) throws ServletException, IOException {
		Escola e = new Escola(
			0,
			pedido.getParameter("nome")
		);
		
		new EscolaDAO().inserir(e);
		
		
	}
}