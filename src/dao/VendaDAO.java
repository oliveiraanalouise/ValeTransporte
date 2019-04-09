package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import entity.Aluno;
import entity.Turno;
import entity.Venda;

public class VendaDAO extends DAO {
	private final String //cVendedor = nomeTabela + ".usuario_id", 
						 cAluno = nomeTabela + ".aluno_id",
						 cQuantidade = nomeTabela + ".quantidade", 
						 cTurno = nomeTabela + ".turno_id";

	public VendaDAO() {
		super("venda");
	}

	public VendaDAO(Connection dbConnection) {
		super("venda", dbConnection);
	}

	public void inserir(Venda v) throws SQLException {
		iniciaConexaoComBanco("insert into " + nomeTabela + " (" + cAluno + ", " + cQuantidade + ", " + cTurno + ") values (?,?,?)");

		int posicao = 1;

		getStatement().setInt(posicao, v.getAluno().getId());
		getStatement().setInt(++posicao, v.getQuantidade());
		getStatement().setInt(++posicao, v.getIdTurno());

		getStatement().executeUpdate();
		
		encerraConexaocomBanco();
	}

	public List<Venda> getByIdTurno(int id) {
		iniciaConexaoComBanco("select * from "+nomeTabela+" where "+cTurno+" = ?");
		
		List<Venda> vendas = new ArrayList<>();
		
		try {
			getStatement().setInt(1, id);
			
			setResultado(getStatement().executeQuery());

			Venda v;
			while(getResultado().next()) {
				v = new Venda(
					getResultado().getInt(cId),
					getResultado().getInt(cQuantidade),
					getResultado().getInt(cTurno),
					new AlunoDAO(getDbConnection()).getById(getResultado().getInt(cAluno))
				);
				
				vendas.add(v);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		encerraConexaocomBanco();
		return vendas;
	}

	public List<Venda> getByAlunoNoMes(Aluno a) {
		iniciaConexaoComBanco("select * from "+nomeTabela+" where "+cAluno+" = ?");
		
		List<Venda> vendas = new ArrayList<>();
		
		try {
			getStatement().setInt(1, a.getId());
			
			setResultado(getStatement().executeQuery());

			Venda v;
			int mesAtual = new DateTime().getMonthOfYear();
			TurnoDAO tdao = new TurnoDAO(getDbConnection());
			Turno t;
			
			while(getResultado().next()) {
				t = tdao.getById(getResultado().getInt(cTurno));
				
				if(t.getData().getMonthOfYear() == mesAtual) {
					v = new Venda(
						getResultado().getInt(cId),
						getResultado().getInt(cQuantidade),
						getResultado().getInt(cTurno),
						new AlunoDAO(getDbConnection()).getById(getResultado().getInt(cAluno))
					);
				
					vendas.add(v);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		
		encerraConexaocomBanco();
		return vendas;
	}
}