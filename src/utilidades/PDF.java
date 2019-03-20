package utilidades;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import org.joda.time.DateTime;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import entity.Aluno;
import entity.Turno;

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
	Image logoPequeno;
	
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
<<<<<<< HEAD
			
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
			p.add("Resp. pela conferência");
			celula = new PdfPCell(p);
			celula.setBorder(PdfPCell.NO_BORDER);
			celula.setHorizontalAlignment(Element.ALIGN_CENTER);
			tabela.addCell(celula);
			
			f.setStyle(Font.ITALIC);
			d.add(tabela);
		} catch (DocumentException | IOException e) {
=======
		} catch (FileNotFoundException | DocumentException e) {
>>>>>>> parent of b6c2ce3... 12/03/2019 - Gera relatório de turno
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
		 try { 
			 File arquivo = new File(nomeArquivo);
		 
			 Desktop.getDesktop().open(arquivo); 
		 } catch (IOException e) {
			 e.printStackTrace(); 
		 }
		 
	};
	

	private void iniciarArquivo(String string) throws DocumentException, MalformedURLException, IOException {
		nomeArquivo = pasta+"\\WebContent\\logado\\comprovantescadastro\\"+string;
		
		PdfWriter.getInstance(d, new FileOutputStream(nomeArquivo));
		d.open();
		
//		logoPequeno = Image.getInstance(String.format(pasta+"\\WebContent\\img\\cabecalho.png"));
		logoPequeno = Image.getInstance(pasta+"\\WebContent\\img\\logo-ctb backup.png");
		
		float teste = 470;
		float documentWidth = d.getPageSize().getWidth() - d.leftMargin() - d.rightMargin() - teste;
		float documentHeight = d.getPageSize().getHeight() - d.topMargin() - d.bottomMargin() - teste;
		logoPequeno.scaleToFit(documentWidth, documentHeight);
		logoPequeno.setAlignment(Element.PARAGRAPH);
//		logoPequeno.scaleToFit(logoPequeno.getAbsoluteX(),logoPequeno.getAbsoluteY());
		
		
	}
}