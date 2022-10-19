import java.io.*;
import java.util.*;
import java.util.regex.*;

public class FileSearch extends TemplateSearch {

	private String _dirName;
	private String _filePattern;
	private boolean _recurse;
	private Matcher _fileMatcher;
	private Matcher _searchMatcher;
	private int _totalMatches;
	
	public FileSearch(String dirName, String filePattern, String searchPattern, boolean recurse) {
		_dirName = dirName;
		_filePattern = filePattern;
		_recurse = recurse;
		_fileMatcher = Pattern.compile(_filePattern).matcher("");
		_searchMatcher = Pattern.compile(searchPattern).matcher("");
		_totalMatches = 0;
		
		super.runSearch(new File(_dirName));
	}

	@Override
	protected boolean doIsDirectory(File dir) {
		if (dir.isDirectory()) { return true; }
		else {
			nonDir(dir);
			return false;
		}
	}

	@Override
	protected boolean doCanRead(File dir) {
		if (dir.canRead()) { return true; }
		else {
			unreadableDir(dir);
			return false;
		}
	}

	@Override
	protected void doSearchFile(File file) { countPattern(file, _fileMatcher); }

	@Override
	protected void doUnreadableFile(File file) { unreadableFile(file);}

	@Override
	protected boolean doRecurse() { return _recurse; }

	@Override
	protected void doPrintResults() {
		System.out.println();
		System.out.println("TOTAL MATCHES: " + _totalMatches);
	}

	@Override
	protected Scanner doCreateScanner(File file) throws FileNotFoundException {
		InputStream data = new BufferedInputStream(new FileInputStream(file));
		return new Scanner(data);
	}

	@Override
	protected int doFindAndCount(int count, String line, File file) {
		_searchMatcher.reset(line);
		if (_searchMatcher.find()) {
			if (++count == 1) {
				System.out.println();
				System.out.println("FILE: " + file);
			}

			System.out.println(line);
			++_totalMatches;
		}
		return count;
	}

	@Override
	protected void doPrintCount(int count, File file) {
		if (count > 0) {
			System.out.println("MATCHES: " + count);
		}
	}
	
	private void nonDir(File dir) { System.out.println(dir + " is not a directory"); }
	
	private void unreadableDir(File dir) { System.out.println("Directory " + dir + " is unreadable"); }
	
	private void unreadableFile(File file) { System.out.println("File " + file + " is unreadable"); }
	
	public static void main(String[] args) {
		
		String dirName = "";
		String filePattern = "";
		String searchPattern = "";
		boolean recurse = false;
		
		if (args.length == 3) {
			recurse = false;
			dirName = args[0];
			filePattern = args[1];
			searchPattern = args[2];
		}
		else if (args.length == 4 && args[0].equals("-r")) {
			recurse = true;
			dirName = args[1];
			filePattern = args[2];
			searchPattern = args[3];
		}
		else {
			usage();
			return;
		}
		
		new FileSearch(dirName, filePattern, searchPattern, recurse);
	}
	
	private static void usage() { System.out.println("USAGE: java FileSearch {-r} <dir> <file-pattern> <search-pattern>"); }
}
