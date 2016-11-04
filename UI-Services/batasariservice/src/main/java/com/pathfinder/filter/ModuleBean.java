package com.pathfinder.filter;

public class ModuleBean {

	private boolean canView;
	private boolean canAdd;
	private boolean canUpdate;
	private boolean canDelete;

	public boolean isCanView() {
		return canView;
	}

	public void setCanView(boolean canView) {
		this.canView = canView;
	}

	public boolean isCanAdd() {
		return canAdd;
	}

	public void setCanAdd(boolean canAdd) {
		this.canAdd = canAdd;
	}

	public boolean isCanUpdate() {
		return canUpdate;
	}

	public void setCanUpdate(boolean canUpdate) {
		this.canUpdate = canUpdate;
	}

	public boolean isCanDelete() {
		return canDelete;
	}

	public void setCanDelete(boolean canDelete) {
		this.canDelete = canDelete;
	}

}
