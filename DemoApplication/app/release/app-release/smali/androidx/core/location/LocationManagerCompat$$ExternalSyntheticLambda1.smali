.class public final synthetic Landroidx/core/location/LocationManagerCompat$$ExternalSyntheticLambda1;
.super Ljava/lang/Object;
.source "D8$$SyntheticClass"

# interfaces
.implements Ljava/util/concurrent/Callable;


# instance fields
.field public final synthetic f$0:Landroid/location/LocationManager;

.field public final synthetic f$1:Landroidx/core/location/LocationManagerCompat$GpsStatusTransport;


# direct methods
.method public synthetic constructor <init>(Landroid/location/LocationManager;Landroidx/core/location/LocationManagerCompat$GpsStatusTransport;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Landroidx/core/location/LocationManagerCompat$$ExternalSyntheticLambda1;->f$0:Landroid/location/LocationManager;

    iput-object p2, p0, Landroidx/core/location/LocationManagerCompat$$ExternalSyntheticLambda1;->f$1:Landroidx/core/location/LocationManagerCompat$GpsStatusTransport;

    return-void
.end method


# virtual methods
.method public final call()Ljava/lang/Object;
    .locals 1

    iget-object v0, p0, Landroidx/core/location/LocationManagerCompat$$ExternalSyntheticLambda1;->f$0:Landroid/location/LocationManager;

    iget-object p0, p0, Landroidx/core/location/LocationManagerCompat$$ExternalSyntheticLambda1;->f$1:Landroidx/core/location/LocationManagerCompat$GpsStatusTransport;

    invoke-static {v0, p0}, Landroidx/core/location/LocationManagerCompat;->lambda$registerGnssStatusCallback$1(Landroid/location/LocationManager;Landroidx/core/location/LocationManagerCompat$GpsStatusTransport;)Ljava/lang/Boolean;

    move-result-object p0

    return-object p0
.end method