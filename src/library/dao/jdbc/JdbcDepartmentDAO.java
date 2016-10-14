package library.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import library.dao.IDepartmentDAO;
import library.model.ContentType;
import library.model.Department;

public class JdbcDepartmentDAO implements IDepartmentDAO {
	
	private static final String SELECT_BY_ID = 
		"SELECT * FROM lib.department WHERE id = ?";
	private static final String SELECT_ALL = 
		"SELECT * FROM lib.department";
	
	private SimpleJdbcTemplate     tmpl;
    private SimpleJdbcInsert       insertActor;
	private RowMapper<Department> rm;
    
    public JdbcDepartmentDAO() {
	    super();
	    rm = new RowMapper<Department>(){
			public Department mapRow(ResultSet rs, int rowNum) 
			throws SQLException {
				Department d = new Department();
				
			    d.setId(rs.getLong("id"));
			    d.setName(rs.getString("name"));
			    d.setNumber(rs.getString("number"));
			    return d;
			}
		};
    }
	
    public void setDataSource(DataSource ds){
    	tmpl = new SimpleJdbcTemplate(ds);
    }
	
	@Override
	public void delete(Department e) {
		// TODO Auto-generated method stub

	}

	@Override
	public Department find(Long id) {
		List<Department> dl = tmpl.query(SELECT_BY_ID, rm, id);
    	return dl.size() > 0 ? dl.get(0): null;
	}

	@Override
	public List<Department> getAll() {
		return tmpl.query(SELECT_ALL, rm);
	}

	@Override
	public void save(Department e) {
		// TODO Auto-generated method stub

	}

}
