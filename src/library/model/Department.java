
package library.model;


public class Department {

    private Long id;
    private String name;
    private String number;
    
    public Department(){}
    public Department(String name, String number){
	this.name = name;
	this.number = number;
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName(){ return name; }
    public void setName(String name){ this.name = name; }

    public String getNumber(){ return number; }
    public void setNumber(String number){ this.number = number; }

}