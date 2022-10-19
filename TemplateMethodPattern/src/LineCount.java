import java.io.*;
import java.util.*;
import java.util.regex.*;

public class LineCount extends TemplateSearch{
	private String _directory;
	private String _pattern;
	private boolean _recurse;
	private int _totalLineCount;
	private Matcher _matcher;
	
	public LineCount(String directory, String pattern, boolean recurse) {
		_directory = directory;
		_pattern = pattern;
		_recurse = recurse;
		_totalLineCount = 0;
		_matcher = Pattern.compile(_pattern).matcher("");		
	}

	private void run() { super.runSearch(new File(_directory)); }

	@Override
	protected boolean doIsDirectory(File dir) {
		if (dir.isDirectory()) { return true; }
		else {
			System.out.println(dir + " is not a directory");
			return false;
		}
	}

	@Override
	protected boolean doCanRead(File dir) {
		if (dir.canRead()) { return true; }
		else {
			System.out.println("Directory " + dir + " is unreadable");
			return false;
		}
	}

	@Override
	protected void doSearchFile(File file) { countPattern(file, _matcher); }

	@Override
	protected void doUnreadableFile(File file) { System.out.println("File " + file + " is unreadable"); }

	@Override
	protected boolean doRecurse() { return _recurse; }

	@Override
	protected void doPrintResults() { System.out.println("TOTAL: " + _totalLineCount); }

	@Override
	protected Scanner doCreateScanner(File file) throws FileNotFoundException {
		Reader reader = new BufferedReader(new FileReader(file));
		return new Scanner(reader);
	}

	@Override
	protected int doFindAndCount(int count, String line, File file) {
		++count;
		++_totalLineCount;
		return count;
	}

	@Override
	protected void doPrintCount(int count, File file) { System.out.println(count + "  " + file); }
	
	public static void main(String[] args) {
		String directory = "";
		String pattern = "";
		boolean recurse = false;
		
		if (args.length == 2) {
			recurse = false;
			directory = args[0];
			pattern = args[1];
		}
		else if (args.length == 3 && args[0].equals("-r")) {
			recurse = true;
			directory = args[1];
			pattern = args[2];
		}
		else {
			usage();
			return;
		}

		LineCount lineCounter = new LineCount(directory, pattern, recurse);
		lineCounter.run();
	}
	
	private static void usage() { System.out.println("USAGE: java LineCount {-r} <dir> <file-pattern>"); }
}
