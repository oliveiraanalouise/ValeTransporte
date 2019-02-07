package dao;

import java.sql.Connection;
import java.sql.SQLException;

import entity.Usuario;

public class UsuarioDAO extends DAO{
	private final String colunaId = getNomeTabela() + ".id", 
			 colunaNome = getNomeTabela() + ".nome", 
			 colunaLogin = getNomeTabela() + ".login",
			 colunaSenha = getNomeTabela() + ".senha",
			 colunaSupervisor = getNomeTabela() + ".supervisor",
			 colunaNomeSupervisor = getNomeTabela() + "2" + ".nome",
			 colunaIdSupervisor = getNomeTabela() + "2" + ".id";
	private static final String tabela = "usuario";
	
	public UsuarioDAO() {
		super(tabela);
	}
	
	protected UsuarioDAO(Connection conexao) {
		super(tabela, conexao);
	}

	public Usuario getByEmail(String email) {
		iniciaConexaoComBanco();

		Usuario u = null;
		
//		monta a query
		setSqlQuery(
			"select " + colunaId + ", " + colunaNome + ", " + colunaLogin + ", " + colunaSenha + ", " + colunaNomeSupervisor + " FROM "+getNomeTabela()+" inner join "+getNomeTabela()+" as "+getNomeTabela()+"2 on "+ colunaSupervisor+"="+colunaIdSupervisor+" where "+colunaLogin+" = ?"
//			"select * from " + getNomeTabela() + " where " + colunaLogin + " = ?"
		);
		
		try {
//			monta o statement
			setStatement(
				getDbConnection().prepareStatement(
					getSqlQuery()
				)
			);
			
//			Preenche o statement
			getStatement().setString(
				1, 
				email
			);
			
//			executa
			setResultado(
				getStatement().executeQuery()
			);

			if (getResultado().next()) {
				u = new Usuario(
					getResultado().getInt(colunaId),
					getResultado().getString(colunaNome),
					getResultado().getString(colunaLogin), 
					getResultado().getString(colunaSenha), 
					getResultado().getString(colunaNomeSupervisor)
				);
			} else
				u = new Usuario();
			
		} catch(SQLException e) {
			e.printStackTrace();
			u = new Usuario();
		}
		
		encerraConexaocomBanco();
		return u;
	}
	
	

}
