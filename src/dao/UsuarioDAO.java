package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Usuario;

public class UsuarioDAO extends DAO {
	private final String cNome = nomeTabela + ".nome", cLogin = nomeTabela + ".login", cSenha = nomeTabela + ".senha",
			cSupervisor = nomeTabela + ".supervisor", cNomeSupervisor = nomeTabela + "2" + ".nome",
			cIdSupervisor = nomeTabela + "2" + ".id";

	public UsuarioDAO() {
		super("usuario");
	}

	protected UsuarioDAO(Connection conexao) {
		super("usuario", conexao);
	}

	public Usuario getByEmail(String email) {
		iniciaConexaoComBanco();

		Usuario u = null;

//		monta a query
		setSqlQuery("select " + cId + ", " + cNome + ", " + cLogin + ", " + cSenha + ", " + cNomeSupervisor + " FROM "
				+ nomeTabela + " inner join " + nomeTabela + " as " + nomeTabela + "2 on " + cSupervisor + "="
				+ cIdSupervisor + " where " + cLogin + " = ?"
//			"select * from " + nomeTabela + " where " + cLogin + " = ?"
		);

		try {
//			monta o statement
			setStatement(getDbConnection().prepareStatement(getSqlQuery()));

//			Preenche o statement
			getStatement().setString(1, email);

//			executa
			setResultado(getStatement().executeQuery());

			if (getResultado().next()) {
				u = new Usuario(getResultado().getInt(cId), getResultado().getString(cNome),
						getResultado().getString(cLogin), getResultado().getString(cSenha),
						getResultado().getString(cNomeSupervisor));
			} else
				u = new Usuario();

		} catch (SQLException e) {
			e.printStackTrace();
			u = new Usuario();
		}

		encerraConexaocomBanco();
		return u;
	}

	public Usuario getById(int id) {
		iniciaConexaoComBanco();

		setSqlQuery("select " + cId + ", " + cNome + ", " + cLogin + ", " + cSenha + ", " + cNomeSupervisor + " FROM "
				+ nomeTabela + " inner join " + nomeTabela + " as " + nomeTabela + "2 on " + cSupervisor + "="
				+ cIdSupervisor + " where " + cId + " = ?");

		try {
			setStatement(getDbConnection().prepareStatement(getSqlQuery()));

			getStatement().setInt(1, id);
			setResultado(getStatement().executeQuery());
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		Usuario u = null;

		try {
			if (getResultado().next()) {
				u = new Usuario(getResultado().getInt(cId), getResultado().getString(cNome),
						getResultado().getString(cLogin), getResultado().getString(cSenha),
						getResultado().getString(cNomeSupervisor));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		encerraConexaocomBanco();
		return u;
	}
	
	public List<Usuario> getAllSupervisor() {
		iniciaConexaoComBanco();

		List<Usuario> lista = new ArrayList<Usuario>();
		
		setSqlQuery("select * from " + nomeTabela + " where " + cId + " = " + cSupervisor);

		try {
			setStatement(getDbConnection().prepareStatement(getSqlQuery()));

			setResultado(getStatement().executeQuery());
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		Usuario u = null;

		try {
			while (getResultado().next()) {
				u = new Usuario(getResultado().getInt(cId), getResultado().getString(cNome),
						getResultado().getString(cLogin), getResultado().getString(cSenha),
						getResultado().getString(cNome));
				
				lista.add(u);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		encerraConexaocomBanco();
		return lista;
	}
}