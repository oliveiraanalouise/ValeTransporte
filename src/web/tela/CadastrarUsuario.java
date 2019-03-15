package web.tela;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDAO;
import web.Logica;

@WebServlet("/telacadastrarusuario")
public class CadastrarUsuario extends Logica{
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest pedido, HttpServletResponse resposta)	throws ServletException, IOException {
		pedido.setAttribute("supervisores", new UsuarioDAO().getAllSupervisor());
		
		redireciona("cadastrarusuario.jsp", pedido, resposta);
	}
}