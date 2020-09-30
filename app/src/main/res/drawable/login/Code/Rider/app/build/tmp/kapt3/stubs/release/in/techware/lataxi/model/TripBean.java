package in.techware.lataxi.model;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b!\n\u0002\u0010\t\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0011\u00105\u001a\u0002062\u0006\u00107\u001a\u00020\u0000H\u0096\u0002J\u0006\u00108\u001a\u000209J\u0006\u0010:\u001a\u000209R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u000b8F\u00a2\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u000b8F\u00a2\u0006\u0006\u001a\u0004\b\u000f\u0010\rR\u0011\u0010\u0010\u001a\u00020\u000b8F\u00a2\u0006\u0006\u001a\u0004\b\u0011\u0010\rR\u0011\u0010\u0012\u001a\u00020\u000b8F\u00a2\u0006\u0006\u001a\u0004\b\u0013\u0010\rR\u001a\u0010\u0014\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0007\"\u0004\b\u0016\u0010\tR\u001a\u0010\u0017\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0007\"\u0004\b\u0019\u0010\tR\u001a\u0010\u001a\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0007\"\u0004\b\u001c\u0010\tR\u001a\u0010\u001d\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0007\"\u0004\b\u001f\u0010\tR\u001a\u0010 \u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0007\"\u0004\b\"\u0010\tR\u001a\u0010#\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0007\"\u0004\b%\u0010\tR\u001a\u0010&\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\'\u0010\u0007\"\u0004\b(\u0010\tR\u001a\u0010)\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u0007\"\u0004\b+\u0010\tR\u001a\u0010,\u001a\u00020-X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b.\u0010/\"\u0004\b0\u00101R\u001a\u00102\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b3\u0010\u0007\"\u0004\b4\u0010\t\u00a8\u0006;"}, d2 = {"Lin/techware/lataxi/model/TripBean;", "Lin/techware/lataxi/model/BaseBean;", "", "()V", "carName", "", "getCarName", "()Ljava/lang/String;", "setCarName", "(Ljava/lang/String;)V", "dDestinationLatitude", "", "getDDestinationLatitude", "()D", "dDestinationLongitude", "getDDestinationLongitude", "dSourceLatitude", "getDSourceLatitude", "dSourceLongitude", "getDSourceLongitude", "date", "getDate", "setDate", "destinationLatitude", "getDestinationLatitude", "setDestinationLatitude", "destinationLongitude", "getDestinationLongitude", "setDestinationLongitude", "driverPhoto", "getDriverPhoto", "setDriverPhoto", "id", "getId", "setId", "rate", "getRate", "setRate", "sourceLatitude", "getSourceLatitude", "setSourceLatitude", "sourceLongitude", "getSourceLongitude", "setSourceLongitude", "time", "", "getTime", "()J", "setTime", "(J)V", "tripStatus", "getTripStatus", "setTripStatus", "compareTo", "", "other", "getDestinationLatLng", "Lcom/google/android/gms/maps/model/LatLng;", "getSourceLatLng", "app_release"})
public final class TripBean extends in.techware.lataxi.model.BaseBean implements java.lang.Comparable<in.techware.lataxi.model.TripBean> {
    @org.jetbrains.annotations.NotNull()
    private java.lang.String id;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String tripStatus;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String date;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String carName;
    private long time;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String rate;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String driverPhoto;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String sourceLatitude;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String sourceLongitude;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String destinationLatitude;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String destinationLongitude;
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getId() {
        return null;
    }
    
    public final void setId(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTripStatus() {
        return null;
    }
    
    public final void setTripStatus(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDate() {
        return null;
    }
    
    public final void setDate(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getCarName() {
        return null;
    }
    
    public final void setCarName(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final long getTime() {
        return 0L;
    }
    
    public final void setTime(long p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getRate() {
        return null;
    }
    
    public final void setRate(@org.jetbrains.annotations.NotNull()
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
    public final com.google.android.gms.maps.model.LatLng getSourceLatLng() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.android.gms.maps.model.LatLng getDestinationLatLng() {
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
    
    @java.lang.Override()
    public int compareTo(@org.jetbrains.annotations.NotNull()
    in.techware.lataxi.model.TripBean other) {
        return 0;
    }
    
    public TripBean() {
        super();
    }
}