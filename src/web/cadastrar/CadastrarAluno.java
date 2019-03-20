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
import utilidades.PDF;
import web.Logica;

@WebServlet("/cadastraraluno")
public class CadastrarAluno extends Logica{
	private static final long serialVersionUID = 1L;
	final String formatoData = "yyyy-MM-dd";
	
	@SuppressWarnings("unchecked")
	@Override
	protected void service(HttpServletRequest pedido, HttpServletResponse resposta) throws ServletException, IOException {
		AlunoDAO adao = new AlunoDAO();
		DateTime nascimento = null;
		int idEscola;
		List<Escola> escolas = (List<Escola>) pedido.getSession().getAttribute("escolas");
//		Escola e = new Escola();
		Aluno aluno;
		
		try {
			nascimento = new DateTime(new SimpleDateFormat(formatoData).parse(pedido.getParameter("nascimento")));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}		
		
		try {
			idEscola = Integer.parseInt(pedido.getParameter("escola"));
		} catch (NumberFormatException nfe) {
			idEscola = -1;			
		}
		
		aluno = new Aluno(
			0,
			pedido.getParameter("nome"),
			pedido.getParameter("rg"),
			pedido.getParameter("bairro"),
			escolas.get(idEscola),
			new DateTime(nascimento)
		);				
		
		if(!adao.exist(aluno) && aluno.getIdade()>5) {//só vai fazer cadastro caso o rg ainda não esteja registrado 
			aluno.setId(adao.inserir(aluno));
			
			pedido.setAttribute("ok", true);
			pedido.setAttribute("id", aluno.getId());
			
//			Gera comprovante de cadastro
			String pasta = this.getServletContext().getRealPath("");
			new PDF(pasta).comprovanteCadastro(aluno);
		}
		else {
			if(aluno.getIdade()<=5)
				pedido.setAttribute("idademinima", true);
			else
				pedido.setAttribute("cpfCadastrado", true);
		}
		
		redireciona("telacadastraraluno", pedido, resposta);
	}
}