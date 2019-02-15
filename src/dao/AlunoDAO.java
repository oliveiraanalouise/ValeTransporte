package dao;

import java.sql.SQLException;

import entity.Aluno;

public class AlunoDAO extends DAO{
	private final String colunaId = getNomeTabela() + ".id",
						 colunaNome = getNomeTabela() + ".nome",
						 colunaCpf = getNomeTabela() + ".cpf",
						 colunaRg = getNomeTabela() + ".rg",
						 colunaEndereco = getNomeTabela() + ".endereco",
						 colunaCep = getNomeTabela() + ".cep",
						 colunaEscola = getNomeTabela() + ".escola_id";

	public AlunoDAO() {
		super("aluno");
	}
	
	public boolean exist(Aluno aluno) {
		//retorna se o aluno já existe no banco através do rg e cpf
		iniciaConexaoComBanco();
		
		boolean exist = false;
		setSqlQuery("select "+colunaId+" from "+getNomeTabela()+" where "+colunaCpf+" = ? or "+colunaRg+" = ?");
		
		try {
			setStatement(getDbConnection().prepareStatement(getSqlQuery()));
			
			int posicao = 1;
			
			getStatement().setString(posicao, aluno.getCpf());
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
			"insert into "+getNomeTabela()+" ("+
				colunaNome+", "+
				colunaRg+", "+
				colunaCpf+", "+
				colunaEndereco+", "+
				colunaCep+", "+
				colunaEscola+
			") values (?,?,?,?,?,?)");
		
		try {
			setStatement(getDbConnection().prepareStatement(getSqlQuery()));
			
			int posicao = 1;
			
			getStatement().setString(posicao, aluno.getNome());
			getStatement().setString(++posicao, aluno.getRg());
			getStatement().setString(++posicao, aluno.getCpf());
			getStatement().setString(++posicao, aluno.getEndereço());
			getStatement().setString(++posicao, aluno.getCep());
			getStatement().setInt(++posicao, aluno.getEscola().getId());
			
			getStatement().executeUpdate();
		} catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		encerraConexaocomBanco();
	}
}