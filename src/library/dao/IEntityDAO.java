package library.dao;

import java.util.List;

public interface IEntityDAO<T> {
	public List<T> getAll();
	public T find(Long id);
	public void save(T e);
	public void delete(T e);
}
