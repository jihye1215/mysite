package com.poscoict.mysite.mvc.main;

import com.poscoict.mysite.web.mvc.Action;
import com.poscoict.mysite.web.mvc.ActionFactory;

public class MainActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		return new MainAction();
	}

}
