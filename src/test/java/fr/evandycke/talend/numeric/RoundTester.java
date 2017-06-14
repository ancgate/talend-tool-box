package fr.evandycke.talend.numeric;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fr.evandycke.talend.log.AppLog;
import junit.framework.TestCase;

// TODO : Documenter
public class RoundTester extends TestCase {
	// logger
	private static AppLog log = AppLog.getInstanceForThisClass();

	private class TestSet {
		// valeurs
		String bd;
		String expectedResult;
		int nbDecimal;

		// constructeur
		public TestSet(String bd, int nbDecimal, String expectedResult) {
			this.nbDecimal = nbDecimal;
			this.bd = bd;
			this.expectedResult = expectedResult;
		}

		// getters
		public String getExpectedResult() {
			return expectedResult;
		}

		public int getNbDecimal() {
			return nbDecimal;
		}

		public BigDecimal getBd() {
			if (bd == null)
				return null;
			return new BigDecimal(bd);
		}
	}

	// creation de l'arrayList concernant les tests
	private List<TestSet> list = new ArrayList<TestSet>();

	@Before
	public void setUp() throws Exception {
		if (this.list.size() > 0) {
			return;
		}
		// ajouts de tests à la liste de tests
		this.list.add(new TestSet(null, 2, null));
		this.list.add(new TestSet("12.56456", 2, "12.56"));
		this.list.add(new TestSet("12.56656", 2, "12.57"));
		this.list.add(new TestSet("12.56656", 3, "12.567"));
	}

	@Test
	public final void testRound() {
		for (TestSet testSet : this.list) {
			String variable = "" + testSet.getBd();
			try {
				BigDecimal result = BigDecimalHelper.round(testSet.getBd(),
						testSet.getNbDecimal());
				log.info("Variable : %s - Attendu : %s - Trouvé : %s",
						variable, testSet.getExpectedResult(), result);

				assertEquals(String.valueOf(testSet.getExpectedResult()),
						String.valueOf(result));
			} catch (Exception e) {
				fail();
				return;
			}
		}
	}
}