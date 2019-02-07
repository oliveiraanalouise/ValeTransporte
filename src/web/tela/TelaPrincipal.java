package web.tela;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.Usuario;
import web.Logica;

@WebServlet("/telaprincipal")
public class TelaPrincipal extends Logica{
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest pedido, HttpServletResponse resposta) throws ServletException, IOException {
		Usuario u = (Usuario) pedido.getSession().getAttribute("usuario");
		
		if(u!=null) 
			redireciona("logado/index.jsp", pedido, resposta);
		else
			redireciona("erro403", pedido, resposta);
	}
}
