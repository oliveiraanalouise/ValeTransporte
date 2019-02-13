/* 
 * Classe usada para comparação de nomes entre dois objetos através do Collections.sort, não importando qual a classe dos 
 * objetos envolvidos 
 * Ambos os objetos devem ter o método "String getNome(){}" para que a comparação seja feita corretamente
 */

package utilidades;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Comparator;

public class ComparadorNome implements Comparator<Object> {

	@Override
	public int compare(Object arg0, Object arg1) {
		String s1 = "", s2 = "";
		
		try {
			//cria um método getNome()
			Method getNome = arg0.getClass().getMethod("getNome");
			
			//chama o método getNome() do primeiro parametro
			s1 = (String) getNome.invoke(arg0);
			
			//chama o método getNome() do segundo parametro
			s2 = (String) getNome.invoke(arg1);
		} catch (NoSuchMethodException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			return -1;
		}
		
		//compara as strings e retorna o resultado
		return s1.compareToIgnoreCase(s2);
	}

}
