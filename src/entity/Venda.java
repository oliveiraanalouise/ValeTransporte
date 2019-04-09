package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Venda {
	int id, quantidade, idTurno;
	Aluno aluno;
}