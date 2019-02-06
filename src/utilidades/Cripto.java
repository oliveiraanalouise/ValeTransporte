/*
 * Classe responsável por criptografar as senhas
 * Necessidade de criar classe separada pois é usada em mais de uma classe distinta 
 */

package utilidades;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

public class Cripto {
	public Cripto(){}
	
	public String criptografa(String senha){
		/*
		 * Código retirado do site http://www.guj.com.br/t/cadastro-de-usuario-com-senha-criptografada/192679
		 */
		
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			digest.update(senha.getBytes());
			BASE64Encoder encoder = new BASE64Encoder();
			return encoder.encode(digest.digest());
		} catch (NoSuchAlgorithmException ns) {
			ns.printStackTrace();
		}
		return senha;
	}
}
