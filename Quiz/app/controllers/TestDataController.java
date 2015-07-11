package controllers;

import models.Admin;
import models.Novelty;
import models.enums.NewsPriority;
import models.enums.NewsType;
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
		
		// Test admin data
		
		Admin admin = new Admin();
		
		admin = new Admin();
		admin.firstName = "Kruno";
		admin.lastName = "Kolak";
		admin.username = "kkolak";
		admin.passwordHash = "ca3e3f85c746511b5f881fe5054d2fc7";
		admin.email = "kolak.kruno@gmail.com";
		admin.userType = UserType.ADMIN;
		admin.questionsAdded = 0;
		admin.clearanceLevel = 2;
		
		userService.save(admin);
		
		admin = new Admin();
		admin.firstName = "Maja";
		admin.lastName = "Cundić";
		admin.username = "mcundic";
		admin.passwordHash = "81dc9bdb52d04dc20036dbd8313ed055";
		admin.email = "email@email.hr";
		admin.userType = UserType.ADMIN;
		admin.questionsAdded = 0;
		admin.clearanceLevel = 2;
		
		userService.save(admin);
		
		admin = new Admin();
		admin.firstName = "Lovro";
		admin.lastName = "Bravić";
		admin.username = "lbravic";
		admin.passwordHash = "81dc9bdb52d04dc20036dbd8313ed055";
		admin.email = "email@email.hr";
		admin.userType = UserType.ADMIN;
		admin.questionsAdded = 0;
		admin.clearanceLevel = 2;
		
		userService.save(admin);
		
		admin = new Admin();
		admin.firstName = "Marin";
		admin.lastName = "Bužančić";
		admin.username = "mbuzancic";
		admin.passwordHash = "81dc9bdb52d04dc20036dbd8313ed055";
		admin.email = "email@email.hr";
		admin.userType = UserType.ADMIN;
		admin.questionsAdded = 0;
		admin.clearanceLevel = 2;
		
		userService.save(admin);
		
		admin = new Admin();
		admin.firstName = "Ivan";
		admin.lastName = "Weber";
		admin.username = "iweber";
		admin.passwordHash = "81dc9bdb52d04dc20036dbd8313ed055";
		admin.email = "email@email.hr";
		admin.userType = UserType.ADMIN;
		admin.questionsAdded = 0;
		admin.clearanceLevel = 2;
		
		userService.save(admin);
		
		Admin superAdmin = new Admin();
		
		superAdmin.firstName = "Luka";
		superAdmin.lastName = "Ruklić";
		superAdmin.username = "lruklic";
		superAdmin.passwordHash = "81dc9bdb52d04dc20036dbd8313ed055";
		superAdmin.email = "ruklic.luka@gmail.com";
		superAdmin.userType = UserType.ADMIN;
		superAdmin.questionsAdded = 0;
		superAdmin.clearanceLevel = 3;
		
		userService.save(superAdmin);
		
		Novelty novelty = new Novelty();
		novelty.noveltyTitle = "Dobro došli u Codex 0.1 !";
		novelty.noveltyText = "Prva verzija Codexa je <b><i>ONLINE</b></i>. Zasad je omogućeno dodavanje, pregled, "
				+ "uređivanje i brisanje pitanja. Implementirane su četiri različite vrste pitanja. <br><br> Slobodno "
				+ "isprobavajte i svakako prijavite bugove ako na njih naiđete.";
		novelty.newsPriority = NewsPriority.HIGH_PRIORITY;
		novelty.newsType = NewsType.FOR_EVERYONE;
		novelty.admin = (Admin) userService.findByUsernameOrEmail("lruklic");
		
		noveltyService.save(novelty);
		
		return ok();
	}
	
}
