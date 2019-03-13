package entity;

import java.math.BigDecimal;
import java.util.List;

import org.joda.time.DateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import utilidades.FormatarCampo;

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

	public double valorArrecadado() {
		return (quantValesRecebidos - quantVales) * 0.25;
	}
	
	public String valorArrecadadoAsString() {
		BigDecimal valor = new BigDecimal(valorArrecadado());
		return new FormatarCampo().decimalToString(valor);
	}
	
	public int quantidadeVendido() {
		return quantValesRecebidos - quantVales;
	}
	
	public Turno(int id, int quantVales, DateTime data, String turno, boolean estado, Usuario vendedor,
			Usuario responsavel, List<Venda> vendas) {
		this.id = id;
		this.quantVales = quantVales;
		this.quantValesRecebidos = quantVales;
		this.data = data;
		this.turno = turno;
		this.concluido = estado;
		this.vendedor = vendedor;
		this.responsavel = responsavel;
		this.vendas = vendas;
		
		for(Venda v: vendas) {
			vendaTicket(v.getQuantidade());
		}
	}
}