package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private String ip = "localhost",
		    porta = "3306",
		    nomeBanco = "", 
		    usuarioBanco = "root", 
		    senhaBanco = "";
	
	public DBConnection(String ip, String nomeBanco, String usuarioBanco, String senhaBanco) {
		this.ip = ip;
		this.nomeBanco = nomeBanco;
		this.usuarioBanco = usuarioBanco;
		this.senhaBanco = senhaBanco;
	}
	
	public Connection getConnection() {
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			
			String parametro1 = "autoReconnect=true",
				   parametro2 = "&useSSL=false",
				   parametro3 = "&allowPublicKeyRetrieval=true"; //bug resolvido ao adicionar esse parâmetro na url
			
			/*Exemplo de url
			jdbc:mysql://ip:porta/nomeDoBanco?autoReconnect=true&useSSL=false*/			
			return DriverManager.getConnection(
				"jdbc:mysql://" + ip + ":" + porta +"/" + nomeBanco + "?" + parametro1 + parametro2 + parametro3, 
				usuarioBanco, 
				senhaBanco
			);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
