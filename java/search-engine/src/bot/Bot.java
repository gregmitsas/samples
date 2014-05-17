package bot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.text.MutableAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLEditorKit;

import database.DataManager;
import database.DatabaseManager;

public class Bot implements Runnable
{

	private String processingMessage;
	private String baseUrl;
	
	private ArrayList<URL> internalURLs = new ArrayList<URL>();
	private ArrayList<URL> externalURLs = new ArrayList<URL>();
	private ArrayList<URL> workLoad = new ArrayList<URL>();
	private ArrayList<URL> processedURLs = new ArrayList<URL>();
	private ArrayList<URL> forbiddenURLs = new ArrayList<URL>();

	private boolean isRunning;
	private boolean isFinished;

	protected int numOfPages;
	protected int brokenLinks;
	protected int forbidden;

	private Integer delay;

	protected boolean kill;

	private Thread thread = new Thread(this);

	public Bot()
	{
		isRunning = false;
		isFinished = false;
		kill = false;
		numOfPages = 0;
		brokenLinks = 0;
		forbidden = 0;

		delay = new Integer(300);
		processingMessage = "";
	}

	public synchronized String getProcessingMessage()
	{
		return processingMessage;
	}

	public synchronized void setProcessingMessage(String processingMessage)
	{
		this.processingMessage = processingMessage;
	}

	public String getBaseUrl()
	{
		return baseUrl;
	}

	public void setBaseUrl(URL base)
	{
		this.baseUrl = base.toString();
	}

	public boolean isRunning()
	{
		return isRunning;
	}

	public void setFinished(boolean isFinished)
	{
		this.isFinished = isFinished;
	}

	public boolean isFinished()
	{
		return isFinished;
	}

	public ArrayList<URL> getExternalURLs()
	{
		return externalURLs;
	}

	public ArrayList<URL> getInternalURLs()
	{
		return internalURLs;
	}

	protected ArrayList<URL> getProcessedURLs()
	{
		return processedURLs;
	}

	public ArrayList<URL> getForbiddenURLs()
	{
		return forbiddenURLs;
	}
	
	protected ArrayList<URL> getWorkLoad()
	{
		return workLoad;
	}

	public void addInternalURL(URL url)
	{
		if (internalURLs.contains(url) == false)
		{
			internalURLs.add(url);
		}
	}
	
	protected void addExternalURL(URL url)
	{
		if (!externalURLs.contains(url))
		{
			externalURLs.add(url);
		}
	}

	protected void addProcessedURL(URL url)
	{
		if (!processedURLs.contains(url))
		{
			processedURLs.add(url);
		}
	}

	protected void addWorkLoad(URL url)
	{
		if (!processedURLs.contains(url) && !workLoad.contains(url))
		{
			workLoad.add(url);
		}
	}

	public void startBot(String inputUrl)
	{

		isFinished = false;
		kill = false;
		isRunning = true;

		try
		{
			URL base = UrlUtils.getURLwithProtocol(inputUrl);
			this.setBaseUrl(base);
			this.addInternalURL(base);
			this.addWorkLoad(base);
		}
		catch (MalformedURLException ex)
		{
			System.out.println("Invalid URL");
		}

		thread.start();
	}

	public void killBot()
	{
		kill = true;
		isRunning = false;
		isFinished = true;
	}

	public void run()
	{

		while (!processedURLs.containsAll(workLoad) && isRunning && !kill)
		{

			for (int i = 0; i < workLoad.size(); i++)
			{
				URL temp = workLoad.get(i);

				if (!processedURLs.contains(temp) && isRunning && !kill)
				{
					processURL(temp);
					workLoad.remove(temp);
					addProcessedURL(temp);
					setProcessingMessage(processedURLs.size() + "/ 1000 urls");

					// 1000 pages limit
					if (processedURLs.size() == 1000)
					{
						killBot();
						setFinished(true);
						break;
					}
				}
				else if (!isRunning)
				{
					break;
				}
			}

			List<String> urlsToSave = UrlUtils.convertUrlsToStrings(processedURLs);

			Connection conn = DatabaseManager.getConnection();

			DataManager.saveUrls(conn, urlsToSave);

			DatabaseManager.closeConnection(conn);

			DatabaseManager.shutDown();
			setProcessingMessage("bot finished");

		}

	}

	// Processing a URL
	private void processURL(URL url)
	{
		URL validUrl = url;

		if (!UrlUtils.isLocalUrl(baseUrl, validUrl))
		{
			this.addExternalURL(validUrl);
			return;
		}

		numOfPages++;

		URLConnection conn;
		try
		{
			conn = url.openConnection();
			// connection properties
			conn.setRequestProperty("User-agent", "Bot Project");
			conn.setRequestProperty("Accept-Language", "en-us");
			conn.setRequestProperty("Content-Type", "text/*");
			conn.setRequestProperty("Accept-Encoding", "gzip,deflate");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setRequestProperty("Cache-Control", "no-cache");

			// check connection content type
			if ((conn.getContentType() != null) && !conn.getContentType().toLowerCase().startsWith("text/"))
			{
				return;
			}

			// check robots.txt
			if (!UrlUtils.isIWRobotSafe(baseUrl, validUrl.toString(), delay))
			{
				forbiddenURLs.add(validUrl);
				forbidden++;
				return;
			}

			InputStream is1 = url.openStream();
			Reader r = new InputStreamReader(is1);
			BufferedReader rd = new BufferedReader(new InputStreamReader(is1));

			// parsing XML to find links
			if (conn.getContentType().contains("xml") || conn.getContentType().contains("plain"))
			{
				String line;
				Pattern urlPat = Pattern.compile("(http://.*?)/?[\"|\\s|<]");
				while ((line = rd.readLine()) != null)
				{
					Matcher matcher = urlPat.matcher(line);

					while (matcher.find())
					{
						addWorkLoad(new URL(matcher.group(1)));
					}
				}
			}
			else
			{
				// parse HTML
				HTMLEditorKit.Parser parse = new HtmlParser().getParser();
				parse.parse(r, new Parser(validUrl), true);
			}

			this.addInternalURL(url);

			try
			{
				Thread.sleep(delay);
			}
			catch (InterruptedException ex)
			{
				ex.printStackTrace();
			}

		}
		catch (IOException e)
		{
			brokenLinks++;
		}

	}

	// HTML PARSER
	protected class Parser extends HTMLEditorKit.ParserCallback
	{

		protected URL base;

		public Parser(URL base)
		{
			this.base = base;
		}

		public void handleSimpleTag(HTML.Tag t, MutableAttributeSet a, int pos)
		{
			String href = (String) a.getAttribute(HTML.Attribute.HREF);

			if ((href == null) && (t == HTML.Tag.FRAME))
			{
				href = (String) a.getAttribute(HTML.Attribute.SRC);
			}

			if (href == null)
			{
				return;
			}

			int i = href.indexOf('#');
			if (i != -1)
			{
				href = href.substring(0, i);
			}

			if (href.toLowerCase().startsWith("mailto:"))
			{
				return;
			}

			handleLink(base, href);
		}

		public void handleStartTag(HTML.Tag t, MutableAttributeSet a, int pos)
		{
			handleSimpleTag(t, a, pos);
		}

		protected void handleLink(URL base, String str)
		{
			try
			{
				URL url = new URL(base, str);
				addWorkLoad(url);
			}
			catch (MalformedURLException e)
			{
				System.out.println("Found malformed URL: " + str);
			}
		}
	}

}
