package library.dao;

import library.model.ContentType;

public interface IContentTypeDAO extends IEntityDAO<ContentType>{
	public ContentType getByMime(String mimeName);
	public void save(ContentType cntt);
}
