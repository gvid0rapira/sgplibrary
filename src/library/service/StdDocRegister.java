package library.service;

import java.util.ArrayList;
import java.util.List;

import library.dao.IStandartCardDAO;
import library.model.StandartCard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true)
@Service
public class StdDocRegister {
	
	@Autowired
	private IStandartCardDAO cardDao;
	@Autowired
	private StdDocService docService;
	

	public List<StdDocRegisterRecord> getRows() {
		List<StandartCard> cards = cardDao.getAll();
		
		return recordsFromCards(cards);
	}
	
	public List<StdDocRegisterRecord> recordsFromCards(List<StandartCard> cards) {
		List<StdDocRegisterRecord> regRows = new ArrayList<StdDocRegisterRecord>();
		for( StandartCard card: cards ) {
			regRows.add(new StdDocRegisterRecord(docService, card));
		}
		
		return regRows;
	}
	
	public List<StandartCard> cardsFromRecords(List<StdDocRegisterRecord> recs) {
		List<StandartCard> cards = 
			new ArrayList<StandartCard>();
		for( StdDocRegisterRecord rec: recs ) {
			cards.add(rec.getCard());
		}
		
		return cards;
	}
	
	/**
	 * 
	 * @param code код документа
	 * @param name название документа
	 * 
	 * @pre code != null
	 * @pre name != null
	 * 
	 * @return
	 * 
	 */
	public List<StdDocRegisterRecord> filter(String name, String code) {
		List<StandartCard> cards = null;
		if ( !code.equals("") ) {
			// name && code
			if (!name.equals("")) {
				cards = cardDao.findByNameAndCode(name, code);
			} else /* code */ {
				cards = cardDao.findByCode(code);
			}
		} else {
			// name
			if (!name.equals("")) {
				cards = cardDao.findByName(name);
			} else /* all */ {
				cards = cardDao.getAll();
			}
		}
		
		List<StdDocRegisterRecord> regRows = new ArrayList<StdDocRegisterRecord>();
		for( StandartCard card: cards ) {
			regRows.add(new StdDocRegisterRecord(docService, card));
		}
		
		return regRows;
	}
	
}
