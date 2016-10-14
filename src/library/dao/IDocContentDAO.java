package library.dao;

import library.model.DocContent;

public interface IDocContentDAO extends IEntityDAO<DocContent> {
	public DocContent findByVersionId(Long versionId);
	/**
	 * ���������� ���������� ����� ���������.
	 *  
	 * @param versionId ID ������ ���������, ���� ������� ����� ��������.
	 * @return
	 */
	public byte[] getFileContent(Long versionId);
}
