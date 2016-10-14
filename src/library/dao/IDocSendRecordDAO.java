package library.dao;

import java.util.List;

import library.model.DocSendRecord;

public interface IDocSendRecordDAO extends IEntityDAO<DocSendRecord> {
	public List<DocSendRecord> findByVersionId(Long version_id);
}
