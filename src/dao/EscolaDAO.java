package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;

import entity.Escola;

public class EscolaDAO extends DAO {
	private final String cNome = nomeTabela + ".nome";

	public EscolaDAO() {
		super("escola");
	}
	
	public EscolaDAO(Connection connection) {
		super("escola", connection);
	}

	public int inserir(Escola e) {
		//retorn a id do registro que acabou de ser inserido
		iniciaConexaoComBanco();
		setSqlQuery("insert into " + nomeTabela + " (" + cNome + ") values (?)");
		
		try {
			setStatement(
				getDbConnection().prepareStatement(
					getSqlQuery()
				)
			);
			
			int posicao = 1;
			getStatement().setString(posicao, e.getNome());
			
			getStatement().executeUpdate();
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return this.getUltimo().getId();
	}

	private Escola getUltimo() {
		iniciaConexaoComBanco();
		Escola e = null;
		
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
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			e = new Escola();			
		}
		
		try {
			if(getResultado().next()) {
				e = new Escola(
					getResultado().getInt(cId),
					getResultado().getString(cNome)
				);
			} else e = new Escola();
		} catch (SQLException sqle) {}
		
		encerraConexaocomBanco();
		return e;
	}

	public List<Escola> getAll() {
		iniciaConexaoComBanco();
		
		List<Escola> escolas = new ArrayList<>();
		
		setSqlQuery("select * from " + nomeTabela + " order by " + cNome);
		
		try {
			setStatement(
				getDbConnection().prepareStatement(
					getSqlQuery()
				)
			);
			
			setResultado(getStatement().executeQuery());
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		
		try {
			Escola e;
			while(getResultado().next()) {
				e = new Escola(
					getResultado().getInt(cId),
					getResultado().getString(cNome)
				);
				
				escolas.add(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		encerraConexaocomBanco();
		
		return escolas;
	}
	
	public Escola getById(int id) {
		iniciaConexaoComBanco();
		Escola escola = null;
		
		setSqlQuery("select * from " + nomeTabela + " where " + cId +" = ?");
		
		try {
			setStatement(
				getDbConnection().prepareStatement(
					getSqlQuery()
				)
			);
			
			getStatement().setInt(1, id);

			
			setResultado(getStatement().executeQuery());
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		
		try {
			if(getResultado().next()) {
				escola = new Escola(
					getResultado().getInt(cId),
					getResultado().getString(cNome)
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		encerraConexaocomBanco();
		return escola;
	}
}
