package dao;

import java.sql.Date;
import java.sql.SQLException;

import org.joda.time.DateTime;

import entity.Turno;

public class TurnoDAO extends DAO {
	private final String 
		colunaId = getNomeTabela() + ".id", 
		colunaData = getNomeTabela() + ".data",
		colunaTurno = getNomeTabela() + ".turno", 
		colunaQuantVales = getNomeTabela() + ".quantidadeVales",
		colunaConcluido = getNomeTabela() + ".concluido",
		colunaIdVendedor = getNomeTabela() + ".vendedor",
		colunaNomeVendedor = new UsuarioDAO().getNomeTabela()+".nome";

	public TurnoDAO() {
		super("turno");
	}

	public void inserir(Turno t) {
		iniciaConexaoComBanco();
		
		setSqlQuery(
			"insert into "+getNomeTabela()+" ("+ 
				colunaData + ", " +
				colunaTurno + ", " +
				colunaQuantVales + ", " +
				colunaIdVendedor
			+") values (?,?,?,?)"				
		);
		try {
			setStatement(
				getDbConnection().prepareStatement(
					getSqlQuery()
				)
			);
			int posicao = 1;
//			Preenche o statement
			getStatement().setDate(posicao, new Date(t.getData().toDate().getTime()));
			getStatement().setString(++posicao, t.getTurno());
			getStatement().setInt(++posicao, t.getQuantVales());
			getStatement().setInt(++posicao, t.getIdVendedor());

//			executa
			getStatement().executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		encerraConexaocomBanco();
	}
	
	public Turno getUltimo(){
		iniciaConexaoComBanco();
		Turno t = null;
		
		setSqlQuery(
			"select * from " + getNomeTabela()+" order by " + colunaId + " desc limit 1"
		);
		
		try {
			setStatement(
				getDbConnection().prepareStatement(
					getSqlQuery()
				)
			);
			
			setResultado(getStatement().executeQuery());
		} catch (SQLException e) {
			e.printStackTrace();
			t = new Turno();			
		}
		
		try {
			if(getResultado().next()) {
				t = new Turno(
					getResultado().getInt(colunaId),
					getResultado().getInt(colunaQuantVales),
					getResultado().getInt(colunaIdVendedor),
					new DateTime(getResultado().getDate(colunaData)),
					getResultado().getString(colunaTurno),
					getResultado().getBoolean(colunaConcluido)
				);
			} else t = new Turno();
		} catch (SQLException e) {}
		
		encerraConexaocomBanco();
		return t;
	}
}
