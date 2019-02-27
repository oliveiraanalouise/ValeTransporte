package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Aluno;

public class AlunoDAO extends DAO{
	private final String cNome = nomeTabela + ".nome",
//						 cCpf = nomeTabela + ".cpf",
						 cRg = nomeTabela + ".rg",
						 cEndereco = nomeTabela + ".endereco",
						 cCep = nomeTabela + ".cep",
						 cBairro = nomeTabela + ".bairro",
						 cEscola = nomeTabela + ".escola_id";

	public AlunoDAO() {
		super("aluno");
	}
	
	public AlunoDAO(Connection c) {
		super("aluno", c);
	}
	
	public boolean exist(Aluno aluno) {
		//retorna se o aluno já existe no banco através do rg e cpf
		iniciaConexaoComBanco();
		
		boolean exist = false;
		setSqlQuery("select "+cId+" from "+nomeTabela+" where "/*+cCpf+" = ? or "*/+cRg+" = ?");
		
		try {
			setStatement(getDbConnection().prepareStatement(getSqlQuery()));
			
			int posicao = 1;
			
//			getStatement().setString(posicao, aluno.getCpf());
			getStatement().setString(++posicao, aluno.getRg());
			
			setResultado(getStatement().executeQuery());
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		try {
			if(getResultado().next()) {
				exist = true;
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		encerraConexaocomBanco();
		return exist;
	}

	public void inserir(Aluno aluno) {
		iniciaConexaoComBanco();
		
		setSqlQuery(
			"insert into "+nomeTabela+" ("+
				cNome+", "+
				cRg+", "+
//				cCpf+", "+
				cEndereco+", "+
				cCep+", "+
				cBairro+", "+
				cEscola+
			") values (?,?,?,?,?,?)");
		
		try {
			setStatement(getDbConnection().prepareStatement(getSqlQuery()));
			
			int posicao = 1;
			
			getStatement().setString(posicao, aluno.getNome());
			getStatement().setString(++posicao, aluno.getRg());
//			getStatement().setString(++posicao, aluno.getCpf());
			getStatement().setString(++posicao, aluno.getEndereço());
			getStatement().setString(++posicao, aluno.getCep());
			getStatement().setInt(++posicao, aluno.getEscola().getId());
			
			getStatement().executeUpdate();
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		encerraConexaocomBanco();
	}

	public List<Aluno> getAll() {
		iniciaConexaoComBanco();

		List<Aluno> alunos = new ArrayList<>();
		setSqlQuery("select * from " + nomeTabela +" order by " +cNome);

		try {
			setStatement(getDbConnection().prepareStatement(getSqlQuery()));

			setResultado(getStatement().executeQuery());
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		try {
			Aluno a;

			while (getResultado().next()) {
				a = new Aluno(
					getResultado().getInt(cId),
					getResultado().getString(cNome),
					getResultado().getString(cRg),
//					getResultado().getString(cCpf),
					getResultado().getString(cEndereco),
					getResultado().getString(cCep),
					getResultado().getString(cBairro),
					new EscolaDAO(getDbConnection()).getById(getResultado().getInt(cEscola))
				);
				
				alunos.add(a);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		encerraConexaocomBanco();
		return alunos;
	}

	public Aluno getById(int id) {
		iniciaConexaoComBanco();

		setSqlQuery("select * from " + nomeTabela +" where "+cId+" =?");

		try {
			setStatement(getDbConnection().prepareStatement(getSqlQuery()));

			getStatement().setInt(1, id);
			setResultado(getStatement().executeQuery());
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		
		Aluno a = null;
		
		try {
			if (getResultado().next()) {
				a = new Aluno(
					getResultado().getInt(cId),
					getResultado().getString(cNome),
					getResultado().getString(cRg),
//					getResultado().getString(cCpf),
					getResultado().getString(cEndereco),
					getResultado().getString(cCep),
					getResultado().getString(cBairro),
					new EscolaDAO(getDbConnection()).getById(getResultado().getInt(cEscola))
				);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		encerraConexaocomBanco();
		return a;
	}
}