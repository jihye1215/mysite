package com.poscoict.mysite.mvc.guestbook;

import com.poscoict.mysite.web.mvc.Action;
import com.poscoict.mysite.web.mvc.ActionFactory;
import com.poscoict.web.util.MvcUtil;

public class GuestbookActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		if("deleteform".equals(actionName)) {
			action = new DeleteFormAction();
		} else if ("delete".equals(actionName)) {
			action = new DeleteAction();
		} else if ("add".equals(actionName)) {
			action = new AddAction();
		} else {
			action = new IndexAction();
		}
		return action;
	}

}
