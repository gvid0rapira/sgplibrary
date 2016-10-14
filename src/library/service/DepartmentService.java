package library.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import library.dao.IDepartmentDAO;
import library.model.Department;

@Transactional(readOnly = true)
@Service
public class DepartmentService {
	
	@Autowired
	IDepartmentDAO depDao;
	
	public List<Department> getAllDeps() {
		return depDao.getAll();
	}
	
}
