package library.dao.jdbc;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import library.dao.IDepartmentDAO;
import library.dao.IDocSendRecordDAO;
import library.dao.IDocVersionDAO;
import library.model.DocSendRecord;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class JdbcDocSendRecordDAO implements IDocSendRecordDAO {
	
	private static final String SELECT_BY_VERSION_ID = 
		"SELECT * FROM lib.doc_send_record WHERE version_id = ?";
	
	private static final String DELETE_BY_ID =
		"DELETE FROM lib.doc_send_record WHERE id = ?";
	
	private SimpleJdbcTemplate       tmpl;
    private SimpleJdbcInsert         insertActor;
	private RowMapper<DocSendRecord> rm;
    
	private IDocVersionDAO           dvDao;
	private IDepartmentDAO           dDao;
	
    public JdbcDocSendRecordDAO() {
	    super();
	    rm = new RowMapper<DocSendRecord>(){
			public DocSendRecord mapRow(ResultSet rs, int rowNum) 
			throws SQLException {
				DocSendRecord dsr = new DocSendRecord();
				
			    dsr.setId(rs.getLong("id"));
			    dsr.setDate(rs.getDate("send_date"));
			    dsr.setDocVersion(dvDao.find(rs.getLong("version_id")));
			    dsr.setDep(dDao.find(rs.getLong("dep_id")));
			    return dsr;
			}
		};
    }
	
    public void setDataSource(DataSource ds){
    	tmpl = new SimpleJdbcTemplate(ds);
    	insertActor= new SimpleJdbcInsert(ds)
		.withSchemaName("lib")
		.withTableName("doc_send_record")
		.usingGeneratedKeyColumns("id"); 
    }
	
    
    
	public void setDvDao(IDocVersionDAO dvDao) {
    	this.dvDao = dvDao;
    }

	public void setdDao(IDepartmentDAO dDao) {
    	this.dDao = dDao;
    }

	@Override
	public void delete(DocSendRecord rec) {
		tmpl.update(DELETE_BY_ID, rec.getId());
	}

	@Override
	public DocSendRecord find(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DocSendRecord> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Выполняет только добавление.
	 */
	@Override
	public void save(DocSendRecord rec) {
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("send_date", rec.getDate());
		args.put("version_id", rec.getDocVersion().getId());
		args.put("dep_id", rec.getDep().getId());
		Long key = ((BigDecimal)insertActor.executeAndReturnKey(args))
			.longValue();
		rec.setId(key);
	}

	@Override
    public List<DocSendRecord> findByVersionId(Long version_id) {
	    return tmpl.query(SELECT_BY_VERSION_ID, rm, version_id);
    }

}
