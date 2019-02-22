package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Turno;
import entity.Venda;

public class VendaDAO extends DAO {
	private final String cVendedor = nomeTabela + ".usuario_id", 
						 cAluno = nomeTabela + ".aluno_id",
						 cQuantidade = nomeTabela + ".quantidade", 
						 cTurno = nomeTabela + ".turno_id";

	public VendaDAO() {
		super("venda");
	}

	public void inserir(Venda v) {
		iniciaConexaoComBanco();

		setSqlQuery("insert into " + nomeTabela + " (" + cAluno + ", " + cQuantidade + ", " + cTurno
				+ ", " + cVendedor + ") values (?,?,?,?)");

		try {
			setStatement(getDbConnection().prepareStatement(getSqlQuery()));

			int posicao = 1;

			getStatement().setInt(posicao, v.getAluno().getId());
			getStatement().setInt(++posicao, v.getQuantidade());
			getStatement().setInt(++posicao, v.getTurno().getId());
			getStatement().setInt(++posicao, v.getVendedor().getId());

			getStatement().executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		encerraConexaocomBanco();
	}

	public List<Venda> getByTurno(Turno t) {
		iniciaConexaoComBanco();
		
		List<Venda> vendas = new ArrayList<Venda>();
		setSqlQuery("select * from "+nomeTabela+" where "+cTurno+" = ?");
		
		try {
			setStatement(getDbConnection().prepareStatement(getSqlQuery()));
			
			getStatement().setInt(1, t.getId());
			
			setResultado(getStatement().executeQuery());
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		try {
			Venda v;
			while(getResultado().next()) {
				v = new Venda(
					getResultado().getInt(cId),
					getResultado().getInt(cQuantidade),
					new AlunoDAO(getDbConnection()).getById(getResultado().getInt(cAluno)),
					new UsuarioDAO(getDbConnection()).getById(getResultado().getInt(cVendedor)),
					t					
				);
				
				vendas.add(v);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		encerraConexaocomBanco();
		return vendas;
	}
}