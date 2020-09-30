package in.techware.lataxi.model;

import java.lang.System;

/**
 * * Created by Jemsheer K D on 24 April, 2017.
 * * Package in.techware.lataxidriver.model
 * * Project LaTaxiDriver
 */
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\n\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0011\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u0000H\u0096\u0002R\u001e\u0010\u0004\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001e\u0010\n\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\tR\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001e\u0010\u0013\u001a\u00020\u00058\u0006@\u0006X\u0087\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0007\"\u0004\b\u0015\u0010\t\u00a8\u0006\u0018"}, d2 = {"Lin/techware/lataxi/model/CountryBean;", "Lin/techware/lataxi/model/BaseBean;", "", "()V", "countryCode", "", "getCountryCode", "()Ljava/lang/String;", "setCountryCode", "(Ljava/lang/String;)V", "dialCode", "getDialCode", "setDialCode", "id", "", "getId", "()I", "setId", "(I)V", "name", "getName", "setName", "compareTo", "other", "app_debug"})
public final class CountryBean extends in.techware.lataxi.model.BaseBean implements java.lang.Comparable<in.techware.lataxi.model.CountryBean> {
    private int id;
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "name")
    private java.lang.String name;
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "dial_code")
    private java.lang.String dialCode;
    @org.jetbrains.annotations.NotNull()
    @com.google.gson.annotations.SerializedName(value = "code")
    private java.lang.String countryCode;
    
    public final int getId() {
        return 0;
    }
    
    public final void setId(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getName() {
        return null;
    }
    
    public final void setName(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDialCode() {
        return null;
    }
    
    public final void setDialCode(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCountryCode() {
        return null;
    }
    
    public final void setCountryCode(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @java.lang.Override()
    public int compareTo(@org.jetbrains.annotations.NotNull()
    in.techware.lataxi.model.CountryBean other) {
        return 0;
    }
    
    public CountryBean() {
        super();
    }
}