package net.kodeku.listviewcheckbox;

public class MenuPilihan {

	String id = null;
	String name = null;
	boolean selected = false;

	public MenuPilihan(String id, String name, boolean selected) {
		super();
		this.id = id;
		this.name = name;
		this.selected = selected;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

}
