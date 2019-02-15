package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Escola;

public class EscolaDAO extends DAO {
	private final String colunaId = getNomeTabela() + ".id", 
						 colunaNome = getNomeTabela() + ".nome";

	public EscolaDAO() {
		super("escola");
	}

	public int inserir(Escola e) {
		//retorn a id do registro que acabou de ser inserido
		iniciaConexaoComBanco();
		setSqlQuery("insert into " + getNomeTabela() + " (" + colunaNome + ") values (?)");
		
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
			"select * from " + getNomeTabela()+" order by " + colunaId + " desc limit 1"
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
					getResultado().getInt(colunaId),
					getResultado().getString(colunaNome)
				);
			} else e = new Escola();
		} catch (SQLException sqle) {}
		
		encerraConexaocomBanco();
		return e;
	}

	public List<Escola> getAll() {
		iniciaConexaoComBanco();
		
		List<Escola> escolas = new ArrayList<>();
		
		setSqlQuery("select * from " + getNomeTabela() + " order by " + colunaNome);
		
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
					getResultado().getInt(colunaId),
					getResultado().getString(colunaNome)
				);
				
				escolas.add(e);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		encerraConexaocomBanco();
		
		return escolas;
	}
}
