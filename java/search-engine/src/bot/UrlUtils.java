package bot;

import index.IndexerUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlUtils
{

	public static URL getURLwithProtocol(String base) throws MalformedURLException
	{
		if (!base.startsWith("http://"))
		{
			base = "http://" + base;
		}
		return new URL(base);
	}

	public static boolean isLocalUrl(final String baseURL, URL url)
	{
		Pattern pattern = Pattern.compile("^" + baseURL + ".*");
		Matcher matcher = pattern.matcher(url.toString());

		// if local
		if (matcher.find())
		{
			return true;
		}
		// if external
		else
		{
			return false;
		}
	}

	public static List<String> convertUrlsToStrings(Collection<URL> urls)
	{
		ArrayList<String> strUrls = new ArrayList<String>();

		for (URL u : urls)
		{
			strUrls.add(u.toString());
		}

		return strUrls;
	}

	public static boolean isIWRobotSafe(String baseURL, String url, Integer delay)
	{
		final String ROBOTS_TXT = "/robots.txt";

		String strRobotURL = url + ROBOTS_TXT;
		URL robotUrl;
		
		try
		{

			robotUrl = new URL(strRobotURL);
			strRobotURL = robotUrl.toString();

			url = url.replaceFirst(baseURL, "");
		}
		catch (MalformedURLException ex)
		{

			return false;
		}

		boolean flag = true;
		InputStream robotStream;
		
		try
		{
			robotStream = robotUrl.openStream();
			BufferedReader r = new BufferedReader(new InputStreamReader(
					robotStream));

			String line = null; // current line
			String userAgent = ""; // user-agent found
			String Rdelay = ""; // delay found

			Pattern userAgentPat = Pattern.compile("^User-agent: (.*)"); // user-agent
			Pattern disPat = Pattern.compile("^Disallow: " + "(.*)/?"); // disallow
			Pattern delayPat = Pattern.compile("^Crawl-delay: " + "(\\d.\\d+)"); // delay
			Matcher matcher;

			while ((line = r.readLine()) != null)
			{

				matcher = userAgentPat.matcher(line);
				if (matcher.find())
				{
					userAgent = matcher.group(1);
				}

				matcher = delayPat.matcher(line);
				if (matcher.find())
				{
					Rdelay = matcher.group(1);
					int requestedDelay = (int) (Float.parseFloat(Rdelay) * 1000);

					if (requestedDelay < delay)
					{
						delay = requestedDelay;
					}
				}

				matcher = disPat.matcher(line);
				if (matcher.find())
				{
					if ((userAgent.equals("*") || userAgent.contains("Bot Proeject")) && (url.contains(matcher.group(1)) || (url + "/").equals(matcher.group(1))))
					{
						flag = false;
					}
				}
			}

		}
		catch (IOException ex)
		{
			return true;
		}

		return flag;
	}

	private static boolean pageIsReadable(URLConnection urlConn)
	{
		Pattern p = Pattern.compile("text/.*", Pattern.CASE_INSENSITIVE);
		Matcher m = null;

		if (urlConn.getContentType() != null)
		{
			m = p.matcher(urlConn.getContentType());

			if (m.find())
			{
				return true;
			}
		}
		return false;
	}

	public static ArrayList<String> readUrlContents(String url,	List<String> stopList)
	{
		ArrayList<String> wordsInUrl = new ArrayList<String>();
		String urlContents = "";
		URL urlx = null;

		try
		{
			urlx = new URL(url);
			URLConnection conn = urlx.openConnection();

			if (pageIsReadable(conn))
			{

				BufferedReader in = null;
				in = new BufferedReader(new InputStreamReader(urlx.openStream()));
				String inputLine = null;
				while ((inputLine = in.readLine()) != null)
				{
					urlContents += inputLine;
				}
				in.close();

				urlContents = IndexerUtils.cleanseString(urlContents);

				Matcher matchWord = IndexerUtils.findWords(urlContents);
				while (matchWord.find())
				{
					String word = matchWord.group();
					word = word.toLowerCase();

					if (IndexerUtils.isSignificant(stopList, word))
					{
						wordsInUrl.add(word);
					}
				}
			}
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		return wordsInUrl;
	}

}
