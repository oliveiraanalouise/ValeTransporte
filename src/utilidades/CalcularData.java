/* 
 * Classe usada para calcular a distância entre data
 * usada para saber quantos dias faltam para o vencimento do contrato 
 * funciona tanto com uma data específica no momento da chamada do método quanto passando uma data na instaciação do objeto
 */

package utilidades;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Years;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CalcularData {
	private DateTime hoje = new DateTime(),
			 outra = hoje;
	
	public CalcularData(DateTime proxima) {
		//usada para setar a próxima data para referência 
		this.outra = proxima;
	}
	
	//retorna a quantidade de dias entre a data inserida e a data de hoje
	public int diasEntre(DateTime data) {
		//+ 1 pois o getDays vai até um dia antes ao dia escolhido 
		return Days.daysBetween(hoje, data).getDays() + 1;
	}
	
	public int diasEntre() {
		return Days.daysBetween(hoje, outra).getDays() + 1;
	}
	
	public int anosEntre() {
		return Years.yearsBetween(outra, hoje).getYears();
	}
}
