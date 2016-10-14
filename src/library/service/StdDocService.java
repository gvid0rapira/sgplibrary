package library.service;

import java.util.Date;
import java.util.List;

import library.dao.IContentTypeDAO;
import library.dao.IDocContentDAO;
import library.dao.IDocSendRecordDAO;
import library.dao.IDocVersionDAO;
import library.dao.IStandartCardDAO;
import library.model.Card;
import library.model.ContentType;
import library.model.Department;
import library.model.DocContent;
import library.model.DocSendRecord;
import library.model.DocVersion;
import library.model.StandartCard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly=true)
public class StdDocService {
	
	@Autowired
	private IStandartCardDAO scDao;
	@Autowired
	private IDocVersionDAO dvDao;
	@Autowired
	private IDocContentDAO cntDao;
	@Autowired
	private IContentTypeDAO cntTypeDao;
	@Autowired
	private IDocSendRecordDAO dsrDao;
	
	public StandartCard findCard(Long id) {
		return scDao.find(id);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void save(StandartCard c) {
		scDao.save(c);
	}
	
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void save(DocVersion v) {
		dvDao.save(v);
	}
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void deleteCards(List<StandartCard> cards) {
		for(StandartCard card: cards) {
			scDao.delete(card);
		}
	}
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void deleteVersions(List<DocVersion> docVersions) {
		for(DocVersion v: docVersions) {
			dvDao.delete(v);
		}
	}
	
	public DocVersion findDocVersion(Long id) {
		DocVersion dv = dvDao.find(id);
		dv.setContent(cntDao.findByVersionId(id));
		return dv;
	}
	
	public List<DocVersion> findDocVersionsByCardId(Long id) {
		return dvDao.getAllByCardId(id);
	}
	
	public DocContent findContentByVersionId(Long id) {
		return cntDao.findByVersionId(id);
	}
	
	/**
	 * ¬озвращает последнюю версию документа. Ёто верси€ с 
	 * наибольшей датой введени€.
	 * 
	 * @param card карточка, дл€ которой надо получить последнюю версию
	 * документа.
	 * @return
	 */
	public DocVersion getLastDocVersion(Card card) {
		return dvDao.getLastVersion(card);
	}
	
	public Long getNextRegNum() {
		return scDao.getMaxRegNum() + 1;
	}
	
	public ContentType getContentTypeByMime(String mime) {
		return cntTypeDao.getByMime(mime);
	}
	
	public byte[] getFileContent(Long versionId) {
		return cntDao.getFileContent(versionId);
	}
	
	/**
	 * @param versionId
	 * 
	 * @return
	 */
	public List<DocSendRecord> getSendRecordsForVersion(Long versionId) {
		return dsrDao.findByVersionId(versionId);
	
	}
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void addSendRecord(DocVersion version, List<Department> deps) {
		
		Date date = new Date();
		for( Department dep: deps ){
			DocSendRecord sRec = new DocSendRecord();
			sRec.setDate(date);
			sRec.setDocVersion(version);
			sRec.setDep(dep);
			
			dsrDao.save(sRec);
		}
	}
	@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	public void deleteSendRecords(List<DocSendRecord> recs) {
		for( DocSendRecord rec: recs ) {
			dsrDao.delete(rec);
		}
	}
	
}
