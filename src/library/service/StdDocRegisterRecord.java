package library.service;

import java.util.Date;

import library.model.DocVersion;
import library.model.StandartCard;

/**
 *  Строка журнала документов.
 *  
 * @author andrey
 *
 */
public class StdDocRegisterRecord {
	
	private StandartCard card;
	
	private StdDocService docSrv;
	
	public StdDocRegisterRecord(StdDocService ds) {
	    super();
	    docSrv = ds;
    }

	public StdDocRegisterRecord(StdDocService ds, StandartCard card) {
		this(ds);
		this.card = card;
	}
	
	public StandartCard getCard() {
		return card;
	}

	public void setDocSrv(StdDocService docSrv) {
    	this.docSrv = docSrv;
    }

	public Long getCardId() {
		return card.getId();
	}
	public Long getRegNum() {
		return card.getRegNum();
	}
	public String getDocCode() {
		return card.getDocCode();
	}
	public String getDocName() {
		return card.getName();
	}
	/**
	 * Возвращает дату введения для последней версии документа.
	 * 
	 * @return
	 */
	public Date getLastApproDate() {
		DocVersion lastVersion = docSrv.getLastDocVersion(card);
		return lastVersion != null ?  lastVersion.getApproDate() :
			null;
	}
}
