package ua.nure.fedosov.SummaryTask2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

	private static final String DEFAULT_ENCODING = System.getProperty("file.encoding");
	private static final PrintStream STANDARD_OUTPUT = System.out;
	private static Pattern pattern = Pattern.compile("\\p{L}+\\s*");
	private static Matcher matcher = null;

	

	public String[] readFile(String filePath) {
		List<String> list = new ArrayList<>();
		try (Scanner scanner = new Scanner(new File(filePath))) {
			if (scanner.hasNextLine() == false) {
				return null;
			}
			while (scanner.hasNextLine()) {
				matcher = pattern.matcher(scanner.nextLine());
				while (matcher.find()) {
					list.add(matcher.group());
				}
			}
		} catch (FileNotFoundException | NullPointerException e) {
			e.printStackTrace();
		}
		return list.toArray((String[]) Array.newInstance(String.class, list.size()));
	}

	public String[] sortArray(String[] unsortedArray) {
		try {
			Arrays.sort(unsortedArray, new Comparator<String>() {

				@Override
				public int compare(String o1, String o2) {
					return o1.compareToIgnoreCase(o2);
				}

			});
		} catch (IllegalArgumentException | NullPointerException e) {
			e.printStackTrace();
		}
		return unsortedArray;
	}

	public void printInfo(String[] sortedArray, String encoding) throws NotDefaultEncodingException {
		if (encoding != System.getProperty("file.encoding")) {
			throw new NotDefaultEncodingException("Not a valid encoding has passed for this operating system");
		}
		try (PrintWriter out = new PrintWriter(new OutputStreamWriter(STANDARD_OUTPUT, encoding), true)) {
			for (int i = 0; i < sortedArray.length;) {
				String str = sortedArray[i].substring(0, 1);
				out.print(sortedArray[i]);
				for (int j = i + 1; j < sortedArray.length; j++) {
					if (str.equalsIgnoreCase(sortedArray[j].substring(0, 1))) {
						out.print(sortedArray[j]);
						if (j == sortedArray.length - 1)
							return;
					} else {
						out.println();
						str = sortedArray[j];
						i = j;
						break;
					}
				}
			}
		} catch (UnsupportedEncodingException | NullPointerException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws NotDefaultEncodingException {
		Main main = new Main();
		 String[] unsortedArray = main.readFile(args[0]);
		 String[] sortedArray = main.sortArray(unsortedArray);
		 main.printInfo(sortedArray, DEFAULT_ENCODING);
	}
}
