package ideafx.model.prova.jsoninfos;

import com.google.gson.annotations.SerializedName;

/**
 *
 * @author flo
 */
public class Covers {
   

    @SerializedName("115")
    private String err_115;
    @SerializedName("202")
    private String err_202;
    @SerializedName("230")
    private String err_230;
    @SerializedName("404")
    private String err_404;
    @SerializedName("808")
    private String err_808;
    @SerializedName("original")
    private String original;
    @SerializedName("max_808")
    private String max_808;

    public String getOriginal() {
        return original;
    }

    public String getErr_404() {
        return err_404;
    }
    
}
