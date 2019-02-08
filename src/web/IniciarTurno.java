package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;

import dao.TurnoDAO;
import entity.Turno;
import entity.Usuario;

@WebServlet("/iniciarturno")
public class IniciarTurno extends Logica{
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest pedido, HttpServletResponse resposta) throws ServletException, IOException {
		String quantVales = (String) pedido.getParameter("quantVales");
		
		Turno t = new Turno(
			0,
			Integer.parseInt(quantVales),
			((Usuario) pedido.getSession().getAttribute("usuario")).getId(),
			new DateTime(),			
			(String) pedido.getParameter("periodo"),
			false
		);
		
		new TurnoDAO().inserir(t);
		pedido.getSession().setAttribute("estadoTurno", t.isConcluido());
		
		redirecionaIndex(pedido, resposta);
	}

}
