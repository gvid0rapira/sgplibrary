package library.model;

import java.util.Date;

/**
 * Запись об отсылке копии версии документа подразделению.
 * 
 * @author Andrey Kukushkin
 *
 */
public class DocSendRecord {
	
	private Long id;
	private Date date;
	private DocVersion docVersion;
	private Department dep;
	
	public DocSendRecord() {
	    super();
    }

	public DocSendRecord(Date date, DocVersion docVersion, Department dep) {
	    super();
	    this.date = date;
	    this.docVersion = docVersion;
	    this.dep = dep;
    }

	public Long getId() {
    	return id;
    }

	public void setId(Long id) {
    	this.id = id;
    }

	public Date getDate() {
    	return date;
    }

	public void setDate(Date date) {
    	this.date = date;
    }

	public DocVersion getDocVersion() {
    	return docVersion;
    }

	public void setDocVersion(DocVersion docVersion) {
    	this.docVersion = docVersion;
    }

	public Department getDep() {
    	return dep;
    }

	public void setDep(Department dep) {
    	this.dep = dep;
    }
}
