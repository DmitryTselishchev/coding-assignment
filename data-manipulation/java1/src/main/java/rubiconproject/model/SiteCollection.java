package rubiconproject.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Domain class for collection of Sites
 */
public class SiteCollection {

    public SiteCollection(String id, Site[] sites){
        this.id = id;
        this.sites = sites;
    }
    @JsonProperty("collectionId")
    private String id;

    private Site[] sites;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Site[] getSites() {
        return sites;
    }

    public void setSites(Site[] sites) {
        this.sites = sites;
    }
}
