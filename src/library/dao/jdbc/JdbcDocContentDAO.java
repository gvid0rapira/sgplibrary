package library.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import library.dao.IContentTypeDAO;
import library.dao.IDocContentDAO;
import library.dao.IDocVersionDAO;
import library.model.DocContent;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class JdbcDocContentDAO implements IDocContentDAO {
	
	private static final String SELECT_BY_ID =
		"SELECT id, version_id, file_name, content_type_id FROM lib.content WHERE id = ?";
	private static final String SELECT_BY_VERSION_ID = 
		"SELECT id, version_id, file_name, content_type_id FROM lib.content WHERE version_id = ?";
	private static final String SELECT_FILE_CONTENT_BY_VERSION_ID =
		"SELECT content FROM lib.content WHERE version_id = ?";
	
	private SimpleJdbcTemplate     tmpl;
	private SimpleJdbcInsert       insertActor;
	private RowMapper<DocContent>  rm;
	
	private IContentTypeDAO        ctDao;
	private IDocVersionDAO         dvDao;
	
	public JdbcDocContentDAO() {
		super();
		// атрибут content не подгружается, т.к. он имеет большой объём и
		// работы с ним ведутся отдельно.
		rm = new RowMapper<DocContent>(){
			public DocContent mapRow(ResultSet rs, int rowNum) 
			throws SQLException {
				DocContent dс = new DocContent();
				dс.setId(rs.getLong("id"));
				dс.setFileName(rs.getString("file_name"));
				dс.setCntType(ctDao.find(rs.getLong("content_type_id")));
				return dс;
			}
		};
	}
	
	public void setDataSource(DataSource ds){
    	tmpl = new SimpleJdbcTemplate(ds);
    	insertActor= new SimpleJdbcInsert(ds)
    		.withSchemaName("lib")
    		.withTableName("content")
    		.usingGeneratedKeyColumns("id");
    }

	public void setCtDao(IContentTypeDAO ctDao) {
    	this.ctDao = ctDao;
    }
	public void setDvDao(IDocVersionDAO dvDao) {
    	this.dvDao = dvDao;
    }

	@Override
	public void delete(DocContent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public DocContent find(Long id) {
		List<DocContent> dcl = tmpl.query(SELECT_BY_ID, rm, id);
		return dcl.size() > 0 ? 
				dcl.get(0) : null;
	}
	
	@Override
    public DocContent findByVersionId(Long versionId) {
		List<DocContent> dcl = tmpl.query(SELECT_BY_VERSION_ID, rm, versionId);
		return dcl.size() > 0 ? 
				dcl.get(0) : null;
    }
	
	@Override
	public List<DocContent> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(DocContent e) {
		// TODO Auto-generated method stub

	}

	@Override
    public byte[] getFileContent(Long versionId) {
	    Map<String, Object> cntFld = tmpl.queryForMap(
	    		SELECT_FILE_CONTENT_BY_VERSION_ID, versionId);
	    byte[] content = (byte[])cntFld.get("content");
	    
	    return content;
    }

}
