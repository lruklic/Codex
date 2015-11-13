package controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import models.Admin;
import models.Chapter;
import models.Grade;
import models.Player;
import models.Subject;
import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import security.PasswordHash;
import services.model.ChapterService;
import services.model.GradeService;
import services.model.NoveltyService;
import services.model.SubjectService;
import services.model.UserService;

import com.google.inject.Inject;

@Transactional
public class TestDataController extends Controller {

	@Inject
	private static UserService userService;
	
	@Inject
	private static NoveltyService noveltyService;
	
	@Inject
	private static SubjectService subjectService;
	
	@Inject
	private static GradeService gradeService;
	
	@Inject
	private static ChapterService chapterService;
	
	public static Result fill() {
		
		fillSubjects();
		fillGrades();
		fillChaptersGeography();
		
		// Admin fill
		Admin admin = new Admin("lruklic", PasswordHash.createHash("1234"), "Luka", "Ruklić", "ruklic.luka@gmail.com", new Date(System.currentTimeMillis()));
		
		List<Subject> permissions = new ArrayList<>();
		permissions.add(subjectService.getSubjectByName("Povijest"));
		permissions.add(subjectService.getSubjectByName("Geografija"));
		admin.subjectPermissions = permissions;
		
		admin.clearanceLevel = 3;
		
		userService.save(admin);
		
		
		admin = new Admin("kkolak", PasswordHash.createHash("1234marija"), "Kruno", "Kolak", "kolak.kruno@gmail.com", new Date(System.currentTimeMillis()));
		
		permissions = new ArrayList<>();
		// permissions.add(Subject.HISTORY);
		admin.subjectPermissions = permissions;
		
		admin.clearanceLevel = 2;
		
		userService.save(admin);
		
		Player player = new Player("player", PasswordHash.createHash("1234"), "Igrač", "Igralec", "player@gmail.com", new Date(System.currentTimeMillis()));
		
		userService.save(player);
		
		return ok();
	}
	
	public static Result fillSubjects() {
		
		String[] subjectList = {"Hrvatski jezik - književnost", "Hrvatski jezik - gramatika", "Matematika", "Engleski jezik",
								"Povijest", "Geografija", "Fizika", "Kemija", "Biologija", "Informatika", "Logika", "Sociologija",
								"Filozofija", "Psihologija", "Politika i gospodarstvo", "Glazbena kultura", "Likovna kultura" };
		
		Subject subject;
		for (String s : subjectList) {
			subject = new Subject(s);
			subjectService.save(subject);
		}
		
		return ok();
		
	}
	
	public static Result fillGrades() {
		String[] gradesList = {"1. razred", "2. razred", "3. razred", "4. razred"};
		
		Grade grade;
		for (String g : gradesList) {
			grade = new Grade(g);
			gradeService.save(grade);
		}
		return ok();
	}
	
	public static Result fillChaptersGeography() {
		
		String[] chapterList = {"Uvod u geografiju", "Zemlja u Sunčevu sustavu", "Orijentacija i određivanje položaja", "Predočavanje zemljine površine",
				"Geološke značajke i reljef Zemlje", "Klima na Zemlji", "Voda, tlo i život na Zemlji"};
		
		Chapter chapter;
		for (String c : chapterList) {
			chapter = new Chapter(c, gradeService.findById((long) 1), subjectService.getSubjectByName("Geografija"));
			chapterService.save(chapter);
		}
		
		return ok();
		
	}
	
}
