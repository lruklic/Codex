package factories.export;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import play.i18n.Messages;
import models.Question;

/**
 * Class that implements methods that receive question list and return 
 * 
 * @author Luka Ruklic
 *
 */

public class ExportFactory {

	/**
	 * Export question list as CSV file.
	 * 
	 * @param questionList
	 * @return CSV turned to bytes for display in controller
	 */
	public byte[] exportAsCSV(List<Question> questionList) {
		
		byte[] output = null;
		
		try {
			StringBuilder sb = new StringBuilder();
			CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator("\n");
			CSVPrinter printer = new CSVPrinter(sb, csvFileFormat);
			
			for (Question q : questionList) {
				List<String> question = questionsAsList(q);
				// somehow add fields outside standard question
				printer.printRecord(question);
			}
		
			output = sb.toString().getBytes();
			
			printer.close();
			
		} catch (IOException ex) {

		}

		return output;
		
	}

	/**
	 * Export question list as XLS Excel file.
	 * @param questionList
	 * @return XLS file turned to byte form for display in controller
	 */
	public byte[] exportAsXLS(List<Question> questionList) {
		
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet(Messages.get("questions"));
		
		int i = 0;
		for (Question q : questionList) {
			Row row = sheet.createRow(i);
			// add width for question tags
			List<String> question = questionsAsList(q);
			
			int j = 0;
			for (String field : question) {
				Cell cell = row.createCell(j);
				cell.setCellValue(field);
				j++;
			}
			i++;
		}
		
		try {
			wb.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return wb.getBytes();
		
	}
	
	private List<String> questionsAsList(Question q) {
		List<String> question = new ArrayList<>();
		
		question.add(String.valueOf(q.id));
		question.add(q.questionText);
		question.add(q.questionType.toString());
		question.add(q.grade.toString());
		question.add(q.subject.toString());
		question.add(q.chapters);
		question.add(q.subjectContent);
		question.add(String.valueOf(q.difficulty));
		question.add(q.admin.username);
		
		return question;
	}
}
