package library.dao;

import java.util.List;

import library.model.Card;
import library.model.DocVersion;

public interface IDocVersionDAO extends IEntityDAO<DocVersion> {
	public DocVersion getLastVersion(Card card);
	public List<DocVersion> getAllByCardId(Long id);
}
