package web.tela;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Usuario;
import web.Logica;

@WebServlet("/telalogin")
public class Login extends Logica{
	private static final long serialVersionUID = 4555805421904310654L;

	@Override
	protected void service(HttpServletRequest pedido, HttpServletResponse resposta)	throws ServletException, IOException {
		Usuario logado = (Usuario) pedido.getSession().getAttribute("usuario");

		try {
			if(null != logado.getNome()){
				redirecionaIndex(pedido, resposta);
			}
		} catch (Exception e) {	}
		
		redireciona("index.jsp", pedido, resposta);
	}
}
