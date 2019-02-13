package web.cadastrar;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.EscolaDAO;
import entity.Escola;
import utilidades.ComparadorNome;
import web.Logica;

@WebServlet("/cadastrarescola")
public class CadastrarEscola extends Logica{
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	protected void service(HttpServletRequest pedido, HttpServletResponse resposta) throws ServletException, IOException {
		List<Escola> escolas = (List<Escola>) pedido.getSession().getAttribute("escolas");
		Escola e = new Escola(
			0,
			pedido.getParameter("nome")
		);
		
		boolean ok = false;
		ComparadorNome cn = new ComparadorNome();
		int posicao = 0, i = 0;		
		
		while(i < escolas.size() || !ok) {
			if (cn.compare(escolas.get(posicao), e) >= 0) {
				ok = true;
				i = escolas.size()-1;
				posicao = i;
			}
			
			++i;
		}
			
		escolas.add(posicao,e);
//		new EscolaDAO().inserir(e);
		
		pedido.getSession().setAttribute("escolas", escolas);
		pedido.setAttribute("escolaRegistrada", e);
		pedido.setAttribute("cadastrada", true);
		redireciona("telacadastrarescola", pedido, resposta);
	}
}