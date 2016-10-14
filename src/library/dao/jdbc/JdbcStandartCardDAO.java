package library.dao.jdbc;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import library.dao.IStandartCardDAO;
import library.model.StandartCard;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class JdbcStandartCardDAO implements IStandartCardDAO {
	
    private static final String SELECT_BY_ID = 
		"SELECT c.id, c.reg_num, sc.doc_code, c.name FROM lib.card c, lib.std_card sc WHERE c.id = ? AND sc.id = ?";
    private static final String SELECT_BY_NAME = 
		"SELECT c.id, c.reg_num, sc.doc_code, c.name FROM lib.card c, lib.std_card sc WHERE c.name LIKE ? AND c.id = sc.id";
    private static final String SELECT_BY_CODE = 
    	"SELECT c.id, c.reg_num, sc.doc_code, c.name FROM lib.card c, lib.std_card sc WHERE sc.doc_code LIKE ? AND c.id = sc.id";
    private static final String SELECT_BY_NAME_AND_CODE = 
    	"SELECT c.id, c.reg_num, sc.doc_code, c.name FROM lib.card c, lib.std_card sc WHERE c.name LIKE ? AND sc.doc_code LIKE ? AND c.id = sc.id";
    private static final String SELECT_ALL = 
		"SELECT c.id, c.reg_num, sc.doc_code, c.name FROM lib.card c, lib.std_card sc WHERE c.id = sc.id";
    private static final String SELECT_MAX_SC_REG_NUM = 
    	"SELECT max(c.reg_num) FROM lib.card c, lib.std_card sc WHERE c.id = sc.id";
    private static final String UPDATE_CARD_BY_ID = 
    	"UPDATE lib.card set name = ? WHERE id = ?";
    private static final String UPDATE_STD_CARD_BY_ID = 
    	"UPDATE lib.std_card set doc_code = ? WHERE id = ?";
    private static final String DELETE_BY_ID =
    	"DELETE FROM lib.card WHERE id = ?";
    
	private SimpleJdbcTemplate       tmpl;
	private SimpleJdbcInsert         cardInsertActor;
	private SimpleJdbcInsert         stdCardInsertActor;
	private RowMapper<StandartCard>  rm;
	
	
	
	public JdbcStandartCardDAO() {
		super();
		rm = new RowMapper<StandartCard>(){
			public StandartCard mapRow(ResultSet rs, int rowNum) 
			throws SQLException {
				StandartCard sc = new StandartCard();
				sc.setId(rs.getLong("id"));
				sc.setRegNum(rs.getLong("reg_num"));
				sc.setDocCode(rs.getString("doc_code"));
				sc.setName(rs.getString("name"));
				
				return sc;
			}
		};
	}
	
	public void setDataSource(DataSource ds){
    	tmpl = new SimpleJdbcTemplate(ds);
    	cardInsertActor= new SimpleJdbcInsert(ds)
    		.withSchemaName("lib")
			.withTableName("card")
			.usingGeneratedKeyColumns("id");
    	stdCardInsertActor= new SimpleJdbcInsert(ds)
    		.withSchemaName("lib")
    		.withTableName("std_card");
    }

	@Override
	public void delete(StandartCard sc) {
		tmpl.update(DELETE_BY_ID, sc.getId());
	}

	@Override
	public StandartCard find(Long id) {
		List<StandartCard> cards = tmpl.query(SELECT_BY_ID, rm, id, id);
		return cards.size() > 0 ? cards.get(0) : null;
	}

	@Override
	public List<StandartCard> getAll() {
		List<StandartCard> cards = tmpl.query(SELECT_ALL, rm);
		return cards;
	}

	@Override
	public void save(StandartCard sc) {
		
		if(sc.getId() == null) {
			Map<String, Object> cArgs = new HashMap<String, Object>();
			cArgs.put("reg_num", sc.getRegNum());
			cArgs.put("name", sc.getName());
			
			Long key = ((BigDecimal)cardInsertActor.executeAndReturnKey(cArgs))
				.longValue();
			
			Map<String, Object> scArgs = new HashMap<String, Object>();
			scArgs.put("id", key);
			scArgs.put("doc_code", sc.getDocCode());
			stdCardInsertActor.execute(scArgs);
			
			sc.setId(key);
		} else {
			tmpl.update(UPDATE_CARD_BY_ID, sc.getName(), sc.getId());
			tmpl.update(UPDATE_STD_CARD_BY_ID, sc.getDocCode(), sc.getId());
		}
	}

	@Override
    public Long getMaxRegNum() {
	    return tmpl.queryForLong(SELECT_MAX_SC_REG_NUM);
    }

	@Override
    public List<StandartCard> findByCode(String code) {
	    return tmpl.query(SELECT_BY_CODE, rm, code);
    }

	@Override
    public List<StandartCard> findByName(String name) {
	    return tmpl.query(SELECT_BY_NAME, rm, name);
    }

	@Override
    public List<StandartCard> findByNameAndCode(String name, String code) {
		return tmpl.query(SELECT_BY_NAME_AND_CODE, rm, name, code);
    }

}
