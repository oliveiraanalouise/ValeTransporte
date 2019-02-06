package dao;

import java.sql.Connection;
import java.sql.SQLException;

import entity.Usuario;

public class UsuarioDAO extends DAO{
	private final String colunaId = getNomeTabela() + ".id", 
			 colunaNome = getNomeTabela() + ".nome", 
			 colunaLogin = getNomeTabela() + ".login",
			 colunaSenha = getNomeTabela() + ".senha",
			 colunaSupervisor = getNomeTabela() + ".supervisor";
	private static final String tabela = "usuario";
	
	public UsuarioDAO() {
		super(tabela);
	}
	
	protected UsuarioDAO(Connection conexao) {
		super(tabela, conexao);
	}

	public Usuario getByEmail(String email) {
		iniciaConexaoComBanco();
		
/*		
 		Exemplo de query para esse método
 		
 		select * from usuario where usuario.login = ?";
 		depois busca setor e cargo através do resultado do usuario
 		
*/
		Usuario u = null;
		
//		monta a query
		setSqlQuery(
//			SELECT usuario.id, usuario.nome, usuario.login, usuario.senha, usuario.supervisor, u2.nome FROM usuario inner join usuario as u2 on usuario.supervisor=u2.id;
			"select * from " + getNomeTabela() + " where " + colunaLogin + " = ?"
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
			
			if(getResultado().next()){
				u = new Usuario(
					getResultado().getInt(
						colunaId
					),
					getResultado().getString(
						colunaNome
					),
					getResultado().getString(
						colunaLogin
					),
					getResultado().getString(
						colunaSenha
					),				
					""
				);
			}
		} catch(SQLException e) {
			e.printStackTrace();
			u = new Usuario();
		}
		
		encerraConexaocomBanco();
		return u;
	}
	
	

}
