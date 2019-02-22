package web.cadastrar;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.VendaDAO;
import entity.Aluno;
import entity.Turno;
import entity.Usuario;
import entity.Venda;
import web.Logica;

@WebServlet("/registrarvenda")
public class CadastrarVenda extends Logica{
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest pedido, HttpServletResponse resposta) throws ServletException, IOException {
		int quantValesProposta = Integer.parseInt(pedido.getParameter("quantidade"));
		Turno turno = (Turno) pedido.getSession().getAttribute("turno");
		
		/*
		 * if(turno.getQuantVales() == 0) { // não há vales disponíveis
		 * pedido.setAttribute("zero", true); } else
		 */if (quantValesProposta > turno.getQuantVales()) {
//			quantidade de vales for maior que a disponível
			pedido.setAttribute("quantMax", true);
		} else {
			try {
//				a tabela de vendas só necessita da id do aluno, então crio um objeto aluno 
//				somente com a id
				Aluno a = new Aluno(
					Integer.parseInt(pedido.getParameter("idAluno")),null,null,null,null,null,null
				);
				
				Venda v = new Venda(
					-1,
					quantValesProposta,
					a,
					(Usuario) pedido.getSession().getAttribute("usuario"),
					turno
				);
				
				turno.vendaTicket(quantValesProposta);
				new VendaDAO().inserir(v);
				pedido.setAttribute("ok", true);			
				pedido.getSession().setAttribute("aluno", null);
			} catch (NumberFormatException e) {
//				caso não seja escolhido um aluno
				pedido.setAttribute("alunoAusente", true);
			}
		}
		
		redireciona("telaregistrarvenda", pedido, resposta);
	}
}