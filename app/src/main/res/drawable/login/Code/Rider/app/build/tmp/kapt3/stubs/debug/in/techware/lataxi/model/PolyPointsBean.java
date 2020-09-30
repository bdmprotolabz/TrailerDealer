package in.techware.lataxi.model;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 13}, bv = {1, 0, 3}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR2\u0010\u000f\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\n0\u00110\u00100\u0010X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\u0017X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\nX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\f\"\u0004\b\u001e\u0010\u000e\u00a8\u0006\u001f"}, d2 = {"Lin/techware/lataxi/model/PolyPointsBean;", "Lin/techware/lataxi/model/BaseBean;", "()V", "distance", "", "getDistance", "()I", "setDistance", "(I)V", "distanceText", "", "getDistanceText", "()Ljava/lang/String;", "setDistanceText", "(Ljava/lang/String;)V", "routes", "", "Ljava/util/HashMap;", "getRoutes", "()Ljava/util/List;", "setRoutes", "(Ljava/util/List;)V", "time", "", "getTime", "()J", "setTime", "(J)V", "timeText", "getTimeText", "setTimeText", "app_debug"})
public final class PolyPointsBean extends in.techware.lataxi.model.BaseBean {
    private long time;
    private int distance;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String timeText;
    @org.jetbrains.annotations.NotNull()
    private java.lang.String distanceText;
    @org.jetbrains.annotations.NotNull()
    private java.util.List<? extends java.util.List<? extends java.util.HashMap<java.lang.String, java.lang.String>>> routes;
    
    public final long getTime() {
        return 0L;
    }
    
    public final void setTime(long p0) {
    }
    
    public final int getDistance() {
        return 0;
    }
    
    public final void setDistance(int p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getTimeText() {
        return null;
    }
    
    public final void setTimeText(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDistanceText() {
        return null;
    }
    
    public final void setDistanceText(@org.jetbrains.annotations.NotNull()
    java.lang.String p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.util.List<java.util.HashMap<java.lang.String, java.lang.String>>> getRoutes() {
        return null;
    }
    
    public final void setRoutes(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends java.util.List<? extends java.util.HashMap<java.lang.String, java.lang.String>>> p0) {
    }
    
    public PolyPointsBean() {
        super();
    }
}