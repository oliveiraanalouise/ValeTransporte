package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Aluno {
	int id;
	String nome;
	Escola escola;
}
