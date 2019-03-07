package utilidades;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.joda.time.DateTime;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import entity.Aluno;

public class PDF {
	
	public void gerarComprovanteCadastro(Aluno a) {
		String nomeArquivo = "ComprovanteCadastro"+a.getId()+".pdf";
		
		Document d = new Document();
		
		try {
			Font f = new Font();

			PdfWriter.getInstance(d, new FileOutputStream(nomeArquivo));
			d.open();

			f.setSize(10);

			Paragraph paragrafo = new Paragraph("MEIA-PASSAGEM ESCOLAR - VALE ESTUDANTE\nCOMPROVANTE DE CADASTRO - "
					+ new DateTime().getYear() + "\n\n");
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
			DateTime hoje = new DateTime();
			paragrafo.add(
					"- Aceito os termos de utilização da Meia Passagem Escolar - Vale Estudante, inclusive os itens que implicam na suspensão do benefício e atesto o recebimento do Cartão de Meia Passagem Escolar.\n"
							+ "Data: " + hoje.getDayOfMonth() + "/" + hoje.getMonthOfYear() + "/" + hoje.getYear() + " "
							+ hoje.getHourOfDay() + ":" + hoje.getMinuteOfHour() + ":" + hoje.getSecondOfMinute() + ""
							+ "     Assinatura:_________________________________________________________\n");
			d.add(paragrafo);

			paragrafo = new Paragraph();
			paragrafo.setFont(f);
			paragrafo.setAlignment(Element.ALIGN_CENTER);
			paragrafo.add("          ______________________________________________________________\n"
					+ "                        Responsável pelo cadastro\n\n");
			d.add(paragrafo);
		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		} finally {
			d.close();
		}

		try {
			Desktop.getDesktop().open(new File(nomeArquivo));
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
