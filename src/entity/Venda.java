package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Venda {
	int id, quantidade, idTurno;
	Aluno aluno;
}