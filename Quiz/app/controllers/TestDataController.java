package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Admin;
import models.Chapter;
import models.Grade;
import models.Player;
import models.Subject;
import cache.models.ModelCache;

import com.google.inject.Inject;

import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import services.model.ChapterService;
import services.model.GradeService;
import services.model.NoveltyService;
import services.model.SubjectService;
import services.model.UserService;

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
		
		// Subject fill
		Subject subject = new Subject("Geografija");
		subjectService.save(subject);
		
		subject = new Subject("Povijest");
		subjectService.save(subject);
		
		subject = new Subject("Matematika");
		subjectService.save(subject);
		
		// Grade fill
		Grade grade = new Grade("Prvi razred");
		gradeService.save(grade);
		
		// Chapter fill
		Chapter chapter = new Chapter("Uvod u povijest", gradeService.findById((long) 1), subjectService.findById((long) 1));
		chapterService.save(chapter);
		
		// Admin fill
		Admin admin = new Admin("lruklic", "81dc9bdb52d04dc20036dbd8313ed055", "Luka", "Ruklić", "ruklic.luka@gmail.com");
		
		List<Subject> permissions = new ArrayList<>();
		permissions.add(subjectService.getSubjectByName("Povijest"));
		permissions.add(subjectService.getSubjectByName("Geografija"));
		admin.subjectPermissions = permissions;
		
		admin.clearanceLevel = 3;
		
		userService.save(admin);
		
		
		admin = new Admin("kkolak", "ca3e3f85c746511b5f881fe5054d2fc7", "Kruno", "Kolak", "kolak.kruno@gmail.com");
		
		permissions = new ArrayList<>();
		// permissions.add(Subject.HISTORY);
		admin.subjectPermissions = permissions;
		
		admin.clearanceLevel = 2;
		
		userService.save(admin);
		
		Player player = new Player("player", "81dc9bdb52d04dc20036dbd8313ed055", "Igrač", "Igralec", "player@gmail.com");
		
		userService.save(player);
		
		return ok();
	}
	
}
