
package library.model;

import java.util.Date;

public class SendRecord {
    
    private Long id;
    private Date date;
    private DocVersion doc;
    private Department dep;

    public SendRecord(){}
    public SendRecord(Date date, DocVersion doc, 
		       Department dep){
	this.date = date;
	this.doc  = doc;
	this.dep  = dep;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Date getDate(){ return date; }
    public void setDate(Date date) { this.date = date; }

    public DocVersion getDoc() { return doc; }
    public void setDoc(DocVersion doc) { this.doc = doc; }

    public Department getDep() { return dep; }
    public void setDep(Department dep) { this.dep = dep; }
}