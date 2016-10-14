package library.model;

public class StandartCard extends Card {
	
    private String docCode;
    public StandartCard(){}
    public StandartCard(String name, Long regNum,  String code)
    {
	super(name, regNum);
	this.docCode = code;
    }
	
    public String getDocCode() {
	return docCode;
    }

    public void setDocCode(String code) {
	this.docCode = code;
    }
}
