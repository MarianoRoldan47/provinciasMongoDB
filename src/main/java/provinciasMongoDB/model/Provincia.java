package provinciasMongoDB.model;

public class Provincia {
	private String parent_code;
	private String code;
	private String label;
	
	public Provincia() {
		super();
	}

	public Provincia(String parent_code, String code, String label) {
		super();
		this.parent_code = parent_code;
		this.code = code;
		this.label = label;
	}

	public String getParent_code() {
		return parent_code;
	}

	public void setParent_code(String parent_code) {
		this.parent_code = parent_code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public String toString() {
		return label;
	}
	
	
	

}
