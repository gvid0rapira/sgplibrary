package library.dao.jdbc;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import library.dao.IDocVersionDAO;
import library.dao.IStandartCardDAO;
import library.model.Card;
import library.model.DocVersion;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class JdbcDocVersionDAO implements IDocVersionDAO {
	
	private static final String SELECT_BY_ID =
		"SELECT * FROM lib.doc_version WHERE id = ?";
	private static final String SELECT_ALL_BY_CARD =
		"SELECT * FROM lib.doc_version WHERE card_id = ?";
	private static final String SELECT_NEWEST_BY_CARD =
		"SELECT * FROM lib.doc_version WHERE card_id = ? AND appro_date = (SELECT MAX(appro_date) FROM lib.doc_version WHERE card_id = ?)";
	
	private static final String UPDATE_VERSION_BY_ID =
		"UPDATE lib.doc_version SET appro_date = ? WHERE id = ?";
	private static final String UPDATE_CONTENT_BY_ID =
		"UPDATE lib.content SET file_name = ?, content = ?, content_type_id = ? WHERE id = ?";
	private static final String DELETE_BY_ID =
		"DELETE FROM lib.doc_version WHERE id = ?";
	
	private SimpleJdbcTemplate    tmpl;
	private SimpleJdbcInsert      vInsertActor;
	private SimpleJdbcInsert      cntInsertActor;
	private RowMapper<DocVersion> rm;
	private IStandartCardDAO      cDao;
	
	public JdbcDocVersionDAO() {
		super();
		rm = new RowMapper<DocVersion>(){
			public DocVersion mapRow(ResultSet rs, int rowNum) 
			throws SQLException {
				DocVersion dv = new DocVersion();
				dv.setId(rs.getLong("id"));
				dv.setApproDate(rs.getDate("appro_date"));
				dv.setCard( cDao.find( rs.getLong("card_id")));
				// dv.setContent( contentDao.findByVersion( rs.getLong( "id" ) ));
				return dv;
			}
		};
	}
	
	public void setDataSource(DataSource ds){
    	tmpl = new SimpleJdbcTemplate(ds);
    	vInsertActor= new SimpleJdbcInsert(ds)
    	    .withSchemaName("lib")
    		.withTableName("doc_version")
    		.usingGeneratedKeyColumns("id");
    	cntInsertActor= new SimpleJdbcInsert(ds)
	    	.withSchemaName("lib")
	    	.withTableName("content")
	    	.usingGeneratedKeyColumns("id");
    }
	
	public void setcDao(IStandartCardDAO cDao) {
    	this.cDao = cDao;
    }
	

	@Override
	public void delete(DocVersion v) {
		tmpl.update(DELETE_BY_ID, v.getId());
		
	}

	@Override
	public DocVersion find(Long id) {
		List<DocVersion> versions = 
			tmpl.query(SELECT_BY_ID, rm, id);
		return versions.size() > 0 ? versions.get(0) : null;
	}

	@Override
	public List<DocVersion> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(DocVersion v) {
		if (v.getId() == null) {
			// Ñîõðàíÿåì âåðñèþ.
			Map<String, Object> vArgs = new HashMap<String, Object>();
			vArgs.put("appro_date", v.getApproDate());
			vArgs.put("card_id", v.getCard().getId());
			
			Long vKey = ((BigDecimal)vInsertActor.executeAndReturnKey(vArgs))
				.longValue();
			
			
			v.setId(vKey);
			
			// Ñîõðàíÿåì ñîäåðæèìîå.
			Map<String, Object> ñntArgs = new HashMap<String, Object>();
			ñntArgs.put("file_name", v.getContent().getFileName());
			ñntArgs.put("content", v.getContent().getContent());
			ñntArgs.put("content_type_id", v.getContent().getCntType().getId());
			ñntArgs.put("version_id", v.getId());
			
			Long cntKey = ((BigDecimal)cntInsertActor.executeAndReturnKey(ñntArgs))
				.longValue();
		
		
			v.getContent().setId(cntKey);
			
		} else {
			tmpl.update(UPDATE_VERSION_BY_ID, v.getApproDate(), v.getId());
			tmpl.update(UPDATE_CONTENT_BY_ID, v.getContent().getFileName(),
					v.getContent().getContent(), 
					v.getContent().getCntType().getId(),
					v.getContent().getId());
		}
	}


	@Override
	public DocVersion getLastVersion(Card card) {
		List<DocVersion> dvs =
			tmpl.query(SELECT_NEWEST_BY_CARD, rm, card.getId(), card.getId());
		return dvs.size() > 0 ? dvs.get(0) : null;
	}

	@Override
    public List<DocVersion> getAllByCardId(Long id) {
	    return tmpl.query(SELECT_ALL_BY_CARD, rm, id);
    }
	
}
