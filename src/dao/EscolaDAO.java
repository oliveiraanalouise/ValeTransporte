package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;

import entity.Escola;

public class EscolaDAO extends DAO {
	private final String cNome = nomeTabela + ".nome",
						 cBairro = nomeTabela + ".bairro";

	public EscolaDAO() {
		super("escola");
	}
	
	public EscolaDAO(Connection connection) {
		super("escola", connection);
	}

	public int inserir(Escola e) {
		//retorn a id do registro que acabou de ser inserido
		iniciaConexaoComBanco("insert into " + nomeTabela + " (" + cNome+", "+cBairro + ") values (?,?)");

		try {			
			int posicao = 1;
			getStatement().setString(posicao++, e.getNome());
			getStatement().setString(posicao++, e.getBairro());
			
			getStatement().executeUpdate();
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return this.getUltimo().getId();
	}

	private Escola getUltimo() {
		iniciaConexaoComBanco("select * from " + nomeTabela+" order by " + cId + " desc limit 1");
		Escola e = null;
		
		try {
			setResultado(getStatement().executeQuery());
		
			if(getResultado().next()) {
				e = new Escola(
					getResultado().getInt(cId),
					getResultado().getString(cNome),
					getResultado().getString(cBairro)
				);
			} else e = new Escola();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			e = new Escola();		
		}
		
		encerraConexaocomBanco();
		return e;
	}

	public List<Escola> getAll() {
		iniciaConexaoComBanco("select * from " + nomeTabela + " order by " + cNome);
		
		List<Escola> escolas = new ArrayList<>();
		
		try {
			setResultado(getStatement().executeQuery());
		
			Escola e;
			while(getResultado().next()) {
				e = new Escola(
					getResultado().getInt(cId),
					getResultado().getString(cNome),
					getResultado().getString(cBairro)
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
		iniciaConexaoComBanco("select * from " + nomeTabela + " where " + cId +" = ?");
		Escola escola = null;
		
		try {			
			getStatement().setInt(1, id);
			
			setResultado(getStatement().executeQuery());
		
			if(getResultado().next()) {
				escola = new Escola(
					getResultado().getInt(cId),
					getResultado().getString(cNome),
					getResultado().getString(cBairro)
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		encerraConexaocomBanco();
		return escola;
	}
}
