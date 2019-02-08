package entity;
import org.joda.time.DateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Turno {
	int id, quantVales, idVendedor;
	DateTime data;
	String turno/*, nomeVendedor*/;
	@Setter
	boolean concluido;
}