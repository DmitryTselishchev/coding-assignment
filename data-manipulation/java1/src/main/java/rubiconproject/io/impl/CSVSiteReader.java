package rubiconproject.io.impl;

import au.com.bytecode.opencsv.CSVReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rubiconproject.io.SiteReader;
import rubiconproject.model.Site;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * * Implementation of {@link SiteReader} processing CSV files
 */
public class CSVSiteReader implements SiteReader {

    final Logger logger = LoggerFactory.getLogger(CSVSiteReader.class);

    @Override
    public List<Site> readSites(File file) {
        List<Site> sites = null;
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(file));
            sites = new ArrayList<>();
            String[] nextLine = reader.readNext(); // Skip header
            while ((nextLine = reader.readNext()) != null && nextLine.length == 4) {
                Site site = new Site();
                site.setId(nextLine[0]);
                site.setName(nextLine[1]);
                site.setMobile(Boolean.parseBoolean(nextLine[2]));
                site.setScore(Double.parseDouble(nextLine[3]));
                sites.add(site);
            }
        } catch (FileNotFoundException e) {
            logger.error(String.format("Couldn't read sites, file not found: %s", file.getAbsolutePath()),e);
        } catch (IOException e) {
            logger.error(String.format("Couldn't read sites from file %s", file.getAbsolutePath()), e);
        } finally {
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    logger.error(String.format("Couldn't close resource %s", file.getAbsolutePath()), e);
                }
            }
        }
        return sites;
    }
}
