package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Venda {
	int id, quantidade;
	Aluno aluno;
	Usuario vendedor;
	Turno turno;
}