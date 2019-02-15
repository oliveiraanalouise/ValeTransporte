package web.cadastrar;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AlunoDAO;
import entity.Aluno;
import entity.Escola;
import web.Logica;

@WebServlet("/cadastraraluno")
public class CadastrarAluno extends Logica{
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	protected void service(HttpServletRequest pedido, HttpServletResponse resposta)	throws ServletException, IOException {
		AlunoDAO adao = new AlunoDAO();
		int idEscola;
		List<Escola> escolas = (List<Escola>) pedido.getSession().getAttribute("escolas");
		Escola e = new Escola();
		
		try {
			idEscola = Integer.parseInt(pedido.getParameter("escola"));
		} catch (NumberFormatException nfe) {
			idEscola = -1;
			
		}
		
		//procura a escola na lista pela id para depois colocar no objeto "Aluno"
		for(int i = 0; i<escolas.size();++i) {
			if(escolas.get(i).getId() == idEscola) {
				e=escolas.get(i);
				i = escolas.size();
			}
		}
		
		Aluno aluno = new Aluno(
			0,
			pedido.getParameter("nome"),
			pedido.getParameter("rg"),
			pedido.getParameter("cpf"),
			pedido.getParameter("endereco"),
			pedido.getParameter("cep"),
			e
		);		
		
		if(!adao.exist(aluno)) {//só vai fazer cadastro caso o cpf e rg ainda não estejam registrados 
			adao.inserir(aluno);
			pedido.setAttribute("ok", true);
		}
		else pedido.setAttribute("cpfCadastrado", true);
		
		redireciona("telacadastraraluno", pedido, resposta);
	}
}