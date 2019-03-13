package utilidades;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.joda.time.DateTime;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import entity.Aluno;
import entity.Turno;
import entity.Venda;

public class PDF {
	/*
	 * pasta do contexto do tomcat em formato de string para que depois o cliente
	 * consiga abrir através de um link na página
	 */	
	private String pasta;
	Document d = new Document();
	Font f = new Font();
	String nomeArquivo;
	Paragraph paragrafo;
	DateTime hoje = new DateTime();
	
	public PDF (String pasta) {
		this.pasta = pasta;
		f.setSize(10);
	}
	
	public void relatorioTurno(Turno t) {
		try {
			iniciarArquivo("RelatorioTurno"+t.getId()+".pdf");
			String ano = (""+hoje.getYear()).substring(2);
			paragrafo = new Paragraph("RELAT�RIO DO TURNO DE VENDA N�:A/"+ano+"/"+t.getId()+"\n\n");
			paragrafo.setAlignment(1);
			d.add(paragrafo);
			
			PdfPTable tabela = new PdfPTable(4);
			
			PdfPCell celula = new PdfPCell(new Phrase("Dia"));
			celula.setHorizontalAlignment(Element.ALIGN_CENTER);
			tabela.addCell(celula);
			
			celula = new PdfPCell(new Phrase("Turno"));
			celula.setHorizontalAlignment(Element.ALIGN_CENTER);
			tabela.addCell(celula);
			
			celula = new PdfPCell(new Phrase("Recebidos"));
			celula.setHorizontalAlignment(Element.ALIGN_CENTER);
			tabela.addCell(celula);
			
			celula = new PdfPCell(new Phrase("Devolvidos"));
			celula.setHorizontalAlignment(Element.ALIGN_CENTER);
			tabela.addCell(celula);						
			
			String data = t.getData().getDayOfMonth() + "/" + t.getData().getMonthOfYear() + "/" + t.getData().getYear();
			celula = new PdfPCell(new Phrase(data)); 
			tabela.addCell(celula);
			
			celula = new PdfPCell(new Phrase(t.getTurno()));
			tabela.addCell(celula);
			
			celula = new PdfPCell(new Phrase(""+t.getQuantValesRecebidos()));
			tabela.addCell(celula);
			
			celula = new PdfPCell(new Phrase(""+t.getQuantVales()));
			tabela.addCell(celula);
			
			d.add(tabela);
			
			paragrafo = new Paragraph("\n");
			paragrafo.setAlignment(Element.ALIGN_CENTER);
			
			d.add(paragrafo);
			
			tabela = new PdfPTable(2);
			
			celula = new PdfPCell(new Phrase("Total arrecadado: R$"+t.valorArrecadadoAsString()));
			tabela.addCell(celula);
			
			celula = new PdfPCell(new Phrase("Total vendido: "+t.quantidadeVendido()));
			celula.setHorizontalAlignment(Element.ALIGN_RIGHT);
			tabela.addCell(celula);
			
			d.add(tabela);
			
			paragrafo = new Paragraph("\nVendas nesse turno\n\n");
			paragrafo.setAlignment(Element.ALIGN_CENTER);
			
			d.add(paragrafo);
			
			tabela = new PdfPTable(2);
			
			Font f2 = new Font();
			f2.setStyle(Font.BOLD);
			
			Phrase p = new Phrase();
			p.setFont(f2);
			p.add("Nome");
			celula = new PdfPCell(p);
			celula.setHorizontalAlignment(Element.ALIGN_CENTER);
			tabela.addCell(celula);
			
			p = new Phrase();
			p.setFont(f2);
			p.add("Quantidade");
			celula = new PdfPCell(p);
			celula.setHorizontalAlignment(Element.ALIGN_CENTER);
			tabela.addCell(celula);
			
			for(Venda v : t.getVendas()) {
				celula = new PdfPCell(new Phrase(v.getAluno().getNome()));
				tabela.addCell(celula);
				
				celula = new PdfPCell(new Phrase("" +v.getQuantidade()));
				celula.setHorizontalAlignment(Element.ALIGN_RIGHT);
				tabela.addCell(celula);
			}
			
			d.add(tabela);
			
			paragrafo = new Paragraph("\n");
			paragrafo.setAlignment(Element.ALIGN_CENTER);
			
			d.add(paragrafo);
			
			tabela = new PdfPTable(2);
			
			for(int i = 0; i<2; ++i) {
				celula = new PdfPCell(new Phrase("______________________________"));
				celula.setBorder(PdfPCell.NO_BORDER);
				celula.setHorizontalAlignment(Element.ALIGN_CENTER);
				tabela.addCell(celula);
			}
			
			p = new Phrase();
			p.setFont(f2);
			p.add(""+t.getVendedor().getNome());
			celula = new PdfPCell(p);
			celula.setBorder(PdfPCell.NO_BORDER);
			celula.setHorizontalAlignment(Element.ALIGN_CENTER);
			tabela.addCell(celula);
			
			p = new Phrase();
			p.setFont(f2);
			p.add(""+t.getResponsavel().getNome());
			celula = new PdfPCell(p);
			celula.setBorder(PdfPCell.NO_BORDER);
			celula.setHorizontalAlignment(Element.ALIGN_CENTER);
			tabela.addCell(celula);
			
			f2.setSize(10);
			
			p = new Phrase();
			p.setFont(f);
			p.add("Resp. pela venda");
			celula = new PdfPCell(p);
			celula.setBorder(PdfPCell.NO_BORDER);
			celula.setHorizontalAlignment(Element.ALIGN_CENTER);
			tabela.addCell(celula);
			
			p = new Phrase();
			p.setFont(f);
			p.add("Resp. pela confer�ncia");
			celula = new PdfPCell(p);
			celula.setBorder(PdfPCell.NO_BORDER);
			celula.setHorizontalAlignment(Element.ALIGN_CENTER);
			tabela.addCell(celula);
			
			f.setStyle(Font.ITALIC);
			d.add(tabela);
		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		} finally {
			d.close();
		}
	}
	
	public void comprovanteCadastro(Aluno a) {
		try {
			iniciarArquivo("ComprovanteCadastro"+a.getId()+".pdf");
			for(int i = 0; i < 2; ++i) {
//				deve fazer duas vias: uma fica com o aluno e outra com a CTB
				paragrafo = new Paragraph("MEIA-PASSAGEM ESCOLAR - VALE ESTUDANTE\nCOMPROVANTE DE CADASTRO - "
						+ hoje.getYear() + "\n\n");
				paragrafo.setAlignment(1);
				d.add(paragrafo);
	
				paragrafo = new Paragraph();
				paragrafo.setFont(f);
				paragrafo.setAlignment(Element.ALIGN_JUSTIFIED_ALL);
				paragrafo.add("Id: " + a.getStringId() + "         RG: " + a.getRg() + "         Idade: " + a.getIdade()
						+ " anos         Limite Mensal: 50 vales\n");
				d.add(paragrafo);
	
				paragrafo = new Paragraph();
				paragrafo.setFont(f);
				paragrafo.add("Nome: " + a.getNome() + " " + "                                Bairro: " + a.getBairro()
						+ "\n" + "Escola: " + a.getEscola().getNome() + "  na localidade " + a.getEscola().getBairro());
				d.add(paragrafo);
	
				paragrafo = new Paragraph();
	
				paragrafo.setAlignment(Element.ALIGN_CENTER);
				paragrafo.add("TERMO DE UTILIZA��O");
				d.add(paragrafo);
	
				paragrafo = new Paragraph();
				paragrafo.setFont(f);
				paragrafo.setAlignment(Element.ALIGN_JUSTIFIED);
				paragrafo.add("- Estudantes que n�o ter�o direito ao benef�cio da Meia Passagem Escolar - Vale Estudante:\n"
						+ "I. Estudantes de p�s-gradua��o, pr�-vestibulares e cursos n�o regulamentados pelo MEC;\n"
						+ "II. Estudantes que j� gozem da gratuidade do Sistema de Transporte Ferrovi�rio;\n"
						+ "II. Estudantes menores que 06 (seis) anos;\n"
						+ "Situa��es que implicam na suspens�o do benef�cio:\n"
						+ "I. Uso por terceiros, inclusive os seus acompanhantes; II. Uso por estudantes n�o cadastrados; III. Comercializa��o.\n"
						+ "Os benefici�rios do sistema de Meia Passagem Escolar - Vale Estudante dever�o apresentar o Cart�o de identifica��o, fornecido no ato do cadastramento, no momento em que for utilizar o seu benef�cio.");
				d.add(paragrafo);
	
				paragrafo = new Paragraph();
				paragrafo.setFont(f);
				paragrafo.setAlignment(Element.ALIGN_CENTER);
				paragrafo.add(
						"- PERDA DO CART�O - No caso de perda ou roubo do cart�o de identifica��o, solicitamos o Registro em Delegacia e posterior apresenta��o do Registro de Ocorr�ncia");
				d.add(paragrafo);
	
				paragrafo = new Paragraph();
				paragrafo.setFont(f);
				paragrafo.setAlignment(Element.ALIGN_JUSTIFIED_ALL);
				paragrafo.add(
						"- Aceito os termos de utiliza��o da Meia Passagem Escolar - Vale Estudante, inclusive os itens que implicam na suspens�o do benef�cio e atesto o recebimento do Cart�o de Meia Passagem Escolar.\n"
								+ "Data: " + hoje.getDayOfMonth() + "/" + hoje.getMonthOfYear() + "/" + hoje.getYear() + " "
								+ hoje.getHourOfDay() + ":" + hoje.getMinuteOfHour() + ":" + hoje.getSecondOfMinute() + ""
								+ "     Assinatura:_________________________________________________________\n");
				d.add(paragrafo);
	
				paragrafo = new Paragraph();
				paragrafo.setFont(f);
				paragrafo.setAlignment(Element.ALIGN_CENTER);
				paragrafo.add("          ______________________________________________________________\n"
						+ "                        Respons�vel pelo cadastro\n\n");
				d.add(paragrafo);
			}
		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		} finally {
			d.close();
		}		
		
		/* descomente esse trecho para o arquivo abrir no SERVIDOR quando for gerado
		 * try { File arquivo = new File(nomeArquivo);
		 * 
		 * Desktop.getDesktop().open(arquivo); } catch (IOException e) {
		 * e.printStackTrace(); }
		 */
	}

	private void iniciarArquivo(String string) throws FileNotFoundException, DocumentException {
		nomeArquivo = pasta+"\\"+string;
		
		PdfWriter.getInstance(d, new FileOutputStream(nomeArquivo));
		d.open();		
	}
}