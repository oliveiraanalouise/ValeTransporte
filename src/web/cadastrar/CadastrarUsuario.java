package web.cadastrar;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UsuarioDAO;
import entity.Usuario;
import utilidades.Cripto;
import web.Logica;

@WebServlet("/cadastrarusuario")
public class CadastrarUsuario extends Logica{
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest pedido, HttpServletResponse resposta)
			throws ServletException, IOException {
		UsuarioDAO udao = new UsuarioDAO();
		
		String email = pedido.getParameter("email");
		if(udao.getByEmail(email) == null){
			Usuario u = new Usuario(
				0,
				pedido.getParameter("nome"),
				email,
				new Cripto().criptografa(pedido.getParameter("senha")),
				pedido.getParameter("responsavel")
			);
			
			udao.inserir(u);
			redireciona("confirmacaocadastro.jsp", pedido, resposta);
		} else {
			pedido.setAttribute("emailusado", true);
			redireciona("telacadastrarusuario", pedido, resposta);
		}
		
	
	}

}
