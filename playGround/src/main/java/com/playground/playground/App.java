package com.playground.playground;

import java.io.File;

/**
 * Hello world!
 *
 */
public class App
{
	public static void main(String[] args)
	{
		try
		{
			WordCounter wordCounter = new WordCounter();
			Folder folder = Folder.fromDirectory(new File(args[0]));
			System.out.println(wordCounter.countOccurrencesInParallel(folder, args[1]));
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
		}

	}
}
