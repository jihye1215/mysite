package com.poscoict.mysite.mvc.board;

import com.poscoict.mysite.mvc.user.JoinAction;
import com.poscoict.mysite.web.mvc.Action;
import com.poscoict.mysite.web.mvc.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if("writeform".equals(actionName)) {
			action = new WriteFormAction();
		} else if("write".equals(actionName)) {
			action = new WriteAction();
		} else if("delete".equals(actionName)) {
			action = new DeleteAction();
		} else if("select".equals(actionName)) {
			action = new SelectAction();
		} else {
			action = new ListAction();
		}
		
		return action;
	}

}
