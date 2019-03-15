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
		iniciaConexaoComBanco(
				"select " + cId + ", " + cNome + ", " + cLogin + ", " + cSenha + ", " + cNomeSupervisor + " FROM "
				+ nomeTabela + " inner join " + nomeTabela + " as " + nomeTabela + "2 on " + cSupervisor + "="
				+ cIdSupervisor + " where " + cLogin + " = ?"
//				"select * from " + nomeTabela + " where " + cLogin + " = ?"		
		);

		Usuario u = null;

		try {
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
		iniciaConexaoComBanco("select " + cId + ", " + cNome + ", " + cLogin + ", " + cSenha + ", " + cNomeSupervisor + " FROM "
				+ nomeTabela + " inner join " + nomeTabela + " as " + nomeTabela + "2 on " + cSupervisor + "="
				+ cIdSupervisor + " where " + cId + " = ?");
		Usuario u = null;

		try {
			getStatement().setInt(1, id);
			setResultado(getStatement().executeQuery());
		
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
		iniciaConexaoComBanco("select * from " + nomeTabela + " where " + cId + " = " + cSupervisor);

		List<Usuario> lista = new ArrayList<Usuario>();
		

		try {
			setResultado(getStatement().executeQuery());
		
			Usuario u = null;
			
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
	
	public void inserir(Usuario u) {
		iniciaConexaoComBanco("insert into " + nomeTabela + " (" + cNome + ", " + cLogin + ", " + cSenha + ", " + cSupervisor + ") values (?,?,?,?)");
		
		try {
			int posicao = 1;
			
			getStatement().setString(posicao++, u.getNome());
			getStatement().setString(posicao++, u.getLogin());
			getStatement().setString(posicao++, u.getSenha());
			getStatement().setInt(posicao++, Integer.parseInt(u.getSupervisor()));
			
			getStatement().executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}