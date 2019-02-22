package entity;
import org.joda.time.DateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
public class Turno {
	int id, quantVales, idVendedor;
	DateTime data;
	String turno/*, nomeVendedor*/;
	@Setter
	boolean concluido;
	
	public void resetTurno() {
		this.turno = "Encerrado";
	}
	
	public void vendaTicket(int quantidade) {
		this.quantVales -= quantidade;
	}
}