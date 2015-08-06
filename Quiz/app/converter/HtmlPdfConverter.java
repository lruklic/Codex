package converter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfWriter;

public class HtmlPdfConverter {

	public static ByteArrayOutputStream convertHtmlToPdf(List<String> scripts) {
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		Document document = new Document();
		
		for (String script : scripts) {
			
		}
		
		return null;
		
	}
	
	public static void convertHtmlToPdf(ByteArrayOutputStream baos, Document document, String scriptContent) {
		
		PdfFont font = new PdfFont();
		
		org.jsoup.nodes.Document jsoupDoc = Jsoup.parse(scriptContent, "UTF-8");
		
		Elements body = jsoupDoc.body().children();
		
		try {
			PdfWriter writer = PdfWriter.getInstance(document, baos);
			document.open();
			
			for (Element element : body) {
				applyElementToPdf(document, font, element);
			}

		} catch (DocumentException e) {
			e.printStackTrace();
		}
		
		document.close();
		
	}
	
	public static void applyElementToPdf(Document doc, PdfFont font, Element element) {
		
		font.resetFont();
		
		com.lowagie.text.Phrase pdfElement = null;
		
		String topLevelTag = element.tag().toString();
		
		if (topLevelTag.equals("p") || topLevelTag.startsWith("h")) {
			
			Paragraph p = new Paragraph();
			
			if (topLevelTag.startsWith("h")) {
				font.setHeadingFont(topLevelTag);
			}
			
			p.setFont(font.getFont());
			
			String text = element.text();
			if (element.toString().contains("&nbsp;")) {
				p.add(new Chunk(" "));
			} else {
				applyText(p, element, font.getFont());
			}
					
			pdfElement = p;
		}
		
		try {
			doc.add(pdfElement);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void applyText(Phrase phrase, Node node, Font font) {
		
		Font currentFont = new Font(font);
		
		if (node instanceof TextNode) {
			phrase.add(new Phrase(node.toString(), currentFont));
			return;
		}
		
		List<Node> nodes = node.childNodes();
		
		for (Node n : nodes) {
			Tag tag = null;
			Attributes attr = null;
			if (n instanceof Element) {
				tag = ((Element) n).tag();
				attr = ((Element) n).attributes();
			}
			applyTagToFont(currentFont, tag, attr);
			
			applyText(phrase, n, currentFont);
		}
		
	}
	
	private static void applyTagToFont(Font font, Tag tag, Attributes attr) {
		int style = font.getStyle();
		
		if (tag != null) {
			if (tag.toString().equals("strong")) {
				style = style | Font.BOLD;
			} else if (tag.toString().equals("em")) {
				style = style | Font.ITALIC;
			}
		}
		
		if (attr != null && attr.get("style") != null) {
			String styleAttr = attr.get("style");
			style = style | applyStyleAttr(styleAttr);
		}

		font.setStyle(style);
	}
	
	private static int applyStyleAttr(String styleAttr) {
		String[] styleAttrArray = styleAttr.split(":");
		if (styleAttrArray[0].equals("text-decoration")) {
			// what if two different styles are present? rework ; problem maybe?
			if (styleAttrArray[1].replaceAll(";", "").trim().equals("underline")) {
				return Font.UNDERLINE;
			}
		}
		
		return 0;
		
	}
}
