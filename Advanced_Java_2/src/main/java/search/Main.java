package search;

import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

public class Main {

	public static String[] files;
	public static CSVReader reader = null;
	public static CSVParser parser = new CSVParserBuilder().withSeparator('|').build();
	public static FilenameFilter filter = new CsvFile();
	public static Configuration con = new Configuration().configure();
	public static SessionFactory sf = con.buildSessionFactory();

	public static void main(String[] args) {

		/* Loading files for the first time */
		Inputs.fileLoader();

		/*
		 * Checking after every interval if new files are present, if yes, then loading
		 * new files
		 */
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				Boolean test;
				String[] newFiles = new File(Variables.csvPath).list(filter);
				if (!(newFiles.length == 0)) {
					for (String s : newFiles) {
						test = Arrays.asList(files).contains(s);
						if (!test) {
							try {
								reader = new CSVReaderBuilder(new FileReader(Variables.csvPath + "\\" + s))
										.withSkipLines(1).withCSVParser(parser).build();
								String[] nextLine;
								Session session = Main.sf.openSession();
								Transaction tx = session.beginTransaction();
								while ((nextLine = reader.readNext()) != null) {
									session.save(new Product(nextLine));
								}
								tx.commit();
								session.close();
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
					files = newFiles;
				}
			}
		}, 0, Variables.interval);

		/* running the search and printing output */
		ProductSearch.search();
		
		sf.close();
		timer.cancel();
	}

}
