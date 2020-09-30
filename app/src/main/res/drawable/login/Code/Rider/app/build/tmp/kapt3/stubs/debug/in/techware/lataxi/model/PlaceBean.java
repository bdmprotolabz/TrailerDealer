package in.techware.lataxi.model;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u0006\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0011\u0010%\u001a\u00020\u00112\u0006\u0010&\u001a\u00020\u0000H\u0096\u0002J\u0006\u0010\'\u001a\u00020(R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u0011\u0010\n\u001a\u00020\u000b8F\u00a2\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u00020\u000b8F\u00a2\u0006\u0006\u001a\u0004\b\u000f\u0010\rR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0007\"\u0004\b\u0018\u0010\tR\u001a\u0010\u0019\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0007\"\u0004\b\u001b\u0010\tR\u001a\u0010\u001c\u001a\u00020\u0005X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0007\"\u0004\b\u001e\u0010\tR\u001c\u0010\u001f\u001a\u0004\u0018\u00010 X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\"\"\u0004\b#\u0010$\u00a8\u0006)"}, d2 = {"Lin/techware/lataxi/model/PlaceBean;", "Lin/techware/lataxi/model/BaseBean;", "", "()V", "address", "", "getAddress", "()Ljava/lang/String;", "setAddress", "(Ljava/lang/String;)V", "dLatitude", "", "getDLatitude", "()D", "dLongitude", "getDLongitude", "id", "", "getId", "()I", "setId", "(I)V", "latitude", "getLatitude", "setLatitude", "longitude", "getLongitude", "setLongitude", "name", "getName", "setName", "place", "Lcom/google/android/libraries/places/api/model/Place;", "getPlace", "()Lcom/google/android/libraries/places/api/model/Place;", "setPlace", "(Lcom/google/android/libraries/places/api/model/Place;)V", "compareTo", "other", "getLatLng", "Lcom/google/android/gms/maps/model/LatLng;", "app_debug"})
public final class PlaceBean extends in.techware.lataxi.model.BaseBean implements java.lang.Comparable<in.techware.lataxi.model.PlaceBean> {
    private int id;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String address;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String latitude;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String longitude;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String name;
    @org.jetbrains.annotations.Nullable()
    private com.google.android.libraries.places.api.model.Place place;
    
    public final int getId() {
        return 0;
    }
    
    public final void setId(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getAddress() {
        return null;
    }
    
    public final void setAddress(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLatitude() {
        return null;
    }
    
    public final void setLatitude(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLongitude() {
        return null;
    }
    
    public final void setLongitude(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getName() {
        return null;
    }
    
    public final void setName(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.google.android.libraries.places.api.model.Place getPlace() {
        return null;
    }
    
    public final void setPlace(@org.jetbrains.annotations.Nullable()
    com.google.android.libraries.places.api.model.Place p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.android.gms.maps.model.LatLng getLatLng() {
        return null;
    }
    
    public final double getDLatitude() {
        return 0.0;
    }
    
    public final double getDLongitude() {
        return 0.0;
    }
    
    @java.lang.Override()
    public int compareTo(@org.jetbrains.annotations.NotNull()
    in.techware.lataxi.model.PlaceBean other) {
        return 0;
    }
    
    public PlaceBean() {
        super();
    }
}