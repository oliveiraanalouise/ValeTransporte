package web.cadastrar;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;

import dao.AlunoDAO;
import dao.EscolaDAO;
import entity.Aluno;
import entity.Escola;
import utilidades.CalcularData;
import web.Logica;

@WebServlet("/editaraluno")
public class EditarAluno extends Logica{
	private static final long serialVersionUID = 1L;
	final String formatoData = "yyyy-MM-dd";
	
	@SuppressWarnings("unchecked")
	@Override
	protected void service(HttpServletRequest pedido, HttpServletResponse resposta) throws ServletException, IOException {
		AlunoDAO adao = new AlunoDAO();
		DateTime nascimento = null;
		int iEscola, id = 0;
		List<Escola> escolas = (List<Escola>) pedido.getSession().getAttribute("escolas");
//		Escola e = new Escola();
		Aluno aluno;
		String data = pedido.getParameter("nascimento");
				
		try {
			nascimento = new DateTime(new SimpleDateFormat(formatoData).parse(data));
		} catch (ParseException e1) {
//			if(data.)
			e1.printStackTrace();
		}
		
		if(new CalcularData(nascimento).anosEntre()>5) {//idade mínima para um aluno		
			if(escolas == null) {
				escolas = new EscolaDAO().getAll();
			}
			
			try {
				id = Integer.parseInt(pedido.getParameter("id"));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}		
			
			try {
				iEscola = Integer.parseInt(pedido.getParameter("escola"));
			} catch (NumberFormatException nfe) {
				iEscola = -1;			
			}
			
			aluno = new Aluno(
				id,
				pedido.getParameter("nome"),
				pedido.getParameter("rg"),
				pedido.getParameter("bairro"),
				escolas.get(iEscola),
				new DateTime(nascimento)
			);			
			
			adao.atualiza(aluno);
		} else {
			pedido.setAttribute("idademinima", true);
		}
		redireciona("telaeditaraluno", pedido, resposta);
	}
}