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
		
		userService.save(admin);
		
		Player player = new Player("player", "81dc9bdb52d04dc20036dbd8313ed055", "Luka", "Ruklić", "player@gmail.com");
		
		userService.save(player);
		
//		Admin admin = new Admin("lruklic", "81dc9bdb52d04dc20036dbd8313ed055", "Luka", "Ruklić", "ruklic.luka@gmail.com");
//		
//		Player player = new Player("player", "81dc9bdb52d04dc20036dbd8313ed055", "Luka", "Ruklić", "player@gmail.com");
//		
//		userService.save(admin);
//		userService.save(player);
		
//		Novelty novelty = new Novelty();
//		novelty.noveltyTitle = "Dobro došli u Codex 0.1 !";
//		novelty.noveltyText = "Prva verzija Codexa je <b><i>ONLINE</b></i>. Zasad je omogućeno dodavanje, pregled, "
//				+ "uređivanje i brisanje pitanja. Implementirane su četiri različite vrste pitanja. <br><br> Slobodno "
//				+ "isprobavajte i svakako prijavite bugove ako na njih naiđete.";
//		novelty.newsPriority = NewsPriority.HIGH_PRIORITY;
//		novelty.newsType = NewsType.FOR_EVERYONE;
//		novelty.admin = (Admin) userService.findByUsernameOrEmail("lruklic");
//		
//		noveltyService.save(novelty);
		
		return ok();
	}
	
}
