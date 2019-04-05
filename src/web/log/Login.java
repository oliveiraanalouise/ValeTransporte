package web.log;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TurnoDAO;
import dao.UsuarioDAO;
import entity.Turno;
import entity.Usuario;
import utilidades.Cripto;
import web.Logica;

@WebServlet("/login")
public class Login extends Logica{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void service(HttpServletRequest pedido, HttpServletResponse resposta)	throws ServletException, IOException {
		String email = pedido.getParameter("email");

		/*
		 * Busca usuario no banco: Caso tenha usado usu�rio administrador geral,
		 * vai gerar uma exception de Null e esse usu�rio fica como nulo. �
		 * exatamente isso que deve acontecer pois o administrador geral n�o
		 * est� cadastrado no banco
		 */
		Usuario u = new UsuarioDAO().getByEmail(email);

		// criptografa senha digitada. A compara��o � feita com as senhas
		// criptografadas
		String senha = new Cripto().criptografa(pedido.getParameter("senha"));

		System.out.println(senha + " " + u.getNome());
		if (u != null && senha.equals(u.getSenha())) {
			// Caso o login seja v�lido
		} else if (email.equals("valetransporte@ctb.ba.gov.br")
				&& senha.equals(/*"SZm6ez170MniprpMv9XhH5HVQ24JYbhs9Z9niOLSGH4="*/"")) {
			// Desse modo, sempre existir� um usu�rio administrador, n�o
			// importando o que h� no banco de dados

			// Cria um objeto usuario para Administrador
			u = new Usuario(
				0,
				"Administrador", 
				email, 
				senha, 
				"Administrador"
			);
		} else {
			// Senha errada, usu�rio inexistente...
			pedido.getRequestDispatcher("/erro403").forward(pedido, resposta);
		}
		// Coloca os dados do usu�rio na sess�o
		pedido.getSession().setAttribute("usuario", u);
		
		Turno t = new TurnoDAO().getUltimo();

		if(t.getTurno() == null) {
//			no caso de n�o haver turnos na tabela 
			t.setConcluido(true);
		}
		
		if(t.isConcluido()) {
//			caso o �ltimo turno j� esteja conclu�do
			t.resetTurno();
		}
		
		pedido.getSession().setAttribute("turno", t);
		// Manda mostrar a tela principal
		redireciona("/telaprincipal", pedido, resposta);
	}
}