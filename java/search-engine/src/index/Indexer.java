package index;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Hashtable;

import bot.UrlUtils;
import database.DataManager;
import database.DatabaseManager;

public class Indexer implements Runnable
{

	private Connection conn = null;
	private ArrayList<String> stopList;
	private ArrayList<String> urls;
	private String processingMessage;
	private boolean isFinished;

	public boolean isFinished()
	{
		return isFinished;
	}

	public void setFinished(boolean isFinished)
	{
		this.isFinished = isFinished;
	}

	public synchronized String getProcessingMessage()
	{
		return processingMessage;
	}

	public synchronized void setProcessingMessage(String processingMessage)
	{
		this.processingMessage = processingMessage;
	}

	public Indexer()
	{

		stopList = IndexerUtils.loadStopList();

		try
		{
			conn = DatabaseManager.getConnection();

			urls = DataManager.getAllUrls(conn);
			this.processingMessage = "";
		}
		catch (Exception e)
		{
			System.out.println("Indexer init failed: " + e.getMessage());
			System.exit(-1);
		}
		finally
		{
			DatabaseManager.closeConnection(conn);
		}

	}

	public void IndexPages()
	{
		int counter = 0;

		Hashtable<String, Integer> frequencyMap = null;
		ArrayList<String> wordsInUrlContents = null;

		for (String url : urls)
		{

			frequencyMap = new Hashtable<String, Integer>();
			wordsInUrlContents = UrlUtils.readUrlContents(url, stopList);

			for (String word : wordsInUrlContents)
			{
				if (frequencyMap.containsKey(word))
				{
					int freq = frequencyMap.get(word) + 1;
					frequencyMap.put(word, freq);
				}
				else
				{
					frequencyMap.put(word, new Integer(1));
				}
			}

			try
			{
				conn = DatabaseManager.getConnection();
				DataManager.saveIndexed(conn, url, frequencyMap);
			}
			catch (Exception e)
			{
				System.out.println("Could not store the index for " + url + ": " + e.getMessage());
			}
			finally
			{
				// release database resources and reset the variables
				DatabaseManager.closeConnection(conn);
				frequencyMap = null;
				wordsInUrlContents = null;
				counter++;
				setProcessingMessage(counter + " / 1000");
				System.out.println("Indexing " + url + " finished");
			}

		}

		setProcessingMessage("IndexPages process has finished");
		setFinished(true);
	}

	public void run()
	{
		IndexPages();
	}
}