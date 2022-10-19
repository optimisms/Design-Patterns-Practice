import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;

public abstract class TemplateSearch {
    private String dir;

    public void runSearch(File dir) {
        //check is directory
        //check can read
        if (doIsDirectory(dir) && doCanRead(dir)) {
            for (File file : dir.listFiles()) {
                if (file.isFile()) {
                    if (file.canRead()) { doSearchFile(file); }
                    else { doUnreadableFile(file); }
                }
            }

            if (doRecurse())
            {
                for (File file : dir.listFiles()) {
                    if (file.isDirectory()) {
                        runSearch(file);
                    }
                }
            }
        }
        doPrintResults();
    }

    public void countPattern(File file, Matcher matcher) {
        String fileName = "";
        try { fileName = file.getCanonicalPath(); } catch (IOException ignored) { }
        matcher.reset(fileName);
        if (matcher.find()) {
            try
            {
                int count = 0;
                Scanner input = doCreateScanner(file);
                try {
                    while (input.hasNextLine()) {
                    count = doFindAndCount(count, input.nextLine(), file);
                    }
                } finally {
                    doPrintCount(count, file);
                    input.close();
                }
            } catch (IOException e) { doUnreadableFile(file); }

            //doSomething
        }
    }

    protected abstract boolean doIsDirectory(File dir);
    protected abstract boolean doCanRead(File dir);
    protected abstract void doSearchFile(File file);
    protected abstract void doUnreadableFile(File file);
    protected abstract boolean doRecurse();
    protected abstract void doPrintResults();
    protected abstract Scanner doCreateScanner(File file) throws FileNotFoundException;
    protected abstract int doFindAndCount(int count, String line, File file);
    protected abstract void doPrintCount(int count, File file);
}
