/*
 * Classe responsável por gerenciar a pilha de páginas acessadas 
 */

package web.filtro;

import java.io.IOException;
import java.util.Stack;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter(
	urlPatterns = {"/*"},
	dispatcherTypes = {
		DispatcherType.REQUEST, 
		DispatcherType.FORWARD
	}
)
public class PilhaPagina implements Filter{
	
	@SuppressWarnings("unchecked")
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest pedido = (HttpServletRequest) req;
		HttpServletResponse resposta = (HttpServletResponse) res;
		HttpSession sessao = pedido.getSession();
		String parametros = pedido.getQueryString(), 
			   paginaAcessada = pedido.getRequestURI() + "?" + parametros;
			   
		Stack<String> paginas = null;
		try {
			paginas = (Stack<String>) sessao.getAttribute("pilhaPaginas");
		} catch (Exception e1) {
			paginas = new Stack<>();
		}
		
		if(paginas == null)
			paginas = new Stack<>();
		
		paginas.push(paginaAcessada);
		sessao.setAttribute("pilhaPaginas", paginas);
//		System.out.println("Filtro: " + paginaAcessada);
		chain.doFilter(pedido, resposta);
	}

}
