package in.techware.lataxi.model;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b#\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u00107\u001a\u000208J\u0006\u00109\u001a\u000208R\u0011\u0010\u0003\u001a\u00020\u00048F\u00a2\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\u00048F\u00a2\u0006\u0006\u001a\u0004\b\b\u0010\u0006R\u0011\u0010\t\u001a\u00020\u00048F\u00a2\u0006\u0006\u001a\u0004\b\n\u0010\u0006R\u0011\u0010\u000b\u001a\u00020\u00048F\u00a2\u0006\u0006\u001a\u0004\b\f\u0010\u0006R\u001a\u0010\r\u001a\u00020\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0014X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\u0014X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0016\"\u0004\b\u001b\u0010\u0018R\u001a\u0010\u001c\u001a\u00020\u0014X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0016\"\u0004\b\u001e\u0010\u0018R\u001a\u0010\u001f\u001a\u00020\u0014X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0016\"\u0004\b!\u0010\u0018R\u001a\u0010\"\u001a\u00020\u0014X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0016\"\u0004\b$\u0010\u0018R\u001a\u0010%\u001a\u00020\u0014X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0016\"\u0004\b\'\u0010\u0018R\u001a\u0010(\u001a\u00020\u0014X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u0016\"\u0004\b*\u0010\u0018R\u001a\u0010+\u001a\u00020\u0014X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\u0016\"\u0004\b-\u0010\u0018R\u001a\u0010.\u001a\u00020\u0014X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\u0016\"\u0004\b0\u0010\u0018R\u001a\u00101\u001a\u00020\u000eX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b2\u0010\u0010\"\u0004\b3\u0010\u0012R\u001a\u00104\u001a\u00020\u0014X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b5\u0010\u0016\"\u0004\b6\u0010\u0018\u00a8\u0006:"}, d2 = {"Lin/techware/lataxi/model/SuccessBean;", "Lin/techware/lataxi/model/BaseBean;", "()V", "dDestinationLatitude", "", "getDDestinationLatitude", "()D", "dDestinationLongitude", "getDDestinationLongitude", "dSourceLatitude", "getDSourceLatitude", "dSourceLongitude", "getDSourceLongitude", "date", "", "getDate", "()J", "setDate", "(J)V", "destination", "", "getDestination", "()Ljava/lang/String;", "setDestination", "(Ljava/lang/String;)V", "destinationLatitude", "getDestinationLatitude", "setDestinationLatitude", "destinationLongitude", "getDestinationLongitude", "setDestinationLongitude", "driverName", "getDriverName", "setDriverName", "driverPhoto", "getDriverPhoto", "setDriverPhoto", "fare", "getFare", "setFare", "source", "getSource", "setSource", "sourceLatitude", "getSourceLatitude", "setSourceLatitude", "sourceLongitude", "getSourceLongitude", "setSourceLongitude", "time", "getTime", "setTime", "tripID", "getTripID", "setTripID", "getDestinationLatLng", "Lcom/google/android/gms/maps/model/LatLng;", "getSourceLatLng", "app_release"})
public final class SuccessBean extends in.techware.lataxi.model.BaseBean {
    @org.jetbrains.annotations.NotNull()
    private java.lang.String tripID;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String driverPhoto;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String driverName;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String fare;
    private long date;
    private long time;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String source;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String destination;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String sourceLatitude;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String sourceLongitude;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String destinationLatitude;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String destinationLongitude;
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTripID() {
        return null;
    }
    
    public final void setTripID(@org.jetbrains.annotations.NotNull()
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
    public final java.lang.String getDriverName() {
        return null;
    }
    
    public final void setDriverName(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFare() {
        return null;
    }
    
    public final void setFare(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final long getDate() {
        return 0L;
    }
    
    public final void setDate(long p0) {
    }
    
    public final long getTime() {
        return 0L;
    }
    
    public final void setTime(long p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSource() {
        return null;
    }
    
    public final void setSource(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDestination() {
        return null;
    }
    
    public final void setDestination(@org.jetbrains.annotations.NotNull()
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
    
    public SuccessBean() {
        super();
    }
}