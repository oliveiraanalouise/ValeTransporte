package web;

import java.io.IOException;
import java.util.EmptyStackException;
import java.util.Stack;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/voltarpagina")
public class VoltarPagina extends Logica{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	protected void service(HttpServletRequest pedido, HttpServletResponse resposta)
			throws ServletException, IOException {
		Stack<String> paginas = (Stack<String>) pedido.getSession().getAttribute("pilhaPaginas");
		String pagina, pag2, backup = "/valetransporte/telaprincipal";
		try {
			paginas.pop();
			paginas.pop();
			do {} while(!paginas.pop().contains("jsp"));
			pagina = paginas.pop();
		} catch (EmptyStackException e) {
			pagina = backup;
		}
		int p = pagina.lastIndexOf("/");
		pag2 = pagina.substring(
					p + 1, 
					pagina.length()
				);
//		System.out.println("função: " + pag2);
		redireciona(pag2, pedido, resposta);	
	}


}
