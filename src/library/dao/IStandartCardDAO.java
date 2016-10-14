package library.dao;

import java.util.List;

import library.model.StandartCard;

public interface IStandartCardDAO extends IEntityDAO<StandartCard> {
	
	public Long getMaxRegNum();
	public List<StandartCard> findByName(String name);
	public List<StandartCard> findByCode(String code);
	public List<StandartCard> findByNameAndCode(String name, String code);
}
