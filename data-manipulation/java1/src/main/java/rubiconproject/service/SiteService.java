package rubiconproject.service;

import rubiconproject.model.Site;
import rubiconproject.model.SiteCollection;

import java.io.File;
import java.util.List;

/**
 * Service managing Sites
 */
public interface SiteService {

    /**
     * Fill keywords of sites list
     *
     * @param sites
     */
    void fillKeywords(List<Site> sites);

    /**
     * Write list of collection of sites in file
     *
     * @param siteCollections list of sites collection
     * @param file fill to write
     */
    void writeSiteCollectionsInFile(List<SiteCollection> siteCollections, File file);

    /**
     * Read sites from file, able to process: JSON, CSV
     *
     * @param file
     * @return list of sites
     */
    List<Site> readSitesFromFile(File file);
}
