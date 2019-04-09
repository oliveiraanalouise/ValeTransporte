package web.cadastrar;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.VendaDAO;
import entity.Aluno;
import entity.Turno;
import entity.Venda;
import web.Logica;

@WebServlet("/registrarvenda")
public class CadastrarVenda extends Logica{
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	@Override
	protected void service(HttpServletRequest pedido, HttpServletResponse resposta) throws ServletException, IOException {
		int quantValesProposta = Integer.parseInt(pedido.getParameter("quantidade")),
			quantVendidaAoAluno = 0;
		Turno turno = (Turno) pedido.getSession().getAttribute("turno");
		List<Aluno> alunos = (List<Aluno>) pedido.getSession().getAttribute("alunos");
		Aluno a = alunos.get(Integer.parseInt(pedido.getParameter("iAluno")));
		List<Venda> comprasDesseAluno = new VendaDAO().getByAlunoNoMes(a);
		
		for (Venda v: comprasDesseAluno) {
			quantVendidaAoAluno += v.getQuantidade();
		}		
		
		if(quantVendidaAoAluno >= 50) {
			pedido.setAttribute("quantmaxaluno", true);			
		} else if (quantValesProposta > turno.getQuantVales()) {
//			quantidade de vales for maior que a disponível
			pedido.setAttribute("quantMax", true);
			
		} else if (quantVendidaAoAluno < 50 && quantValesProposta + quantVendidaAoAluno > 50){
			pedido.setAttribute("quantmaxparavenda", true);
			pedido.setAttribute("ticketsdisponiveis", 50 - quantVendidaAoAluno);
		}else {
			try {				
				Venda v = new Venda(
					-1,
					quantValesProposta,
					turno.getId(),					
					a/*, comentado pois informação estava duplicada. Já há um registro de vendedor através do turno 
					(Usuario) pedido.getSession().getAttribute("usuario")*/							 
				);
				new VendaDAO().inserir(v);
				
				turno.vendaTicket(quantValesProposta);
				pedido.setAttribute("ok", true);			
				pedido.getSession().setAttribute("aluno", null);
				
				turno.getVendas().add(v);

				pedido.getSession().setAttribute("turno", turno);
			} catch (NumberFormatException e) {
//				caso n�o seja escolhido um aluno
				pedido.setAttribute("alunoAusente", true);
			} catch (SQLException sqle) {
				pedido.setAttribute("erro", true);
			}
		}

		redireciona("telaregistrarvenda", pedido, resposta);
	}
}