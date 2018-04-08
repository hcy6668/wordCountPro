package wordTest;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCount {
	public static void main(String[] args) {
		String aString = "﻿softwarecontent-based content-basedLet’s Let’s Let’snight- night- “I TABLE1-2 TABLE1-2(see Box 3–2).8885d_c01_016";
		String bString = "123xas- Mitchell _H";
		String cString = "";

		System.out.println(TxtFile.handle2(aString).toString());
		System.out.println(TxtFile.handle2(bString).toString());
		System.out.println(TxtFile.handle2(cString).toString());
	}
}

class TxtFile {
	private final String path;

	TxtFile(String p) {
		this.path = p;
	}

	String input() {
		BufferedReader reader = null;
		String result = "";
		try {
			StringBuilder sb = new StringBuilder();
			reader = new BufferedReader(new FileReader(new File(path)));
			String mid = "";
			while ((mid = reader.readLine()) != null) {
				sb.append(mid + "\n");
				mid = null;
			}
			result = sb.toString();
		} catch (Exception e) {
			System.out.println("Error path name!");
		}
		return result;
	}

	ArrayList<WordInfo> handle(String handleString) {
		ArrayList<WordInfo> result = new ArrayList<WordInfo>();
		Pattern pattern = Pattern.compile("[a-zA-Z]+\\-?[a-zA-Z]*");
		Matcher matcher = pattern.matcher(handleString);
		while (matcher.find()) {
			String word = matcher.group().toLowerCase();
			if (word.lastIndexOf("-") == (word.length() - 1)) {
				word = word.substring(0, word.length() - 1);
			}
			WordInfo wordInfo = new WordInfo(word);
			int index = result.indexOf(wordInfo);
			if (index == -1) {
				result.add(wordInfo);
			} else {
				result.get(index).count();
			}
		}
		Collections.sort(result);
		return result;
	}

	static LinkedList<WordInfo> handle2(String handleString) {
		LinkedList<WordInfo> result = new LinkedList<WordInfo>();
		Pattern pattern = Pattern.compile("[a-zA-Z]+\\-?[a-zA-Z]*");
		Matcher matcher = pattern.matcher(handleString);
		while (matcher.find()) {
			String word = matcher.group().toLowerCase();
			if (word.lastIndexOf("-") == (word.length() - 1)) {
				word = word.substring(0, word.length() - 1);
			}
			WordInfo wordInfo = new WordInfo(word);
			int index = result.indexOf(wordInfo);
			if (index == -1) {
				result.add(wordInfo);
			} else {
				result.get(index).count();
			}
		}
		Collections.sort(result);
		return result;
	}

	void output2(LinkedList<WordInfo> handleList) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new BufferedOutputStream(
					new FileOutputStream("result.txt")));
			for (WordInfo wordInfo : handleList) {
				writer.write(wordInfo.toString() + "\r\n");
			}
			writer.flush();
		} catch (Exception e) {
			System.out.println("Error in output");
		} finally {
			writer.close();
		}
	}

	void output(ArrayList<WordInfo> handleList) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new BufferedOutputStream(
					new FileOutputStream("result.txt")));
			for (WordInfo wordInfo : handleList) {
				writer.write(wordInfo.toString() + "\r\n");
			}
			writer.flush();
		} catch (Exception e) {
			System.out.println("Error in output");
		} finally {
			writer.close();
		}
	}
}

class WordInfo implements Comparable<WordInfo> {
	String word;
	int num;

	public WordInfo(String word) {
		this.word = word;
		num = 1;
	}

	void count() {
		num++;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof WordInfo) {
			if (word.equals(((WordInfo) o).word)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int compareTo(WordInfo o) {
		if (num < o.num) {
			return 1;
		} else if (num > o.num) {
			return -1;
		} else if (word.compareTo(o.word) < 0) {
			return -1;
		} else {
			return 1;
		}
	}

	@Override
	public String toString() {
		return word + " " + num;
	}
}