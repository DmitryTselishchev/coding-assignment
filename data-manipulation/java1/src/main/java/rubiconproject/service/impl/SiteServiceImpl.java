package rubiconproject.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rubiconproject.io.SiteReader;
import rubiconproject.io.SiteWriter;
import rubiconproject.io.impl.CSVSiteReader;
import rubiconproject.io.impl.JSONSiteReader;
import rubiconproject.io.impl.JSONSiteWriter;
import rubiconproject.model.Site;
import rubiconproject.model.SiteCollection;
import rubiconproject.service.KeywordService;
import rubiconproject.service.SiteService;

import java.io.File;
import java.util.Collections;
import java.util.List;

/**
 * Base implementation of {@link SiteService}
 */
public class SiteServiceImpl implements SiteService {

    final Logger logger = LoggerFactory.getLogger(SiteServiceImpl.class);
    private KeywordService keywordService = new KeywordServiceImpl();// ideally should be injected with DI

    @Override
    public void fillKeywords(List<Site> sites) {
        for (Site site: sites){
            site.setKeywords(keywordService.resolveKeywords(site));
        }
    }

    @Override
    public void writeSiteCollectionsInFile(List<SiteCollection> siteCollections, File file) {
        SiteWriter siteWriter = new JSONSiteWriter();
        siteWriter.writeSiteCollections(siteCollections, file);
    }

    @Override
    public List<Site> readSitesFromFile(File file) {
        SiteReader siteReader = getSiteReaderByFileName(file.getName());
        if (siteReader == null) {
            logger.error(String.format("Couldn't process file %s, skipped.", file.getName()));
            return Collections.EMPTY_LIST;
        }
        return siteReader.readSites(file);
    }

    /**
     * Returns special implementation of {@link SiteReader} depends of file dimension
     * @param fileName
     * @return {@link JSONSiteReader} or {@link CSVSiteReader}
     */
    private static SiteReader getSiteReaderByFileName(String fileName) {
        if (fileName.toLowerCase().endsWith(".csv")) {
            return new CSVSiteReader();
        } else if (fileName.toLowerCase().endsWith(".json")) {
            return new JSONSiteReader();
        }
        return null;
    }
}
