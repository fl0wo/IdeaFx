package ideafx.model.prova.jsoninfos;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import javafx.scene.image.Image;

/**
 *
 * @author flo
 */

public class Project {
    
    @SerializedName("id")
    private int id;
    
    @SerializedName("name")
    private String name;
    
    @SerializedName("published_on")
    private int publishedOn;
    
    @SerializedName("created_on")
    private int createdOn;
    
    @SerializedName("modified_on")
    private int modifiedOn;
    
    @SerializedName("url")
    private String url;
    
    @SerializedName("slug")
    private String slug;
    
    @SerializedName("privacy")
    private String privacy;
    
    @SerializedName("fields")
    private ArrayList<String> fields;
    
    @SerializedName("covers")
    private Covers covers;

    @SerializedName("mature_content")
    private int matureContent;
    
    @SerializedName("mature_access")
    private String matureAccess;
    
    @SerializedName("owners")
    private ArrayList<Owner> owners;
    
    private ProjectStats stats;
    
    @SerializedName("conceived_on")
    private int conceivedOn;
    
    private ArrayList<Feature> features;
    
    private ArrayList<ProjectColor> colors;

    @Override
    public String toString() {
        return "Project{" + "id=" + id + ", name=" + name + ", publishedOn=" + publishedOn + ", createdOn=" + createdOn + ", modifiedOn=" + modifiedOn + ", url=" + url + ", slug=" + slug + ", privacy=" + privacy + ", fields=" + fields + ", covers=" + covers + ", matureContent=" + matureContent + ", matureAccess=" + matureAccess + ", owners=" + owners + ", stats=" + stats + ", conceivedOn=" + conceivedOn + ", features=" + features + ", colors=" + colors + '}';
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPublishedOn() {
        return publishedOn;
    }

    public int getCreatedOn() {
        return createdOn;
    }

    public int getModifiedOn() {
        return modifiedOn;
    }

    public String getUrl() {
        return url;
    }

    public String getSlug() {
        return slug;
    }

    public String getPrivacy() {
        return privacy;
    }

    public ArrayList<String> getFields() {
        return fields;
    }

    public Covers getCovers() {
        return covers;
    }

    public int getMatureContent() {
        return matureContent;
    }

    public String getMatureAccess() {
        return matureAccess;
    }

    public ArrayList<Owner> getOwners() {
        return owners;
    }

    public ProjectStats getStats() {
        return stats;
    }

    public int getConceivedOn() {
        return conceivedOn;
    }

    public ArrayList<Feature> getFeatures() {
        return features;
    }

    public ArrayList<ProjectColor> getColors() {
        return colors;
    }
    
    private Image image;

    public void setLinkedImage(Image image) {
        this.image = image;
    }

    public Image getLinkedImage() {
        return image;
    }
    
    
    
    
    
    
    
}
