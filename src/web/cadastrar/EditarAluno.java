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
import entity.Aluno;
import entity.Escola;
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
		int idEscola, id = 0;
		List<Escola> escolas = (List<Escola>) pedido.getSession().getAttribute("escolas");
//		Escola e = new Escola();
		Aluno aluno;
		
		try {
			nascimento = new DateTime(new SimpleDateFormat(formatoData).parse(pedido.getParameter("nascimento")));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}		
		
		try {
			id = Integer.parseInt(pedido.getParameter("id"));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}		
		
		try {
			idEscola = Integer.parseInt(pedido.getParameter("escola"));
		} catch (NumberFormatException nfe) {
			idEscola = -1;			
		}
		
		aluno = new Aluno(
			id,
			pedido.getParameter("nome"),
			pedido.getParameter("rg"),
			pedido.getParameter("bairro"),
			escolas.get(idEscola),
			new DateTime(nascimento)
		);			
		
		adao.atualiza(aluno);
		
		redireciona("telaeditaraluno", pedido, resposta);
	}
}