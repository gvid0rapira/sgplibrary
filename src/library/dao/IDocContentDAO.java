package library.dao;

import library.model.DocContent;

public interface IDocContentDAO extends IEntityDAO<DocContent> {
	public DocContent findByVersionId(Long versionId);
	/**
	 * Возвращает содержимое файла документа.
	 *  
	 * @param versionId ID версии документа, файл которой нужно получить.
	 * @return
	 */
	public byte[] getFileContent(Long versionId);
}
