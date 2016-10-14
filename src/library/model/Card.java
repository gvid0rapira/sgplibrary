package library.model;

public abstract class Card {

    private Long id;
    private String name;
    private Long regNum;

    public Card(){}
    public Card(String name, Long regNum){
    	this.name = name;
    	this.regNum = regNum;
    }
	
    public Long getId() {
    	return id;
    }

    public void setId(Long id) {
    	this.id = id;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }
    
    public Long getRegNum(){
	return regNum;
    }

    public void setRegNum(Long num){
	regNum = num;
    }
}
