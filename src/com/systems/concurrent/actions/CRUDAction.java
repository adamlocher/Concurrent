package com.systems.concurrent.actions;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ModelDriven;
/*import com.systems.concurrent.ejb.CrudManagerRemote;*/
import com.systems.concurrent.ejb.dto.AbstractData;

/*import com.systems.concurrent.utils.EJBApplicationClient;*/

public abstract class CRUDAction<TYPE extends AbstractData> extends AbstractAction {

	private static final long serialVersionUID = 8158225378359469576L;
	protected Long userId;
	protected TYPE item;
	protected String actionName;
	protected boolean editMode;

	interface Actions {
		public String MODIFY = "Modify";
		public String CREATE = "Create";
		public String REMOVE = "Remove";
	}

	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public String getActionName() {
		return actionName;
	}

	public void setId(Long userId) {
		this.userId = userId;
	}

	public Long getId() {
		return userId;
	}

	public String Modify() {
		editMode = true;
		return Action.INPUT;
	}

	public TYPE getItem() {
		if (getAttribute("item") != null)// getSession().get("item")!=null)
			return (TYPE) getAttribute("item");// getSession().get("item");
		return item;
	}

	public void setItem(TYPE item) {
		this.item = item;
	}

	public abstract String Create();

	public abstract String List();

	public abstract String Remove();

	public String Load() {
		return Action.NONE;
	}

	public String Save() {

		editMode = false;

		return Action.SUCCESS;
	}

}
