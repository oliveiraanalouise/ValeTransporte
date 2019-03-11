package web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TurnoDAO;
import entity.Turno;
import utilidades.PDF;

@WebServlet("/encerrarturno")
public class EncerrarTurno extends Logica{
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest pedido, HttpServletResponse resposta) throws ServletException, IOException {
		Turno t = (Turno) pedido.getSession().getAttribute("turno");
		t.setConcluido(true);
		
//		new TurnoDAO().atualizar(t);
		
//		t.resetTurno();
		
		pedido.getSession().setAttribute("turno", t);
		String pasta = this.getServletContext().getRealPath("");
		
		new PDF(pasta).relatorioTurno(t);
		
		redirecionaIndex(pedido, resposta);
	}

}
