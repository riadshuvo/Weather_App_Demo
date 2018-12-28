
package com.example.shuvo.weatherapp.modelclass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Weatherapp {

    @SerializedName("cod")
    @Expose
    private String cod;
    @SerializedName("calctime")
    @Expose
    private Double calctime;
    @SerializedName("cnt")
    @Expose
    private Integer cnt;
    @SerializedName("list")
    @Expose
    private java.util.List<List> list = null;

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public Double getCalctime() {
        return calctime;
    }

    public void setCalctime(Double calctime) {
        this.calctime = calctime;
    }

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public java.util.List<List> getList() {
        return list;
    }

    public void setList(java.util.List<List> list) {
        this.list = list;
    }

}
