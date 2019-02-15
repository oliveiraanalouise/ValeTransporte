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
		
		//vai procurar em qual posição da lista essa escola será inserida
		for(Escola escola: escolas) {
			if (cn.compare(escola, e) >= 0 && !ok) { 
				ok = true; //marca que já achou a posição
				posicao = i;
			}
			
			++i;
		}
		
		//insere no banco e atualiza o id no objeto
		e.setId(new EscolaDAO().inserir(e));
		escolas.add(posicao,e);
		
		
		pedido.getSession().setAttribute("escolas", escolas);
		pedido.setAttribute("escolaRegistrada", e);
		pedido.setAttribute("cadastrada", true);
		redireciona("telacadastrarescola", pedido, resposta);
	}
}