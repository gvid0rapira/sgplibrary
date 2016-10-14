package library.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class DocVersion implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    
    @DateTimeFormat(pattern="dd.MM.yyyy")
    private Date approDate;
    private StandartCard card;
    private DocContent content;

    public DocVersion(){}
	
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Date getApproDate(){	return approDate; }
    public void setApproDate(Date date){ approDate = date; }

    public StandartCard getCard(){ return card; }
    public void setCard(StandartCard card) { this.card = card; }

    public DocContent getContent() { return content; }
    public void setContent(DocContent content) {
    	content.setDoc(this);
    	this.content = content; 
    }
}
