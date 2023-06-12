package tn.iit.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

import org.apache.pdfbox.io.IOUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

import tn.iit.entities.Enseignant;

public class Pdf {
	public static ByteArrayOutputStream generatePDF(Enseignant ens) {
		int weeksLeft = 52 - Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		try {
			PDDocument document = new PDDocument();
			PDPage page = new PDPage(PDRectangle.A4);
			document.addPage(page);

			PDPageContentStream contentStream = new PDPageContentStream(document, page);

			contentStream.setFont(PDType1Font.TIMES_BOLD, 25);
			contentStream.beginText();
			contentStream.newLineAtOffset(200, 800);
			contentStream.showText("Autorisation de travail");
			contentStream.endText();

			contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
			contentStream.setLeading(15f);
			contentStream.beginText();
			contentStream.newLineAtOffset(50, 650);

			String introText = ens.getNom() + " " + ens.getPrenom() + " de la carte identité :" + ens.getCin()
					+ " est autorisé(e) " + weeksLeft * 4 + " heures de travail pour les activités d’expertise ";
			float maxWidth = 500;

			String[] words = introText.split("\\s+");
			StringBuilder line = new StringBuilder();
			for (String word : words) {
				float width = PDType1Font.TIMES_ROMAN.getStringWidth(line.toString() + " " + word) / 1000 * 12;
				if (width > maxWidth) {
					contentStream.showText(line.toString().trim());
					contentStream.newLine();
					line = new StringBuilder(word + " ");
				} else {
					line.append(word).append(" ");
				}
			}
			contentStream.showText(line.toString().trim());
			contentStream.setLeading(25f);
			contentStream.newLine();
			contentStream.setFont(PDType1Font.TIMES_BOLD, 15);
			contentStream.showText("Signature: ");
			contentStream.close();

			document.save(outputStream);
			document.close();
			System.out.println("PDF generated successfully.");

		} catch (IOException e) {
			e.printStackTrace();
		}
		return outputStream;

	}
}