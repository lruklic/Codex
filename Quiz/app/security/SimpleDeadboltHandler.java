package security;

import models.enums.UserType;
import play.libs.F;
import play.libs.F.Promise;
import play.mvc.Http.Context;
import play.mvc.SimpleResult;
import be.objectify.deadbolt.core.models.Subject;
import be.objectify.deadbolt.java.AbstractDeadboltHandler;

/**
 * Deadbolt class that handles which type of user can access and activate
 * certain controller method.
 * 
 * @author Luka Ruklic
 *
 */

public class SimpleDeadboltHandler extends AbstractDeadboltHandler {

	@Override
	public Promise<SimpleResult> beforeAuthCheck(Context context) {
		return null;
	}
	
	@Override
	public Subject getSubject(Context context) {
		String currentUser = context.session().get("type");
		if (currentUser.equals("ADMIN")) {
			return UserType.ADMIN;
		}
		
		return UserType.PLAYER;
	}
	
	@Override
	public Promise<SimpleResult> onAuthFailure(Context context, String content) {
		
        return F.Promise.promise(new F.Function0<SimpleResult>()
        {
            @Override
            public SimpleResult apply() throws Throwable {
            	// TODO what happens when authentication failed
                return ok();
            }
        });
	}

}
