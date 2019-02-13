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

	public void inserir(Escola e) {
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
		
		encerraConexaocomBanco();
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
