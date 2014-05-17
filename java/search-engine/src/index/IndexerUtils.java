package index;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IndexerUtils
{

	private static final String STOP_LIST_FILE = "src/stop_list.txt";

	public static Matcher findWords(String urlString)
	{
		Pattern wordPat = Pattern.compile("\\b[A-Za-z]+\\b", Pattern.CASE_INSENSITIVE);
		Matcher matchWord = wordPat.matcher(urlString);
		return matchWord;
	}

	public static String cleanseString(String urlString)
	{
		urlString = urlString.toLowerCase();
		urlString = urlString.replaceAll("\\<.*?>", " ");
		urlString = urlString.replaceAll("[^A-Z|^a-z|^0-9|^\\s]", " ");
		String[] vals = urlString.split(" ");
		String parsed = "";
		StringBuilder stringBuilder = new StringBuilder();
		for (String s : vals)
		{
			if (s.trim().length() > 0)
			{
				stringBuilder.append(s).append(" ");
			}
		}
		parsed = stringBuilder.toString().trim();

		return parsed;
	}

	private static boolean isStopWord(final List<String> stopList, String word)
	{
		return stopList.contains(word);
	}

	public static boolean isSignificant(final List<String> stopList, String word)
	{
		return !isStopWord(stopList, word) && hasValidLength(word);
	}

	private static boolean hasValidLength(String word)
	{
		return word.length() >= 3;
	}

	public static ArrayList<String> loadStopList()
	{
		ArrayList<String> stopList = new ArrayList<String>();
		
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(STOP_LIST_FILE));
			String stopWord = null;
			while ((stopWord = br.readLine()) != null)
			{
				stopList.add(stopWord);
			}
		}
		catch (Exception e)
		{
			System.out.println("Could not read stop-list file " + e.getMessage());
		}

		return stopList;
	}
}
