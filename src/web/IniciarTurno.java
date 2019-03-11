package web;

import java.io.IOException;
import java.util.ArrayList;

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
			new DateTime(),			
			(String) pedido.getParameter("periodo"),
			false,
			(Usuario) pedido.getSession().getAttribute("usuario"),
			new Usuario(Integer.parseInt(pedido.getParameter("responsavel")),null,null,null,null),
			new ArrayList<>()
		);
		
		new TurnoDAO().inserir(t);
		pedido.getSession().setAttribute("turno", t);
		
		redirecionaIndex(pedido, resposta);
	}

}
