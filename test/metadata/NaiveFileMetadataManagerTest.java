/**
 * 
 */
package metadata;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import src.metadata.MetadataManagerFactory;

/**
 * @author pvassil
 *
 */
public class NaiveFileMetadataManagerTest {
	//private static MetadataManagerFactory metadataManager;
	private static String pAlias;
	private static File pFile;
	private static String pSeparator;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		pAlias = "defaultAlias";
		pFile = new File("./test/resources/input/simple.csv");
		pSeparator = ",";
		MetadataManagerFactory f = new MetadataManagerFactory(String pAlias, File pFile, String pSeparator);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		;
	}

//	/**
//	 * Test method for {@link metadata.NaiveFileMetadataManager#NaiveFileMetadataManager(java.lang.String, java.io.File, java.lang.String)}.
//	 */
//	@Test
//	public final void testNaiveFileMetadataManager() {
//		fail("Not yet implemented"); // TODO
//	}

	/**
	 * Test method for
	 * {@link metadata.NaiveFileMetadataManager#getFieldPositions()}.
	 */
	@Test
	public final void testGetFieldPositionsHappyDay() {

		String[] expectedColNames = { "HF:Financing scheme", "HC:Function", "HP:Provider", "LOCATION:Country",
				"TIME:Year", "MSR:Value" };
		List<String> colList = Arrays.asList(expectedColNames);
		int schemePos = colList.indexOf("HF:Financing scheme");
		int timePos = colList.indexOf("TIME:Year");
		assertEquals(0, schemePos);
		assertEquals(4, timePos);
	}

	/**
	 * Test method for {@link metadata.NaiveFileMetadataManager#getDataFile()}.
	 */
	@Test
	public final void testGetDataFileHappyDay() {
		File resultFile = metadataManager.getDataFile();
		File referenceFile = new File("./test/resources/input/simple.csv");

		if (!resultFile.exists())
			fail("The result file does not exist");
		try {
			assert (referenceFile.getCanonicalPath().equals(resultFile.getCanonicalPath()));
		} catch (IOException e) {
			System.err.println(
					"NaiveFileMAtadataManagerTest::testGetDataFileHappyDay() failed to contract ref and result files");
			e.printStackTrace();
		}
	}

	/**
	 * Test method for {@link metadata.NaiveFileMetadataManager#getSeparator()}.
	 */
	@Test
	public final void testGetSeparatorHappyDay() {
		String resultSep = metadataManager.getSeparator();
		assertEquals(resultSep, ",");
	}

	/**
	 * Test method for {@link metadata.NaiveFileMetadataManager#getColumnNames()}.
	 */
	@Test
	public final void testGetColumnNamesHappyDay() {
		String[] resColNames = metadataManager.getColumnNames();
		String[] expectedColNames = { "HF:Financing scheme", "HC:Function", "HP:Provider", "LOCATION:Country",
				"TIME:Year", "MSR:Value" };
		for (int i = 0; i < resColNames.length; i++) {
			if (!resColNames[i].equals(expectedColNames[i]))
				fail("Erroneous col name arrays");
		}
	}

	/**
	 * Test method for {@link metadata.NaiveFileMetadataManager#getAlias()}.
	 */
	@Test
	public final void testGetAliasHappyDay() {
		String resultAlias = metadataManager.getAlias();
		assertEquals(resultAlias, "defaultAlias");
	}

	// @Test(expected = Exception.class)
	@Test(expected = NullPointerException.class)
	public final void testGetDataFileWrongFile() {
		String alias = "defaultAlias";
		File f = new File("./test/resources/input/NowyouSeeMeNowYouDont.csv");
		String separator = ",";
		metadataManager = new MetadataManagerFactory(alias, f, separator);

	}

}// end class
