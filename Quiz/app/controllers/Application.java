package controllers;

import play.Routes;
import play.mvc.Controller;
import play.mvc.Result;

public class Application extends Controller {

	public static Result javascriptRoutes() {
	    response().setContentType("text/javascript");
	    return ok(
	        Routes.javascriptRouter("jsRoutes",
	            routes.javascript.QuestionController.delete(),
	            routes.javascript.QuizController.quizHome()
	            //inside somepackage
	            // controllers.somepackage.routes.javascript.Application.updateItem()
	        )
	    );
	}
	
}
