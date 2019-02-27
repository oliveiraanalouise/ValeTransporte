package dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import entity.Turno;

public class TurnoDAO extends DAO {
	private final String 
		cData = nomeTabela + ".data",
		cTurno = nomeTabela + ".turno", 
		cQuantVales = nomeTabela + ".quantidadeVales",
		cConcluido = nomeTabela + ".concluido",
		cIdVendedor = nomeTabela + ".vendedor"/*,
		cNomeVendedor = new UsuarioDAO().nomeTabela+".nome"*/;

	public TurnoDAO() {
		super("turno");
	}

	public void inserir(Turno t) {
		iniciaConexaoComBanco();
		
		setSqlQuery(
			"insert into "+nomeTabela+" ("+ 
				cData + ", " +
				cTurno + ", " +
				cQuantVales + ", " +
				cIdVendedor
			+") values (?,?,?,?)"				
		);
		
		try {
			setStatement(
				getDbConnection().prepareStatement(
					getSqlQuery()
				)
			);
			int posicao = 1;
//			Preenche o statement
			getStatement().setDate(posicao, new Date(t.getData().toDate().getTime()));
			getStatement().setString(++posicao, t.getTurno());
			getStatement().setInt(++posicao, t.getQuantVales());
			getStatement().setInt(++posicao, t.getIdVendedor());

//			executa
			getStatement().executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		encerraConexaocomBanco();
	}
	
	public Turno getUltimo(){
		iniciaConexaoComBanco();
		Turno t = null;
		
		setSqlQuery(
			"select * from " + nomeTabela+" order by " + cId + " desc limit 1"
		);
		
		try {
			setStatement(
				getDbConnection().prepareStatement(
					getSqlQuery()
				)
			);
			
			setResultado(getStatement().executeQuery());
		} catch (SQLException e) {
			e.printStackTrace();
			t = new Turno();			
		}
		
		try {
			if(getResultado().next()) {
				t = new Turno(
					getResultado().getInt(cId),
					getResultado().getInt(cQuantVales),
					getResultado().getInt(cIdVendedor),
					new DateTime(getResultado().getDate(cData)),
					getResultado().getString(cTurno),
					getResultado().getBoolean(cConcluido)
				);
			} else t = new Turno();
		} catch (SQLException e) {}
		
		encerraConexaocomBanco();
		return t;
	}

	public void atualizar(Turno t) {
		iniciaConexaoComBanco();
		
		setSqlQuery(
			"update "+nomeTabela+" set "+ 
				/*
				 * cData + " = ?, " + cTurno + " = ?, " + cQuantVales + " = ?, "
				 * + cIdVendedor + " = ?, " +
				 */cConcluido	+ " = ? where " +
			cId + " = ?"
		);
		
		try {
			setStatement(getDbConnection().prepareStatement(getSqlQuery()));
			
			int posicao = 1;
			
			/*
			 * getStatement().setDate(posicao, new Date(t.getData().toDate().getTime()));
			 * getStatement().setString(++posicao, t.getTurno());
			 * getStatement().setInt(++posicao, t.getQuantVales());
			 * getStatement().setInt(++posicao, t.getIdVendedor());
			 */
			getStatement().setBoolean(posicao, t.isConcluido());
			getStatement().setInt(++posicao, t.getId());
			
			getStatement().executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		encerraConexaocomBanco();
	}

	public List<Turno> getAll() {
		iniciaConexaoComBanco();
		List<Turno> turnos = new ArrayList<Turno>();
		
		setSqlQuery(
			"select * from " + nomeTabela+" order by " + cId + " desc"
		);
		
		try {
			setStatement(
				getDbConnection().prepareStatement(
					getSqlQuery()
				)
			);
			
			setResultado(getStatement().executeQuery());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			Turno t = null;
			
			while(getResultado().next()) {
				t = new Turno(
					getResultado().getInt(cId),
					getResultado().getInt(cQuantVales),
					getResultado().getInt(cIdVendedor),
					new DateTime(getResultado().getDate(cData)),
					getResultado().getString(cTurno),
					getResultado().getBoolean(cConcluido)
				);
				
				turnos.add(t);
			} 
		} catch (SQLException e) {}
		
		encerraConexaocomBanco();
		return turnos;
	}
}
