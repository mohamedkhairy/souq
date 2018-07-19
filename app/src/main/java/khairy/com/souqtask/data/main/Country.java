
package khairy.com.souqtask.data.main;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Country implements Serializable {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("TitleEN")
    @Expose
    private String titleEN;
    @SerializedName("TitleAR")
    @Expose
    private String titleAR;
    @SerializedName("CurrencyId")
    @Expose
    private Integer currencyId;
    @SerializedName("CurrencyEN")
    @Expose
    private String currencyEN;
    @SerializedName("CurrencyAR")
    @Expose
    private String currencyAR;
    @SerializedName("CodeEN")
    @Expose
    private String codeEN;
    @SerializedName("CodeAR")
    @Expose
    private String codeAR;
    @SerializedName("Code")
    @Expose
    private String code;

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

    public Integer getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(Integer currencyId) {
        this.currencyId = currencyId;
    }

    public String getCurrencyEN() {
        return currencyEN;
    }

    public void setCurrencyEN(String currencyEN) {
        this.currencyEN = currencyEN;
    }

    public String getCurrencyAR() {
        return currencyAR;
    }

    public void setCurrencyAR(String currencyAR) {
        this.currencyAR = currencyAR;
    }

    public String getCodeEN() {
        return codeEN;
    }

    public void setCodeEN(String codeEN) {
        this.codeEN = codeEN;
    }

    public String getCodeAR() {
        return codeAR;
    }

    public void setCodeAR(String codeAR) {
        this.codeAR = codeAR;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
