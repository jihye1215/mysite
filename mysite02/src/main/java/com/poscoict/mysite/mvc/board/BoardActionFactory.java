package com.poscoict.mysite.mvc.board;

import com.poscoict.mysite.web.mvc.Action;
import com.poscoict.mysite.web.mvc.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if("writeform".equals(actionName)) {
			
		} else {
			action = new ListAction();
		}
		
		return action;
	}

}
