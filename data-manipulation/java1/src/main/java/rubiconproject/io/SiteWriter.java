package rubiconproject.io;

import rubiconproject.model.SiteCollection;

import java.io.File;
import java.util.List;

/**
 * A writer class for Site related objects
 */
public interface SiteWriter {

    /**
     * Write collections of sites to file
     * @param siteCollections list of sites collections
     * @param file
     */
    void writeSiteCollections(List<SiteCollection> siteCollections, File file);
}
