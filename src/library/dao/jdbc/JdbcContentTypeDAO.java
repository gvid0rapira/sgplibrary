 
package library.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import library.dao.IContentTypeDAO;
import library.model.ContentType;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

public class JdbcContentTypeDAO implements IContentTypeDAO {
	
	private static final String SELECT_BY_ID = 
		"SELECT * FROM lib.content_type WHERE id = ?";
	private static final String SELECT_BY_MIME = 
		"SELECT * FROM lib.content_type WHERE mime = ?";
    private static final String UPDATE_BY_ID = 
    	"UPDATE lib.content_type SET ext = ?, mime = ? WHERE id = ?";
    private static final String DELETE_BY_ID = 
    	"DELETE FROM lib.content_type WHERE id = ?";
	
    private SimpleJdbcTemplate     tmpl;
    private SimpleJdbcInsert       insertActor;
	private RowMapper<ContentType> rm;
    
    public JdbcContentTypeDAO() {
	    super();
	    rm = new RowMapper<ContentType>(){
			public ContentType mapRow(ResultSet rs, int rowNum) 
			throws SQLException {
				ContentType ct = new ContentType();
				
			    ct.setId(rs.getLong(1));
			    ct.setExt(rs.getString(2));
			    ct.setMime(rs.getString(3));
			    return ct;
			}
		};
    }
	
    public void setDataSource(DataSource ds){
    	tmpl = new SimpleJdbcTemplate(ds);
    }
	
    public ContentType getByMime(String mime){
	
    	List<ContentType> ctl = tmpl.query(SELECT_BY_MIME, rm, mime);
		
    	return ctl.size() > 0 ? ctl.get(0): null;
    }

    public void save(ContentType ct){
		if(ct.getId() == null){
			
		    // TODO: Заполнить
		}else{
		    tmpl.update(UPDATE_BY_ID,
					ct.getExt(), ct.getMime(), ct.getId());
		}
    }

    public void delete(ContentType ct){
    	tmpl.update(DELETE_BY_ID, ct.getId());
    }

	@Override
	public ContentType find(Long id) {
		
		List<ContentType> ctl = tmpl.query(SELECT_BY_ID, rm, id);
		
    	return ctl.size() > 0 ? ctl.get(0): null;
	}

	@Override
	public List<ContentType> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
}