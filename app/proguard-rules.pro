## Add project specific ProGuard rules here.
## You can control the set of applied configuration files using the
## proguardFiles setting in build.gradle.
##
## For more details, see
##   http://developer.android.com/guide/developing/tools/proguard.html
#
## If your project uses WebView with JS, uncomment the following
## and specify the fully qualified class name to the JavaScript interface
## class:
##-keepclassmembers class fqcn.of.javascript.interface.for.webview {
##   public *;
##}
#
## Uncomment this to preserve the line number information for
## debugging stack traces.
##-keepattributes SourceFile,LineNumberTable
#
## If you keep the line number information, uncomment this to
## hide the original source file name.
##-renamesourcefileattribute SourceFile
#
##-----------------------okhttp3+retrofit2+rxjava2-------------------
#-dontwarn javax.annotation.**
#-dontwarn javax.inject.**
## OkHttp3
#-dontwarn com.squareup.okhttp3.**
#-keep class com.squareup.okhttp3.** { *;}
#-keep class okhttp3.**{*;}
#-dontwarn okio.**
#-dontwarn okhttp3.**
#
## Retrofit
#-dontwarn retrofit2.**
#-keep class retrofit2.** { *; }
#-keepattributes Signature
#-keepattributes Exceptions
## RxJava RxAndroid
#-dontwarn sun.misc.**
#-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
#    long producerIndex;
#    long consumerIndex;
#}
#-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
#    rx.internal.util.atomic.LinkedQueueNode producerNode;
#}
#-keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
#    rx.internal.util.atomic.LinkedQueueNode consumerNode;
#}
#
## Gson
#-keep class com.google.gson.stream.** { *; }
#-keepattributes EnclosingMethod
#
## Gson Bean
#-keep class com.*.*.model.entity.**{*;} # 自定义数据模型的bean目录
#-keep class com.*.*.model.bean.**{*;} # 自定义数据模型的bean目录
#
#
##---------------------------------------EventBus-----------------------------------
#-keepattributes *Annotation*
#-keepclassmembers class * {
#    @org.greenrobot.eventbus.Subscribe <methods>;
#}
#-keep enum org.greenrobot.eventbus.ThreadMode { *; }
#
## Only required if you use AsyncExecutor
#-keepclassmembers class * extends org.greenrobot.eventbus.util.ThrowableFailureEvent {
#    <init>(java.lang.Throwable);
#}
#
##------------------------------------Gride-----------------------------------
#-keep public class * implements com.bumptech.glide.module.GlideModule
#-keep public class * extends com.bumptech.glide.module.AppGlideModule
#-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
#    **[] $VALUES;
#    public *;
#}
#
## for DexGuard only
##-keepresourcexmlelements manifest/application/meta-data@value=GlideModule
#
##------------------------------------Arouter路由--------------------------
#-keep public class com.alibaba.android.arouter.routes.**{*;}
#-keep public class com.alibaba.android.arouter.facade.**{*;}
#-keep class * implements com.alibaba.android.arouter.facade.template.ISyringe{*;}
#
## 如果使用了 byType 的方式获取 Service，需添加下面规则，保护接口
##-keep interface * implements com.alibaba.android.arouter.facade.template.IProvider
#
## 如果使用了 单类注入，即不定义接口实现 IProvider，需添加下面规则，保护实现
## -keep class * implements com.alibaba.android.arouter.facade.template.IProvider
#
##----------------------------------XBanner 图片轮播混淆配置-------------------------
#-keep class com.stx.xhb.xbanner.**{*;}
#
##--------------------------------ImmersionBar android 4.4以上沉浸式----------------
#-keep class com.gyf.immersionbar.* {*;}
#-dontwarn com.gyf.immersionbar.**
#
##--------------------------------Loader 加载中动画------------------------------------
#-keep class com.wang.avi.** { *; }
#-keep class com.wang.avi.indicators.** { *; }
#
##--------------------------------剪裁图片------------------------------------
#-dontwarn com.yalantis.ucrop**
#-keep class com.yalantis.ucrop** { *; }
#-keep interface com.yalantis.ucrop** { *; }
#
#-keep class com.dx.mobile.captcha.**{*;}
#-keep class org.json.**{*;}
