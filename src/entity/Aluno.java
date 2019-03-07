package entity;

import org.joda.time.DateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import utilidades.CalcularData;

@Getter
@AllArgsConstructor
@ToString
public class Aluno {
	@Setter
	int id;
	String nome, rg, /* cpf,  endereço, cep,*/ bairro;
	Escola escola;
	private DateTime dataNascimento;
	
	public int getIdade() {
		return new CalcularData(dataNascimento).anosEntre();
	}
	
	public String getStringId() {
		String id = ""+this.id;
		
		switch(id.length()) {
			case 1:
				return "00"+this.id;
			case 2:
				return "0"+this.id;
			default:
				return ""+this.id;
		}		 
	}
}