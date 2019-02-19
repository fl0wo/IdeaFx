package ideafx.model.prova.jsoninfos;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

/**
 *
 * @author flo
 */
public class JsonResult {
    
    @SerializedName("projects")
    private ArrayList<Project> projects; 

    public ArrayList<Project> getProjects() {
        return projects;
    }
    
}
