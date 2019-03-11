package entity;

import java.util.List;

import org.joda.time.DateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@ToString
public class Turno {
	int id, quantVales, quantValesRecebidos;
	DateTime data;
	String turno/*, nomeVendedor*/;
	@Setter
	boolean concluido;
	Usuario vendedor, responsavel;
	List<Venda> vendas;
	
	public void resetTurno() {
		this.turno = "Encerrado";
	}
	
	public void vendaTicket(int quantidade) {
		this.quantVales -= quantidade;
	}

	public float
	public Turno(int id2, int int1, DateTime dateTime, String string, boolean boolean1, Usuario usuario,
			Usuario usuario2, List<Venda> byIdTurno) {
		this.id = id2;
		this.quantVales = int1;
		this.quantValesRecebidos = int1;
		this.data = dateTime;
		this.turno = string;
		this.concluido = boolean1;
		this.vendedor = usuario;
		this.responsavel = usuario2;
		this.vendas = byIdTurno;
		
		for(Venda v: vendas) {
			vendaTicket(v.getQuantidade());
		}
	}
}