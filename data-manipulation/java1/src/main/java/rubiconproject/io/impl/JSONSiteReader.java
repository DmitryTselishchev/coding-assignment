package rubiconproject.io.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rubiconproject.io.SiteReader;
import rubiconproject.model.Site;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Implementation of {@link SiteReader} processing JSON files
 */
public class JSONSiteReader implements SiteReader {

    final Logger logger = LoggerFactory.getLogger(JSONSiteReader.class);
    final ObjectMapper mapper = new ObjectMapper();

    @Override
    public List<Site> readSites(File file) {
        List<Site> sites = null;
        try {
            sites = mapper.readValue(file, new TypeReference<List<Site>>(){});
        } catch (FileNotFoundException e) {
            logger.error(String.format("Couldn't read sites, file not found: %s", file.getAbsolutePath()),e);
        } catch (IOException e) {
            logger.error(String.format("Couldn't read sites from file %s", file.getAbsolutePath()), e);
        }
        return sites;
    }
}
