package controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import models.Admin;
import models.Question;
import models.User;

import com.google.inject.Inject;

import play.Logger;
import play.data.Form;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import services.model.QuestionService;
import services.model.UserService;
import views.html.admin_question;
import views.html.admin_questionlist;
import enums.ExportType;
import factories.export.ExportFactory;
import forms.QuestionForm;

/**
 * Controller that handles operations over questions.
 * 
 * @author Luka Ruklic
 *
 */

@Transactional
public class QuestionController extends Controller {

	@Inject
	public static UserService userService;
	
	@Inject
	public static QuestionService questionService;
	
	private static ExportFactory exportFactory = new ExportFactory();
	
	/**
	 * Mapped to POST method under adminQuestion
	 * @return
	 */
	
	public static Result submit() {
		Form<QuestionForm> questionForm = Form.form(QuestionForm.class).bindFromRequest();
		
		User user = getCurrentUser();
		
		if(questionForm.hasErrors()) {
			return badRequest(admin_question.render(questionForm));
		}
		
		Question question = null;
		if (user instanceof Admin) {
			 question = questionForm.get().createQuestion((Admin) user);	// if there are any errors, .get() will throw IllegalStateException: no value
			 question.lastEdited = System.currentTimeMillis();
		}
//		if (question.id != null) {
//			questionService.delete(questionService.findById(question.id));		// is this necessary?
//		}
		questionService.save(question);

		return redirect(routes.AdminController.adminList());
	}

	
	public static Result edit(Long id) {
		
		// TODO deadbolt or some other handler to disable attempts for non-admin users to change question values
		
		User user = getCurrentUser();
		
		Question question = questionService.findById(id);
		
		QuestionForm qf = new QuestionForm();
		qf.fillForm(question);
		
		qf.id = String.valueOf(question.id);
		
		Form<QuestionForm> form = Form.form(QuestionForm.class).fill(qf);
		
		return ok(admin_question.render(form));
		
	}
	
	public static Result delete(Long id) {
		
		// TODO deadbolt or some other handler to disable attempts for non-admin users to change question values
		
		User user = getCurrentUser();
		
		Question question = questionService.findById(id);
		
		if (question == null) {
			return ok(admin_questionlist.render(questionService.findAll()));
		}
		
		questionService.delete(question);
		
		return ok(admin_questionlist.render(questionService.findAll()));
		
	}
	
	public static Result export(String exportType) throws IOException {
		
		byte[] output = null;
		
//		PrintWriter writer = new PrintWriter("the-file-name.txt", "UTF-8");
//		writer.println("The first line");
//		writer.println("The second line");
//		writer.close();
//		
		List<Question> questionList = questionService.findQuestionsByAdmin(getCurrentUser().username);
		
		// return error if user has no questions
		
		if (exportType.equals("csv")) {
			output = exportFactory.exportAsCSV(questionList);
		} else if (exportType.equals("xls")) {
			output = exportFactory.exportAsXLS(questionList);
		} else {
			return TODO;
		}
		
//		String str = "The second line";
////		
//		byte dataToWrite[] = str.getBytes();
////		FileOutputStream out = new FileOutputStream("the-file-name");
////		out.write(dataToWrite);
////		out.close();
//		
//		InputStream is = null;
//		byte[] arr = null;
//		
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("Sample shit");
		Row row = sheet.createRow(0);
		//Create a new cell in current row
		Cell cell = row.createCell(0);
		//Set value to new value
		cell.setCellValue("Blahblah");
//		
//		arr = wb.getBytes();
		
		response().setContentType("application/x-download");
		response().setHeader("Content-disposition", "attachment; filename=questionList."+exportType);
		return ok(output);
				
	}
	
	/** 
	 * Method that gets current user for session.
	 * @return current user
	 */
	private static User getCurrentUser() {
		String credential = session().get("credential");
		User user = userService.findByUsernameOrEmail(credential);
		return user;
	}
	
//	public static Result getChapters(String grade, String subject) {
//		// JsonNode json = Json.toJson(Chapter.getByGradeAndSubject(Grade.valueOf(grade), Subject.valueOf(subject)));
//		return ok();
//	}
	
}
