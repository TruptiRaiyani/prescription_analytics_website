package NodeAPI;

import java.io.Serializable;

public class CptRecord implements Serializable{
	
	private static final long serialVersionUID = 1L;

	
	String descriptionicd;
	int cptcode;
	String cptdescription;
	String charge;
	
	public CptRecord(){
		
	}

	public String getIcdDescription() {
		return descriptionicd;
	}

	public void setIcdDescription(String descriptionicd) {
		this.descriptionicd = descriptionicd;
	}

	public int getCptCode() {
		return cptcode;
	}

	public void setCptCode(int cptcode) {
		this.cptcode = cptcode;
	}

	public String getCptDescription() {
		return cptdescription;
	}

	public void setCptDescription(String cptdescription) {
		this.cptdescription = cptdescription;
	}

	public String getCharge() {
		return charge;
	}

	public void setCharge(String charge) {
		this.charge = charge;
	}
	

}
