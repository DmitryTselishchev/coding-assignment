package rubiconproject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rubiconproject.model.Site;
import rubiconproject.model.SiteCollection;
import rubiconproject.service.SiteService;
import rubiconproject.service.impl.SiteServiceImpl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Class runner contains main method
 */
public class Main {

    final static Logger logger = LoggerFactory.getLogger(Main.class);

    /**
     * Runs the application with parameters
     *
     * @param args <pathToDirectory> <outputFile>
     */
    public static void main(String... args) {
        if (!checkInputArgs(args)) {
            logger.error("Input arguments are not correct.");
            return;
        }
        final String pathToDirectory = args[0];
        final String outputFile = args[1];
        File[] files = new File(pathToDirectory).listFiles();
        List<SiteCollection> siteCollections = new ArrayList<>(files.length);
        SiteService siteService = new SiteServiceImpl();
        for (File file : files) {
            List<Site> sites = siteService.readSitesFromFile(file);
            siteService.fillKeywords(sites);
            SiteCollection siteCollection = new SiteCollection(file.getName(), sites.toArray(new Site[sites.size()]));
            siteCollections.add(siteCollection);
        }
        siteService.writeSiteCollectionsInFile(siteCollections, new File(outputFile));
    }


    private static boolean checkInputArgs(String... args) {
        if (args.length == 2 && new File(args[0]).isDirectory() && new File(args[0]).canRead()) {
            return true;
        }
        return false;
    }
}
