package project1;

import java.util.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class QueryExpander
{
	public static final String ACCOUNTKEY = "SjDOYitRz3IJ7yB5cVrI4AE96FV1vrvS1Q2PWRNU5s";
	
	public static void main(String[] args)
	{
		QueryExpander QE = new QueryExpander();
		QE.initiateQuery();
	}
	
	private void initiateQuery()
	{
		Console console = System.console();
		String input = console.readLine("Please enter search term: ");
		while (input.isEmpty())
		{
			input = console.readLine("Please enter a valid search term: ");
		}
		
		String precision = console.readLine("Please enter desired precision(between 0 and 1): ");
		while (precision.isEmpty() || !isPrecisionValid(precision))
		{
			precision = console.readLine("Please enter a valid precision(between 0 and 1): ");
		}
		
		try {
			String bingResults = searchBing(input);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private String searchBing(String query) throws IOException
	{
		String bingUrl = "https://api.datamarket.azure.com/Bing/Search/Web?Query=%27" + query + "%27&$top=10&$format=Atom"; //Currently only works for single term queries
		byte[] accountKeyBytes = Base64.getEncoder().encode((ACCOUNTKEY + ":" + ACCOUNTKEY).getBytes());
		String accountKeyEnc = new String(accountKeyBytes);

		URL url = null;
		try {
			url = new URL(bingUrl);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		URLConnection urlConnection = url.openConnection();
		urlConnection.setRequestProperty("Authorization", "Basic " + accountKeyEnc);
				
		InputStream inputStream = (InputStream) urlConnection.getContent();		
		byte[] contentRaw = new byte[urlConnection.getContentLength()];
		inputStream.read(contentRaw);
		String content = new String(contentRaw);
		return content;
	}
	
	private boolean isPrecisionValid(String precision)
	{
		double precisionValue;
		try
		{
			precisionValue = Double.parseDouble(precision);
		}
		catch (NumberFormatException e)
		{
			return false;
		}
		if (precisionValue >= 0 && precisionValue <= 1)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}














