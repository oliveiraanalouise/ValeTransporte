package utilidades;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.joda.time.DateTime;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
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
	private String pasta, nomeArquivo;
	private Document d = new Document();
	private Font f = new Font();
	private Paragraph paragrafo;
	private DateTime hoje = new DateTime();
	private Image logoPequeno;
	
	public PDF (String pasta) {
		this.pasta = pasta;
		f.setSize(10);
	}
	
	public void relatorioTurno(Turno t) {
		try {
			iniciarArquivo("RelatorioTurno"+t.getId()+".pdf");
			
			d.add(logoPequeno);
			
			String ano = (""+hoje.getYear()).substring(2);
			paragrafo = new Paragraph("RELATÓRIO DO TURNO DE VENDA Nº:A/"+ano+"/"+t.getId()+"\n\n");
			paragrafo.setAlignment(Element.ALIGN_CENTER);
			d.add(paragrafo);
			
			PdfPTable tabela = new PdfPTable(4);

			tabela.addCell(celula(frase("Dia")));			
			tabela.addCell(celula(frase("Turno")));		
			tabela.addCell(celula(frase("Recebidos")));			
			tabela.addCell(celula(frase("Devolvidos")));		
			
			tabela.addCell(new PdfPCell(new Phrase(t.getDataAsString())));
			tabela.addCell(new PdfPCell(new Phrase(t.getTurno())));
			tabela.addCell(new PdfPCell(new Phrase(""+t.getQuantValesRecebidos())));
			tabela.addCell(new PdfPCell(new Phrase(""+t.getQuantVales())));
			
			d.add(tabela);
			
			paragrafo = new Paragraph("\n");
			
			d.add(paragrafo);
			
			tabela = new PdfPTable(2);
			 
			tabela.addCell(new PdfPCell(new Phrase("Total arrecadado: R$"+t.valorArrecadadoAsString())));			
			tabela.addCell(celula(new Phrase("Total vendido: "+t.quantidadeVendido()), Element.ALIGN_RIGHT));			
			d.add(tabela);
			
			paragrafo = new Paragraph("\nVendas nesse turno\n\n");
			paragrafo.setAlignment(Element.ALIGN_CENTER);
			
			d.add(paragrafo);
			
			tabela = new PdfPTable(2);
			
			Font f2 = new Font();
			f2.setStyle(Font.BOLD);
			
			tabela.addCell(celula(frase("Nome", f2)));			
			tabela.addCell(celula(frase("Quantidade", f2)));
			
			for(Venda v : t.getVendas()) {
				tabela.addCell(new PdfPCell(new Phrase(v.getAluno().getNome())));				
				tabela.addCell(celula(frase("" +v.getQuantidade())));
			}
			
			d.add(tabela);
			
			paragrafo = new Paragraph("\n");			
			d.add(paragrafo);
			
			tabela = new PdfPTable(2);
			
			for(int i = 0; i<2; ++i) {
				tabela.addCell(celulaSemBorda(new Phrase("______________________________"), Element.ALIGN_CENTER));
			}
			
			tabela.addCell(celulaSemBorda(frase(t.getVendedor().getNome(), f2), Element.ALIGN_CENTER));			
			tabela.addCell(celulaSemBorda(frase(t.getResponsavel().getNome(),f2), Element.ALIGN_CENTER));			
			f2.setSize(10);
			
			tabela.addCell(celulaSemBorda(frase("Resp. pela venda", f),Element.ALIGN_CENTER));
			tabela.addCell(celulaSemBorda(frase("Resp. pela conferência", f), Element.ALIGN_CENTER));
			
			f.setStyle(Font.ITALIC);
			d.add(tabela);
		} catch (DocumentException | IOException e) {
			e.printStackTrace();
		} finally {
			fecharArquivo();
		}
	}
	
	public void gerarCarteira(List<Aluno> lista) {
		int posX=0, posY=0;
		try {
			PdfContentByte cb = iniciarArquivo("carteiras.pdf").getDirectContent();
			Paragraph p;
			
			for (Aluno aluno: lista) {
				
				BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.CP1252, BaseFont.NOT_EMBEDDED);
			    cb.saveState();
			    cb.beginText();
			    cb.moveText(300, 200);
			    cb.setFontAndSize(bf, 12);
			    cb.showText(aluno.getEscola().getNome()+" "+aluno.getEscola().getBairro());
			    cb.endText();
			    cb.restoreState();
			}
		} catch (DocumentException | IOException e) {
			e.printStackTrace();
		} finally {
			fecharArquivo();
		}
	}

	public void comprovanteCadastro(Aluno a) {
		FormatarCampo fc = new FormatarCampo();
		String datahora = fc.datahoraToString(hoje);
		
		try {
			iniciarArquivo("ComprovanteCadastro"+a.getId()+".pdf");
			for(int i = 0; i < 2; ++i) {
//				deve fazer duas vias: uma fica com o aluno e outra com a CTB
				d.add(logoPequeno);
				
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
				paragrafo.add("TERMO DE UTILIZAÇÃO");
				d.add(paragrafo);
	
				paragrafo = new Paragraph();
				paragrafo.setFont(f);
				paragrafo.setAlignment(Element.ALIGN_JUSTIFIED);
				paragrafo.add("- Estudantes que não terão direito ao benefício da Meia Passagem Escolar - Vale Estudante:\n"
						+ "I. Estudantes de pós-graduação, pré-vestibulares e cursos não regulamentados pelo MEC;\n"
						+ "II. Estudantes que já gozem da gratuidade do Sistema de Transporte Ferroviário;\n"
						+ "II. Estudantes menores que 06 (seis) anos;\n"
						+ "Situações que implicam na suspensão do benefício:\n"
						+ "I. Uso por terceiros, inclusive os seus acompanhantes; II. Uso por estudantes não cadastrados; III. Comercialização.\n"
						+ "Os beneficiários do sistema de Meia Passagem Escolar - Vale Estudante deverão apresentar o Cartão de identificação, fornecido no ato do cadastramento, no momento em que for utilizar o seu benefício.");
				d.add(paragrafo);
	
				paragrafo = new Paragraph();
				paragrafo.setFont(f);
				paragrafo.setAlignment(Element.ALIGN_CENTER);
				paragrafo.add(
						"- PERDA DO CARTÃO - No caso de perda ou roubo do cartão de identificação, solicitamos o Registro em Delegacia e posterior apresentação do Registro de Ocorrência");
				d.add(paragrafo);
	
				paragrafo = new Paragraph();
				paragrafo.setFont(f);
				paragrafo.setAlignment(Element.ALIGN_JUSTIFIED_ALL);
				paragrafo.add(
						"- Aceito os termos de utilização da Meia Passagem Escolar - Vale Estudante, inclusive os itens que implicam na suspensão do benefício e atesto o recebimento do Cartão de Meia Passagem Escolar.\n"
								+ "Data: " + datahora + ""
								+ "     Assinatura:_________________________________________________________\n");
				d.add(paragrafo);
	
				paragrafo = new Paragraph();
				paragrafo.setFont(f);
				paragrafo.setAlignment(Element.ALIGN_CENTER);
				
				if (i == 0)
					paragrafo.add("          ______________________________________________________________\n"
							+ "                        Responsável pelo cadastro\n"
							+ "_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ \n");
				else 
						paragrafo.add("          ______________________________________________________________\n"
								+ "                        Responsável pelo cadastro\n");
				
				d.add(paragrafo);
			}
		} catch (DocumentException | IOException e) {
			e.printStackTrace();
		} finally {
			fecharArquivo();
		}
	}
	
	private void fecharArquivo() {
		d.close();
		
		
		/* descomente esse trecho para o arquivo abrir no SERVIDOR quando for gerado*/
//		try { File arquivo = new File(nomeArquivo);
//		  
//		  Desktop.getDesktop().open(arquivo); } catch (IOException e) {
//		  e.printStackTrace(); }
		 
		 
	};
	

	private PdfWriter iniciarArquivo(String string) throws DocumentException, MalformedURLException, IOException {
		nomeArquivo = pasta+"logado\\comprovantes\\"+string;
		
		System.out.println(nomeArquivo);
		new File(pasta+"logado\\comprovantes\\").mkdirs();
		FileOutputStream fos =  new FileOutputStream(nomeArquivo);
		PdfWriter pdfWriter = PdfWriter.getInstance(d,fos);
		d.open();

		logoPequeno = Image.getInstance(pasta+"img\\logo-ctb pequeno.png");
		
		float teste = 470;
		float documentWidth = d.getPageSize().getWidth() - d.leftMargin() - d.rightMargin() - teste;
		float documentHeight = d.getPageSize().getHeight() - d.topMargin() - d.bottomMargin() - teste;
		logoPequeno.scaleToFit(documentWidth, documentHeight);
		logoPequeno.setAlignment(Element.PARAGRAPH);
//		logoPequeno.scaleToFit(logoPequeno.getAbsoluteX(),logoPequeno.getAbsoluteY());
		
		return pdfWriter;
	}
	
	private Phrase frase(String texto) {
		return frase(texto, f);
	}
	
	private Phrase frase(String texto, Font f2) {
		Phrase frase = new Phrase();
		frase.setFont(f2);
		frase.add(texto);
		
		return frase;
	}
	
	private PdfPCell celula(Phrase texto) {		
		return celula(texto, Element.ALIGN_CENTER);
	}
	
	private PdfPCell celula(Phrase texto, int alinhamento) {
		PdfPCell celula = new PdfPCell(texto);
		celula.setHorizontalAlignment(alinhamento);
		
		return celula;
	}
	
	private PdfPCell celulaSemBorda(Phrase texto, int alinhamento) {
		PdfPCell celula = new PdfPCell(texto);
		celula.setBorder(Rectangle.NO_BORDER);
		celula.setHorizontalAlignment(alinhamento);
		
		return celula;
	}
}