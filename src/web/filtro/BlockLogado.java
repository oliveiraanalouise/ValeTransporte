package web.filtro;

import java.io.IOException;

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

import entity.Usuario;

@WebFilter(
		urlPatterns = {"/logado/*"},
		dispatcherTypes = {
			DispatcherType.REQUEST, 
			DispatcherType.FORWARD
		}
	)
public class BlockLogado implements Filter{

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2) throws IOException, ServletException {
		HttpServletRequest pedido = (HttpServletRequest) arg0;
		HttpServletResponse resposta = (HttpServletResponse) arg1;
		HttpSession sessao = pedido.getSession();
		
		Usuario logado = (Usuario) sessao.getAttribute("usuario");
		
		try {
			if(logado.getNome() != null) {
				arg2.doFilter(arg0, arg1);
			} else {
				pedido.getRequestDispatcher("/erro403").forward(pedido, resposta);
			}
		} catch(Exception e) {
			pedido.getRequestDispatcher("/erro403").forward(pedido, resposta);
		}
				
	}
}