import org.h2.engine.Session;

import cache.models.ModelCache;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

import controllers.AdminController;
import controllers.LoginController;
import controllers.NewsController;
import controllers.QuestionController;
import controllers.QuizController;
import controllers.StartController;
import controllers.TestDataController;
import play.Application;
import play.GlobalSettings;
import play.Logger;
import play.mvc.Result;
import security.SimpleDeadboltHandler;
import services.model.ChapterService;
import services.model.FacebookAuthService;
import services.model.GradeService;
import services.model.NoveltyService;
import services.model.QuestionService;
import services.model.SubjectService;
import services.model.UserService;
import services.model.impl.ChapterServiceImpl;
import services.model.impl.FacebookAuthServiceImpl;
import services.model.impl.GradeServiceImpl;
import services.model.impl.NoveltyServiceImpl;
import services.model.impl.QuestionServiceImpl;
import services.model.impl.SubjectServiceImpl;
import services.model.impl.UserServiceImpl;


/**
 * <p>Global class that extends GlobalSettings that has onStart method which triggers when application is started.</p>
 * 
 * @author Luka Ruklic
 *
 */

public class Global extends GlobalSettings {

	private Injector injector;
	
	@Override
	public void onStart(Application application) {
		
		Logger.info("App has started!");
		
		injector = Guice.createInjector(new AbstractModule() {

			@Override
			protected void configure() {
				
				requestStaticInjection(TestDataController.class);
				requestStaticInjection(LoginController.class);
				requestStaticInjection(QuestionController.class);
				requestStaticInjection(StartController.class);
				requestStaticInjection(AdminController.class);
				requestStaticInjection(NewsController.class);
				requestStaticInjection(QuizController.class);
				requestStaticInjection(SimpleDeadboltHandler.class);
				requestStaticInjection(Session.class);
				requestStaticInjection(ModelCache.class);
				
				bind(UserService.class).to(UserServiceImpl.class);
				bind(QuestionService.class).to(QuestionServiceImpl.class);
				bind(NoveltyService.class).to(NoveltyServiceImpl.class);
				bind(SubjectService.class).to(SubjectServiceImpl.class);
				bind(ChapterService.class).to(ChapterServiceImpl.class);
				bind(GradeService.class).to(GradeServiceImpl.class);
				bind(FacebookAuthService.class).to(FacebookAuthServiceImpl.class);
			}
			
		});
	}

}
