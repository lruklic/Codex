package controllers;

import java.util.ArrayList;
import java.util.List;

import models.Admin;
import models.Novelty;
import models.Player;
import models.enums.NewsPriority;
import models.enums.NewsType;
import models.enums.Subject;
import models.enums.UserType;

import com.google.inject.Inject;

import play.db.jpa.Transactional;
import play.mvc.Controller;
import play.mvc.Result;
import services.model.NoveltyService;
import services.model.UserService;

@Transactional
public class TestDataController extends Controller {

	@Inject
	private static UserService userService;
	
	@Inject
	private static NoveltyService noveltyService;
	
	public static Result fill() {
		
		Admin admin = new Admin("lruklic", "81dc9bdb52d04dc20036dbd8313ed055", "Luka", "Ruklić", "ruklic.luka@gmail.com");
		
		List<Subject> permissions = new ArrayList<>();
		permissions.add(Subject.HISTORY);
		permissions.add(Subject.GEOGRAPHY);
		admin.subjectPermissions = permissions;
		
		admin.clearanceLevel = 3;
		
		userService.save(admin);
		
		
		admin = new Admin("kkolak", "ca3e3f85c746511b5f881fe5054d2fc7", "Kruno", "Kolak", "kolak.kruno@gmail.com");
		
		permissions = new ArrayList<>();
		permissions.add(Subject.HISTORY);
		permissions.add(Subject.GEOGRAPHY);
		admin.subjectPermissions = permissions;
		
		admin.clearanceLevel = 2;
		
		userService.save(admin);
		
		Player player = new Player("player", "81dc9bdb52d04dc20036dbd8313ed055", "Igrač", "Igralec", "player@gmail.com");
		
		userService.save(player);
		
		return ok();
	}
	
}
