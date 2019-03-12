package dao;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import entity.Turno;
import entity.Usuario;

public class TurnoDAO extends DAO {
	private final String 
		cData = nomeTabela + ".data",
		cTurno = nomeTabela + ".turno", 
		cQuantVales = nomeTabela + ".quantidadeVales",
		cIdVendedor = nomeTabela + ".vendedor",
		cIdResponsavel = nomeTabela + ".responsavel",
		cConcluido = nomeTabela + ".concluido"
		/*,
		cNomeVendedor = new UsuarioDAO().nomeTabela+".nome"*/;

	public TurnoDAO() {
		super("turno");
	}

	public void inserir(Turno t) {
		iniciaConexaoComBanco(
				"insert into "+nomeTabela+" ("+ 
				cData + ", " +
				cTurno + ", " +
				cQuantVales + ", " +
				cIdVendedor + ", " +
				cIdResponsavel
			+") values (?,?,?,?,?)"
		);
		
		try {
			int posicao = 1;
//			Preenche o statement
			getStatement().setDate(posicao, new Date(t.getData().toDate().getTime()));
			getStatement().setString(++posicao, t.getTurno());
			getStatement().setInt(++posicao, t.getQuantVales());
			getStatement().setInt(++posicao, t.getVendedor().getId());
			getStatement().setInt(++posicao, t.getResponsavel().getId());

//			executa
			getStatement().executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		encerraConexaocomBanco();
	}
	
	public Turno getUltimo(){
		iniciaConexaoComBanco(
			"select * from " + nomeTabela+" order by " + cId + " desc limit 1"
		);
		Turno t = null;
		
		try {						
			setResultado(getStatement().executeQuery());
			
			
			if(getResultado().next()) {
				int id = getResultado().getInt(cId);
				
				t = new Turno(
					id,
					getResultado().getInt(cQuantVales),
					new DateTime(getResultado().getDate(cData)),
					getResultado().getString(cTurno),
					getResultado().getBoolean(cConcluido),
					new UsuarioDAO(getDbConnection()).getById(getResultado().getInt(cIdVendedor)),
					new UsuarioDAO(getDbConnection()).getById(getResultado().getInt(cIdResponsavel)),
					new VendaDAO(getDbConnection()).getByIdTurno(id)
				);
			} else t = new Turno();
		} catch (SQLException e) {
			e.printStackTrace();
			t = new Turno();	
		}
		
		encerraConexaocomBanco();
		return t;
	}

	public void atualizar(Turno t) {
		iniciaConexaoComBanco(
			"update "+nomeTabela+" set "+ 
			/*
			 * cData + " = ?, " + cTurno + " = ?, " + cQuantVales + " = ?, "
			 * + cIdVendedor + " = ?, " +
			 */cConcluido	+ " = ? where " +
			cId + " = ?"
		);
		
		try {
			int posicao = 1;
			
			/*
			 * getStatement().setDate(posicao, new Date(t.getData().toDate().getTime()));
			 * getStatement().setString(++posicao, t.getTurno());
			 * getStatement().setInt(++posicao, t.getQuantVales());
			 * getStatement().setInt(++posicao, t.getIdVendedor());
			 */
			getStatement().setBoolean(posicao, t.isConcluido());
			getStatement().setInt(++posicao, t.getId());
			
			getStatement().executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		encerraConexaocomBanco();
	}

	public List<Turno> getAll() {
		iniciaConexaoComBanco("select * from " + nomeTabela+" order by " + cId + " desc");
		List<Turno> turnos = new ArrayList<Turno>();
		
		try {
			setResultado(getStatement().executeQuery());
		
			Turno t = null;
			int id;
			while(getResultado().next()) {
				id = getResultado().getInt(cId);
						
				t = new Turno(
					getResultado().getInt(cId),
					getResultado().getInt(cQuantVales),
					new DateTime(getResultado().getDate(cData)),
					getResultado().getString(cTurno),
					getResultado().getBoolean(cConcluido),
					new Usuario(getResultado().getInt(cIdVendedor),null,null,null,null),
					new Usuario(getResultado().getInt(cIdResponsavel),null,null,null,null),
					new VendaDAO(getDbConnection()).getByIdTurno(id)
				);
				
				turnos.add(t);
			} 
		} catch (SQLException e) {}
		
		encerraConexaocomBanco();
		return turnos;
	}
}
