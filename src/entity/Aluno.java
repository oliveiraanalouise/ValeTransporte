package entity;

import org.joda.time.DateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import utilidades.CalcularData;

@Getter
@AllArgsConstructor
@ToString
public class Aluno {
	int id;
	String nome, rg, /* cpf,  endereço, cep,*/ bairro;
	Escola escola;
	private DateTime dataNascimento;
	
	public int getIdade() {
		return new CalcularData(dataNascimento).anosEntre();
	}
}