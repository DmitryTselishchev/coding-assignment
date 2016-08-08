package rubiconproject.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import rubiconproject.model.Site;
import rubiconproject.service.impl.SiteServiceImpl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Test class for {@link SiteServiceImpl}
 */
public class SiteServiceImplTest {

    private static final String INPUT_RESOURCES_FOLDER = "src/test/data/input/";
    private SiteService siteService;

    @Before
    public void setUp(){
        siteService = new SiteServiceImpl();
    }

    @After
    public void tearDown(){
        siteService = null;
    }

    @Test
    public void fillKeywordsShouldSetKeywords(){
        List<Site> sites = new ArrayList<>();
        sites.add(new Site());
        siteService.fillKeywords(sites);
        Assert.assertNotNull(sites.get(0).getKeywords());
    }

    @Test
    public void readSitesFromFileShouldReturnEmptyListIfFileIsNotRecognized(){
        List<Site> sites = siteService.readSitesFromFile(new File("file.xml"));
        Assert.assertTrue(sites.isEmpty());
    }

    @Test
    public void readSitesFromFileShouldReadSitesFromJsonFile() throws Exception {
        List<Site> sites = siteService.readSitesFromFile(new File(INPUT_RESOURCES_FOLDER + "input2.json"));
        Assert.assertEquals(sites.size(), 3);
        assertSiteFieldsAreFilled(sites);
    }

    @Test
    public void readSitesFromFileShouldReadSitesFromCSVFile() throws Exception {
        System.out.println(new File(INPUT_RESOURCES_FOLDER + "input1.csv").getAbsolutePath());
        List<Site> sites = siteService.readSitesFromFile(new File(INPUT_RESOURCES_FOLDER + "input1.csv"));
        Assert.assertEquals(sites.size(), 3);
        assertSiteFieldsAreFilled(sites);
    }

    @Test
    public void readSitesFromFileShouldReturnNullIfFileIsNotExistsWhenProcessCSV(){
        List<Site> sites = siteService.readSitesFromFile(new File(INPUT_RESOURCES_FOLDER + "fake.csv"));
        Assert.assertNull(sites);
    }

    @Test
    public void readSitesFromFileShouldReturnNullIfFileIsNotExistsWhenProcessJSON(){
        List<Site> sites = siteService.readSitesFromFile(new File(INPUT_RESOURCES_FOLDER + "fake.json"));
        Assert.assertNull(sites);
    }

    private void assertSiteFieldsAreFilled(List<Site> sites){
        Assert.assertEquals(sites.size(), 3);
        for (Site site : sites) {
            Assert.assertNotNull(site.getId());
            Assert.assertNotNull(site.getName());
            Assert.assertNotNull(site.getScore());
            Assert.assertNotNull(site.isMobile());
        }
    }
}
