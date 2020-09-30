package in.techware.lataxi.model;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\'\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0007\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010V\u001a\u00020WJ\u0006\u0010X\u001a\u00020WR\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n8F\u00a2\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\r\u001a\u00020\n8F\u00a2\u0006\u0006\u001a\u0004\b\u000e\u0010\fR\u0011\u0010\u000f\u001a\u00020\n8F\u00a2\u0006\u0006\u001a\u0004\b\u0010\u0010\fR\u0011\u0010\u0011\u001a\u00020\n8F\u00a2\u0006\u0006\u001a\u0004\b\u0012\u0010\fR\u001a\u0010\u0013\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0006\"\u0004\b\u0015\u0010\bR\u001a\u0010\u0016\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0006\"\u0004\b\u0018\u0010\bR\u001a\u0010\u0019\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0006\"\u0004\b\u001b\u0010\bR\u001a\u0010\u001c\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0006\"\u0004\b\u001e\u0010\bR\u001a\u0010\u001f\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0006\"\u0004\b!\u0010\bR\u001a\u0010\"\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0006\"\u0004\b$\u0010\bR\u001a\u0010%\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0006\"\u0004\b\'\u0010\bR\u001a\u0010(\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u0006\"\u0004\b*\u0010\bR\u001a\u0010+\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\u0006\"\u0004\b-\u0010\bR\u001a\u0010.\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\u0006\"\u0004\b0\u0010\bR \u00101\u001a\b\u0012\u0004\u0012\u00020302X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b4\u00105\"\u0004\b6\u00107R\u001a\u00108\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b9\u0010\u0006\"\u0004\b:\u0010\bR\u001a\u0010;\u001a\u00020<X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b=\u0010>\"\u0004\b?\u0010@R\u001a\u0010A\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bB\u0010\u0006\"\u0004\bC\u0010\bR\u001a\u0010D\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bE\u0010\u0006\"\u0004\bF\u0010\bR\u001a\u0010G\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bH\u0010\u0006\"\u0004\bI\u0010\bR\u001a\u0010J\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bK\u0010\u0006\"\u0004\bL\u0010\bR\u001a\u0010M\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bN\u0010\u0006\"\u0004\bO\u0010\bR\u001a\u0010P\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bQ\u0010\u0006\"\u0004\bR\u0010\bR\u001a\u0010S\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\bT\u0010\u0006\"\u0004\bU\u0010\b\u00a8\u0006Y"}, d2 = {"Lin/techware/lataxi/model/TripDetailsBean;", "Lin/techware/lataxi/model/BaseBean;", "()V", "baseFare", "", "getBaseFare", "()Ljava/lang/String;", "setBaseFare", "(Ljava/lang/String;)V", "dDestinationLatitude", "", "getDDestinationLatitude", "()D", "dDestinationLongitude", "getDDestinationLongitude", "dSourceLatitude", "getDSourceLatitude", "dSourceLongitude", "getDSourceLongitude", "destinationLatitude", "getDestinationLatitude", "setDestinationLatitude", "destinationLongitude", "getDestinationLongitude", "setDestinationLongitude", "destinationName", "getDestinationName", "setDestinationName", "driverName", "getDriverName", "setDriverName", "driverPhoto", "getDriverPhoto", "setDriverPhoto", "id", "getId", "setId", "kilometer", "getKilometer", "setKilometer", "kilometerFare", "getKilometerFare", "setKilometerFare", "minute", "getMinute", "setMinute", "minutesFare", "getMinutesFare", "setMinutesFare", "path", "", "Lin/techware/lataxi/model/PlaceBean;", "getPath", "()Ljava/util/List;", "setPath", "(Ljava/util/List;)V", "promotionFare", "getPromotionFare", "setPromotionFare", "rating", "", "getRating", "()F", "setRating", "(F)V", "sourceLatitude", "getSourceLatitude", "setSourceLatitude", "sourceLongitude", "getSourceLongitude", "setSourceLongitude", "sourceName", "getSourceName", "setSourceName", "subTotalFare", "getSubTotalFare", "setSubTotalFare", "time", "getTime", "setTime", "totalFare", "getTotalFare", "setTotalFare", "tripStatus", "getTripStatus", "setTripStatus", "getDestinationLatLng", "Lcom/google/android/gms/maps/model/LatLng;", "getSourceLatLng", "app_release"})
public final class TripDetailsBean extends in.techware.lataxi.model.BaseBean {
    @org.jetbrains.annotations.NotNull()
    private java.lang.String id;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String tripStatus;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String time;
    private float rating;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String driverName;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String driverPhoto;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String kilometer;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String minute;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String baseFare;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String kilometerFare;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String minutesFare;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String subTotalFare;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String promotionFare;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String totalFare;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String sourceLatitude;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String sourceLongitude;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String destinationLatitude;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String destinationLongitude;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String sourceName;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String destinationName;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<in.techware.lataxi.model.PlaceBean> path;
    
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
    public final java.lang.String getTime() {
        return null;
    }
    
    public final void setTime(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    public final float getRating() {
        return 0.0F;
    }
    
    public final void setRating(float p0) {
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
    public final java.lang.String getKilometer() {
        return null;
    }
    
    public final void setKilometer(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMinute() {
        return null;
    }
    
    public final void setMinute(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getBaseFare() {
        return null;
    }
    
    public final void setBaseFare(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getKilometerFare() {
        return null;
    }
    
    public final void setKilometerFare(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getMinutesFare() {
        return null;
    }
    
    public final void setMinutesFare(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSubTotalFare() {
        return null;
    }
    
    public final void setSubTotalFare(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPromotionFare() {
        return null;
    }
    
    public final void setPromotionFare(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTotalFare() {
        return null;
    }
    
    public final void setTotalFare(@org.jetbrains.annotations.NotNull()
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
    public final java.lang.String getSourceName() {
        return null;
    }
    
    public final void setSourceName(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDestinationName() {
        return null;
    }
    
    public final void setDestinationName(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<in.techware.lataxi.model.PlaceBean> getPath() {
        return null;
    }
    
    public final void setPath(@org.jetbrains.annotations.NotNull()
    java.util.List<in.techware.lataxi.model.PlaceBean> p0) {
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
    
    public TripDetailsBean() {
        super();
    }
}