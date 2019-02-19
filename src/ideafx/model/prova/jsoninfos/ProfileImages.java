package ideafx.model.prova.jsoninfos;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author flo
 */
public class ProfileImages {
    
    public static final int DIMENSIONE_50 = 50;
    public static final int DIMENSIONE_100 = 100;
    public static final int DIMENSIONE_115 = 115;
    public static final int DIMENSIONE_138 = 138;
    public static final int DIMENSIONE_230 = 230;
    
    
    @SerializedName("50")
    private String err_50;
    @SerializedName("100")
    private String err_100;
    @SerializedName("115")
    private String err_115;
    @SerializedName("138")
    private String err_138;
    @SerializedName("230")
    private String err_230;
    @SerializedName("276")
    private String err_276;

    public String getErr_50() {
        return err_50;
    }

    public String getErr_100() {
        return err_100;
    }

    public String getErr_115() {
        return err_115;
    }

    public String getErr_138() {
        return err_138;
    }

    public String getErr_230() {
        return err_230;
    }

    public String getErr_276() {
        return err_276;
    }
        
    
}
