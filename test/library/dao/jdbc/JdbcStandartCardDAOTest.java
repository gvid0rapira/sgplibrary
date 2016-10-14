package library.dao.jdbc;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import library.model.StandartCard;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class JdbcStandartCardDAOTest {

	private static JdbcStandartCardDAO scDao;
	private static StandartCard sc1;

	@BeforeClass
	public static void setUpClass() {
		ApplicationContext ctx = new FileSystemXmlApplicationContext(
		        new String[] { "WebContent/WEB-INF/library-data-jdbc.xml" });
		scDao = (JdbcStandartCardDAO) ctx.getBean("StandartCardDAO");
	}

	@Test
	public void testInsert() {
		try {

			sc1 = new StandartCard();
			sc1.setRegNum(1L);
			sc1.setDocCode("СТД 0001");
			sc1.setName("Стандарт №1");
			scDao.save(sc1);

			StandartCard sc1stored = scDao.find(sc1.getId());
			assertNotNull(sc1stored);

		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}

	@Test
	public void testDelete() {
		try {
			scDao.delete(sc1);
		} catch (Exception e) {
			e.printStackTrace();
			fail();
		}
	}
}