package in.techware.lataxi.viewModels;

import java.lang.System;

/**
 * * Created by Jemsheer K D on 27 July, 2018.
 * * Package `in`.techware.lataxi.viewModels
 * * Project Carrefour
 */
@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b#\n\u0002\u0010\u0007\n\u0002\b\u000e\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0006\u0010D\u001a\u00020EJ\u0006\u0010F\u001a\u00020GJ\u0006\u0010H\u001a\u00020IJ\u0006\u0010J\u001a\u00020IJ\u0006\u0010K\u001a\u00020LR*\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\f8F\u00a2\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\f8F\u00a2\u0006\u0006\u001a\u0004\b\u0010\u0010\u000eR\u0011\u0010\u0011\u001a\u00020\f8F\u00a2\u0006\u0006\u001a\u0004\b\u0012\u0010\u000eR\u0011\u0010\u0013\u001a\u00020\f8F\u00a2\u0006\u0006\u001a\u0004\b\u0014\u0010\u000eR\u001a\u0010\u0015\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u0017\"\u0004\b\u001c\u0010\u0019R\u001a\u0010\u001d\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u0017\"\u0004\b\u001f\u0010\u0019R\u001a\u0010 \u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0017\"\u0004\b\"\u0010\u0019R\u001a\u0010#\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0017\"\u0004\b%\u0010\u0019R\u001a\u0010&\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\'\u0010\u0017\"\u0004\b(\u0010\u0019R\u001a\u0010)\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u0017\"\u0004\b+\u0010\u0019R*\u0010,\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b-\u0010\b\"\u0004\b.\u0010\nR\u001a\u0010/\u001a\u000200X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b1\u00102\"\u0004\b3\u00104R\u001a\u00105\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b6\u0010\u0017\"\u0004\b7\u0010\u0019R\u001a\u00108\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b9\u0010\u0017\"\u0004\b:\u0010\u0019R\u001a\u0010;\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b<\u0010\u0017\"\u0004\b=\u0010\u0019R\u001a\u0010>\u001a\u00020?X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b@\u0010A\"\u0004\bB\u0010C\u00a8\u0006M"}, d2 = {"Lin/techware/lataxi/viewModels/TripFeedbackViewModel;", "Landroid/arch/lifecycle/ViewModel;", "()V", "badFeedbackList", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "getBadFeedbackList", "()Ljava/util/ArrayList;", "setBadFeedbackList", "(Ljava/util/ArrayList;)V", "dDestinationLatitude", "", "getDDestinationLatitude", "()D", "dDestinationLongitude", "getDDestinationLongitude", "dSourceLatitude", "getDSourceLatitude", "dSourceLongitude", "getDSourceLongitude", "destination", "getDestination", "()Ljava/lang/String;", "setDestination", "(Ljava/lang/String;)V", "destinationLatitude", "getDestinationLatitude", "setDestinationLatitude", "destinationLongitude", "getDestinationLongitude", "setDestinationLongitude", "driverName", "getDriverName", "setDriverName", "driverPhoto", "getDriverPhoto", "setDriverPhoto", "fare", "getFare", "setFare", "feedback", "getFeedback", "setFeedback", "goodFeedbackList", "getGoodFeedbackList", "setGoodFeedbackList", "rating", "", "getRating", "()F", "setRating", "(F)V", "source", "getSource", "setSource", "sourceLatitude", "getSourceLatitude", "setSourceLatitude", "sourceLongitude", "getSourceLongitude", "setSourceLongitude", "time", "", "getTime", "()J", "setTime", "(J)V", "clearFeedback", "", "generateFeedbackBean", "Lin/techware/lataxi/model/FeedbackBean;", "getDestinationLatLng", "Lcom/google/android/gms/maps/model/LatLng;", "getSourceLatLng", "isDataCollected", "", "app_debug"})
public final class TripFeedbackViewModel extends android.arch.lifecycle.ViewModel {
    private float rating;
    private long time;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String driverName;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String driverPhoto;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String fare;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String source;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String sourceLatitude;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String sourceLongitude;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String destination;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String destinationLatitude;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String destinationLongitude;
    @org.jetbrains.annotations.NotNull()
    private java.util.ArrayList<java.lang.String> goodFeedbackList;
    @org.jetbrains.annotations.NotNull()
    private java.util.ArrayList<java.lang.String> badFeedbackList;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String feedback;
    
    public final float getRating() {
        return 0.0F;
    }
    
    public final void setRating(float p0) {
    }
    
    public final long getTime() {
        return 0L;
    }
    
    public final void setTime(long p0) {
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
    public final java.lang.String getFare() {
        return null;
    }
    
    public final void setFare(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getSource() {
        return null;
    }
    
    public final void setSource(@org.jetbrains.annotations.NotNull()
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
    public final java.lang.String getDestination() {
        return null;
    }
    
    public final void setDestination(@org.jetbrains.annotations.NotNull()
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
    public final java.util.ArrayList<java.lang.String> getGoodFeedbackList() {
        return null;
    }
    
    public final void setGoodFeedbackList(@org.jetbrains.annotations.NotNull()
    java.util.ArrayList<java.lang.String> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.ArrayList<java.lang.String> getBadFeedbackList() {
        return null;
    }
    
    public final void setBadFeedbackList(@org.jetbrains.annotations.NotNull()
    java.util.ArrayList<java.lang.String> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getFeedback() {
        return null;
    }
    
    public final void setFeedback(@org.jetbrains.annotations.NotNull()
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
    
    @org.jetbrains.annotations.NotNull()
    public final in.techware.lataxi.model.FeedbackBean generateFeedbackBean() {
        return null;
    }
    
    public final boolean isDataCollected() {
        return false;
    }
    
    public final void clearFeedback() {
    }
    
    public TripFeedbackViewModel() {
        super();
    }
}