package ideafx.model.prova.jsoninfos;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import javafx.scene.image.Image;

/**
 *
 * @author flo
 */
public class Owner {
    
    private int id;
    
    @SerializedName("first_name")
    private String firstName;
    
    @SerializedName("last_name")
    private String lastName;
    
    @SerializedName("username")
    private String username;
    
    @SerializedName("city")
    private String city;
    
    @SerializedName("state")
    private String state;
    
    @SerializedName("country")
    private String country;
    
    @SerializedName("location")
    private String location;
    
    @SerializedName("company")
    private String company;
    
    @SerializedName("occupation")
    private String occupation;
    
    @SerializedName("created_on")
    private int createdOn;
    
    private String url;
    
    private ProfileImages images;
    
    @SerializedName("display_name")
    private String displayName;
    
    private ArrayList<String> fields;
    
    @SerializedName("has_default_image")
    private int hasDefaultImage;
    
    private String webSite;
    
    private OwnerStats stats;

    @Override
    public String toString() {
        return "Owner{" + "id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", username=" + username + ", city=" + city + ", state=" + state + ", country=" + country + ", location=" + location + ", company=" + company + ", occupation=" + occupation + ", createdOn=" + createdOn + ", url=" + url + ", images=" + images + ", displayName=" + displayName + ", fields=" + fields + ", hasDefaultImage=" + hasDefaultImage + ", webSite=" + webSite + ", stats=" + stats + '}';
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getCountry() {
        return country;
    }

    public String getLocation() {
        return location;
    }

    public String getCompany() {
        return company;
    }

    public String getOccupation() {
        return occupation;
    }

    public int getCreatedOn() {
        return createdOn;
    }

    public String getUrl() {
        return url;
    }

    public ProfileImages getImages() {
        return images;
    }

    public String getDisplayName() {
        return displayName;
    }

    public ArrayList<String> getFields() {
        return fields;
    }

    public int getHasDefaultImage() {
        return hasDefaultImage;
    }

    public String getWebSite() {
        return webSite;
    }

    public OwnerStats getStats() {
        return stats;
    }
    
    private Image image;
    
    public void setLinkedImage(Image image){
        this.image = image;
    }

    public Image getImage() {
        return image;
    }
    
    
    
}
