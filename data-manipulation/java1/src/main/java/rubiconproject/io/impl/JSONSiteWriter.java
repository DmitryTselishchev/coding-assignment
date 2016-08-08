package rubiconproject.io.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rubiconproject.io.SiteWriter;
import rubiconproject.model.SiteCollection;

import java.io.*;
import java.util.List;

/**
 * Implementation of {@link SiteWriter} processing JSON files
 */
public class JSONSiteWriter implements SiteWriter {

    final Logger logger = LoggerFactory.getLogger(JSONSiteWriter.class);
    final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void writeSiteCollections(List<SiteCollection> siteCollections, File file) {
        OutputStream outputStream = null;
        Writer outputStreamWriter = null;
        try {
            outputStream = new FileOutputStream(file);
            outputStreamWriter = new OutputStreamWriter(outputStream);
            for (SiteCollection siteCollection : siteCollections) {
                outputStreamWriter.write(mapper.writeValueAsString(siteCollection) + System.getProperty("line.separator"));
            }
            outputStreamWriter.flush();
        } catch (FileNotFoundException e) {
            logger.error(String.format("Couldn't write collections of sites, file not found: %s", file.getAbsolutePath()), e);
        } catch (IOException e) {
            logger.error(String.format("Couldn't write sites in file %s", file.getAbsolutePath()), e);
        } finally {
            if (outputStreamWriter != null){
                try {
                    outputStreamWriter.close();
                } catch (IOException e) {
                    logger.error(String.format("Couldn't close resource %s", file.getAbsolutePath()), e);
                }
            }
        }
    }
}
