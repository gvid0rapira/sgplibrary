package library.dao.jdbc;

import library.dao.jdbc.JdbcContentTypeDAO;
import library.model.ContentType;

import org.junit.Test;
import org.junit.BeforeClass;
import static org.junit.Assert.*;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class JdbcContentTypeDAOTest {
    
    private static JdbcContentTypeDAO ctDao;
    
    @BeforeClass
    public static void setUpClass(){
	ApplicationContext ctx = new FileSystemXmlApplicationContext(new String[] 
		    {"WebContent/WEB-INF/library-data-jdbc.xml"});
	ctDao = (JdbcContentTypeDAO)ctx.getBean("cntTypeDAO");
    }

    @Test
    public void testGetById(){
	try{

	    ContentType ct1 = ctDao.getByMime("image/tiff");
	
	    assertNotNull(ct1);
	    
	}catch(Exception e){
	    e.printStackTrace();
	    fail();
	}
    }

    @Test
    public void testInsert(){
	try{
	    
	    ContentType ct1 = new ContentType();
	    ct1.setExt("tst");
	    ct1.setMime("application/test");
	    ctDao.save(ct1);

	    ContentType ct2 = ctDao.getByMime("application/test");
	    assertNotNull(ct2);
	    
	}catch(Exception e){
	    e.printStackTrace();
	    fail();
	}
    }
    
    @Test
    public void testDelete(){
	try{

	    ContentType ct1 = ctDao.getByMime("application/test");
	
	    ctDao.delete(ct1);
	    
	}catch(Exception e){
	    e.printStackTrace();
	    fail();
	}
    }
}