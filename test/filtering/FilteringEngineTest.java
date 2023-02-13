/**
 * 
 */
package filtering;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;

import src.filtering.FilteringEngineFactory;
import src.metadata.MetadataManagerFactory;

/**
 * @author pvassil
 *
 */
public class FilteringEngineTest {
	private static FilteringEngineFactory filteringEngine;
	private static Map<String, List<String>> atomicFilters;
	private static MetadataManagerFactory metadataManager;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		String sAlias = "simple";
		String sSeparator = ",";
		File sFile = new File("./test/resources/input/simple.csv");
		metadataManager = new MetadataManagerFactory(sAlias, sFile, sSeparator);

		atomicFilters = new HashMap<String, List<String>>();
		List<String> countryFilter = new ArrayList<String>();
		countryFilter.add("AUS:Australia");
		atomicFilters.put("LOCATION:Country", countryFilter);

		filteringEngine = new FilteringEngineFactory(atomicFilters, metadataManager);
	}

	/**
	 * Test method for {@link filtering.FilteringEngine#workWithFile()}.
	 */
	@Test
	public final void testWorkWithFileHappyDay() {
		List<String[]> verySimpleResult = FilteringEngineFactory.workWithFile();
		assertEquals(15, verySimpleResult.size());
	}

	/**
	 * Test method for {@link filtering.FilteringEngine#workWithFile()}.
	 */
	@Test
	public final void testMultipleCriteriaHappyDay() throws Exception {

		List<String> timeFilter = new ArrayList<String>();
		timeFilter.add("2010");
		timeFilter.add("2011");
		timeFilter.add("2012");
		atomicFilters.put("TIME:Year", timeFilter);

		FilteringEngineFactory anotherfilteringEngine = new FilteringEngineFactory(atomicFilters, metadataManager);
		List<String[]> verySimpleResult = anotherfilteringEngine.workWithFile();
		assertEquals(3, verySimpleResult.size());
	}

}// end class
