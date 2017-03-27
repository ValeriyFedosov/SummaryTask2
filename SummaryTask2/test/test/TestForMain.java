package test;

import org.junit.Assert;
import org.junit.Test;
import ua.nure.fedosov.SummaryTask2.Main;
import ua.nure.fedosov.SummaryTask2.NotDefaultEncodingException;

public class TestForMain {

	private Main main = new Main();

	@Test
	public void testReadFileForEmptyFile() {
		Assert.assertNull(main.readFile("EmptyFile.txt"));
	}

	@Test
	public void testReadFileForNotEMPTYFile() {
		Assert.assertNotNull(main.readFile("Text.txt"));
	}

	@Test(expected = NotDefaultEncodingException.class)
	public void testPrintInfoForEncodings() throws NotDefaultEncodingException {
		main.printInfo(new String[] { "one", "two" }, "fakedEncoding");
	}

}
