
package khairy.com.souqtask.data.main;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class City implements Serializable{

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("TitleEN")
    @Expose
    private String titleEN;
    @SerializedName("TitleAR")
    @Expose
    private String titleAR;
    @SerializedName("CountryId")
    @Expose
    private Integer countryId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitleEN() {
        return titleEN;
    }

    public void setTitleEN(String titleEN) {
        this.titleEN = titleEN;
    }

    public String getTitleAR() {
        return titleAR;
    }

    public void setTitleAR(String titleAR) {
        this.titleAR = titleAR;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

}
