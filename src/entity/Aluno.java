package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Aluno {
	int id;
	String nome;
	Escola escola;
}
