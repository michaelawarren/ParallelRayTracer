package com.playground.playground;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

class Document
{
	private final List<String> lines;

	Document(List<String> lines)
	{
		this.lines = lines;
	}

	List<String> getLines()
	{
		return this.lines;
	}

	static Document fromFile(File file) throws IOException
	{
		List<String> lines = new LinkedList<String>();
		BufferedReader reader = new BufferedReader(new FileReader(file));
		try
		{
			String line = reader.readLine();
			while (line != null)
			{
				lines.add(line);
				line = reader.readLine();
			}
		}
		catch (IOException ex)
		{
			throw ex;
		}
		finally
		{
			reader.close();
		}

		return new Document(lines);
	}
}
