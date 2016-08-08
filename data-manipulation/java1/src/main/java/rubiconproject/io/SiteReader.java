package rubiconproject.io;

import rubiconproject.model.Site;

import java.io.File;
import java.util.List;

/**
 *  A reader class for Site related objects
 */
public interface SiteReader {

    /**
     * Read sites from file
     * @param file
     * @return list of sites
     */
    List<Site> readSites(File file);
}
