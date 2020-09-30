package in.techware.lataxi.model;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0010\u0006\n\u0002\b\u001c\n\u0002\u0010\u0007\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010M\u001a\u00020NJ\u0006\u0010O\u001a\u00020NJ\u0006\u0010P\u001a\u00020NR\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR\u001a\u0010\u0012\u001a\u00020\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\f\"\u0004\b\u0014\u0010\u000eR\u001a\u0010\u0015\u001a\u00020\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\f\"\u0004\b\u0017\u0010\u000eR\u001a\u0010\u0018\u001a\u00020\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\f\"\u0004\b\u001a\u0010\u000eR\u0011\u0010\u001b\u001a\u00020\u001c8F\u00a2\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u001f\u001a\u00020\u001c8F\u00a2\u0006\u0006\u001a\u0004\b \u0010\u001eR\u0011\u0010!\u001a\u00020\u001c8F\u00a2\u0006\u0006\u001a\u0004\b\"\u0010\u001eR\u0011\u0010#\u001a\u00020\u001c8F\u00a2\u0006\u0006\u001a\u0004\b$\u0010\u001eR\u0011\u0010%\u001a\u00020\u001c8F\u00a2\u0006\u0006\u001a\u0004\b&\u0010\u001eR\u0011\u0010\'\u001a\u00020\u001c8F\u00a2\u0006\u0006\u001a\u0004\b(\u0010\u001eR\u001a\u0010)\u001a\u00020\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\f\"\u0004\b+\u0010\u000eR\u001a\u0010,\u001a\u00020\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b-\u0010\f\"\u0004\b.\u0010\u000eR\u001a\u0010/\u001a\u00020\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b0\u0010\f\"\u0004\b1\u0010\u000eR\u001a\u00102\u001a\u00020\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b3\u0010\f\"\u0004\b4\u0010\u000eR\u001a\u00105\u001a\u00020\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b6\u0010\f\"\u0004\b7\u0010\u000eR\u001a\u00108\u001a\u000209X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R\u001a\u0010>\u001a\u00020\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b?\u0010\f\"\u0004\b@\u0010\u000eR\u001a\u0010A\u001a\u00020\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bB\u0010\f\"\u0004\bC\u0010\u000eR\u001a\u0010D\u001a\u00020\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bE\u0010\f\"\u0004\bF\u0010\u000eR\u001a\u0010G\u001a\u00020\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bH\u0010\f\"\u0004\bI\u0010\u000eR\u001a\u0010J\u001a\u00020\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bK\u0010\f\"\u0004\bL\u0010\u000e\u00a8\u0006Q"}, d2 = {"Lin/techware/lataxi/model/DriverBean;", "Lin/techware/lataxi/model/BaseBean;", "()V", "appStatus", "", "getAppStatus", "()I", "setAppStatus", "(I)V", "carLatitude", "", "getCarLatitude", "()Ljava/lang/String;", "setCarLatitude", "(Ljava/lang/String;)V", "carLongitude", "getCarLongitude", "setCarLongitude", "carName", "getCarName", "setCarName", "carNumber", "getCarNumber", "setCarNumber", "carPhoto", "getCarPhoto", "setCarPhoto", "dCarLatitude", "", "getDCarLatitude", "()D", "dCarLongitude", "getDCarLongitude", "dDestinationLatitude", "getDDestinationLatitude", "dDestinationLongitude", "getDDestinationLongitude", "dSourceLatitude", "getDSourceLatitude", "dSourceLongitude", "getDSourceLongitude", "destinationLatitude", "getDestinationLatitude", "setDestinationLatitude", "destinationLongitude", "getDestinationLongitude", "setDestinationLongitude", "driverName", "getDriverName", "setDriverName", "driverNumber", "getDriverNumber", "setDriverNumber", "driverPhoto", "getDriverPhoto", "setDriverPhoto", "rating", "", "getRating", "()F", "setRating", "(F)V", "requestStatus", "getRequestStatus", "setRequestStatus", "sourceLatitude", "getSourceLatitude", "setSourceLatitude", "sourceLongitude", "getSourceLongitude", "setSourceLongitude", "time", "getTime", "setTime", "tripID", "getTripID", "setTripID", "getCarLatLng", "Lcom/google/android/gms/maps/model/LatLng;", "getDestinationLatLng", "getSourceLatLng", "app_debug"})
public final class DriverBean extends in.techware.lataxi.model.BaseBean {
    @org.jetbrains.annotations.NotNull()
    private java.lang.String tripID;
    private int appStatus;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String requestStatus;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String driverName;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String driverPhoto;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String driverNumber;
    private float rating;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String carName;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String carNumber;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String time;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String carPhoto;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String sourceLatitude;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String sourceLongitude;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String destinationLatitude;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String destinationLongitude;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String carLatitude;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String carLongitude;
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTripID() {
        return null;
    }
    
    public final void setTripID(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final int getAppStatus() {
        return 0;
    }
    
    public final void setAppStatus(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRequestStatus() {
        return null;
    }
    
    public final void setRequestStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDriverName() {
        return null;
    }
    
    public final void setDriverName(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDriverPhoto() {
        return null;
    }
    
    public final void setDriverPhoto(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDriverNumber() {
        return null;
    }
    
    public final void setDriverNumber(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final float getRating() {
        return 0.0F;
    }
    
    public final void setRating(float p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCarName() {
        return null;
    }
    
    public final void setCarName(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCarNumber() {
        return null;
    }
    
    public final void setCarNumber(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTime() {
        return null;
    }
    
    public final void setTime(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCarPhoto() {
        return null;
    }
    
    public final void setCarPhoto(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSourceLatitude() {
        return null;
    }
    
    public final void setSourceLatitude(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSourceLongitude() {
        return null;
    }
    
    public final void setSourceLongitude(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.android.gms.maps.model.LatLng getSourceLatLng() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDestinationLatitude() {
        return null;
    }
    
    public final void setDestinationLatitude(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDestinationLongitude() {
        return null;
    }
    
    public final void setDestinationLongitude(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.android.gms.maps.model.LatLng getDestinationLatLng() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCarLatitude() {
        return null;
    }
    
    public final void setCarLatitude(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCarLongitude() {
        return null;
    }
    
    public final void setCarLongitude(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.android.gms.maps.model.LatLng getCarLatLng() {
        return null;
    }
    
    public final double getDSourceLatitude() {
        return 0.0;
    }
    
    public final double getDSourceLongitude() {
        return 0.0;
    }
    
    public final double getDDestinationLatitude() {
        return 0.0;
    }
    
    public final double getDDestinationLongitude() {
        return 0.0;
    }
    
    public final double getDCarLatitude() {
        return 0.0;
    }
    
    public final double getDCarLongitude() {
        return 0.0;
    }
    
    public DriverBean() {
        super();
    }
}