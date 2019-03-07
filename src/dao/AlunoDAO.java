package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import entity.Aluno;

public class AlunoDAO extends DAO {
	private final String cNome = nomeTabela + ".nome",
//						 cCpf = nomeTabela + ".cpf",
			cRg = nomeTabela + ".rg",
//						 cEndereco = nomeTabela + ".endereco",
//						 cCep = nomeTabela + ".cep",
			cBairro = nomeTabela + ".bairro", cEscola = nomeTabela + ".escola_id",
			cDataNascimento = nomeTabela + ".nascimento";

	public AlunoDAO() {
		super("aluno");
	}

	public AlunoDAO(Connection c) {
		super("aluno", c);
	}

	public boolean exist(Aluno aluno) {
		// retorna se o aluno já existe no banco através do rg e cpf
		iniciaConexaoComBanco("select " + cId + " from " + nomeTabela + " where "/* +cCpf+" = ? or " */ + cRg + " = ?");

		boolean exist = false;

		try {
			int posicao = 1;

//			getStatement().setString(posicao, aluno.getCpf());
			getStatement().setString(posicao, aluno.getRg());

			setResultado(getStatement().executeQuery());

			if (getResultado().next()) {
				exist = true;
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		encerraConexaocomBanco();
		return exist;
	}

	public int inserir(Aluno aluno) {
		iniciaConexaoComBanco("insert into " + nomeTabela + " (" + cNome + ", " + cRg + ", " +
//			cCpf+", "+
//			cEndereco+", "+
//			cCep+", "+
				cBairro + ", " + cEscola + ", " + cDataNascimento + ") values (?,?,?,?,?)");

		try {
			int posicao = 1;

			getStatement().setString(posicao, aluno.getNome());
			getStatement().setString(++posicao, aluno.getRg());
			getStatement().setString(++posicao, aluno.getBairro());
			getStatement().setInt(++posicao, aluno.getEscola().getId());
			getStatement().setDate(++posicao, new Date(aluno.getDataNascimento().toDate().getTime()));

			getStatement().executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		iniciaConexaoComBanco("select "+cId+" from " + nomeTabela + " order by " + cId + " desc limit 1");
		int id = -1;
		
		try {
			setResultado(getStatement().executeQuery());

			if (getResultado().next()) {
				id = getResultado().getInt(cId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		encerraConexaocomBanco();
		
		return id;
	}

	public List<Aluno> getAll() {
		iniciaConexaoComBanco("select * from " + nomeTabela + " order by " + cNome);

		List<Aluno> alunos = new ArrayList<>();

		try {
			setResultado(getStatement().executeQuery());

			Aluno a;

			while (getResultado().next()) {
				a = new Aluno(getResultado().getInt(cId), getResultado().getString(cNome),
						getResultado().getString(cRg),
//					getResultado().getString(cCpf),
//					getResultado().getString(cEndereco),
//					getResultado().getString(cCep),
						getResultado().getString(cBairro),
						new EscolaDAO(getDbConnection()).getById(getResultado().getInt(cEscola)),
						new DateTime(getResultado().getDate(cDataNascimento)));

				alunos.add(a);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		encerraConexaocomBanco();
		return alunos;
	}

	public Aluno getById(int id) {
		iniciaConexaoComBanco("select * from " + nomeTabela + " where " + cId + " =?");

		Aluno a = null;

		try {
			getStatement().setInt(1, id);
			setResultado(getStatement().executeQuery());

			if (getResultado().next()) {
				a = new Aluno(getResultado().getInt(cId), getResultado().getString(cNome),
						getResultado().getString(cRg),
//					getResultado().getString(cCpf),
//					getResultado().getString(cEndereco),
//					getResultado().getString(cCep),
						getResultado().getString(cBairro),
						new EscolaDAO(getDbConnection()).getById(getResultado().getInt(cEscola)),
						new DateTime(getResultado().getDate(cDataNascimento)));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		encerraConexaocomBanco();
		return a;
	}
}