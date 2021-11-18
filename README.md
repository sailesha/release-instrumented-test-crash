
This is a simple way to reproduce a crash in release instrumented tests.

This example does the following:
* enable core library desugaring
* set target and source Java version to 11
* set test build type to release

The main app activity uses java.time.Duration API. The instrumentation test also uses the same API.

When the instrumentation is run, the app crashes with the following stack trace:
```
java.lang.NoSuchMethodError: No virtual method isZero()Z in class Lj$/time/Duration; or its super classes (declaration of 'j$.time.Duration' appears in /data/app/com.example.releaseautomation.test-CjSCPaNkDrJi-Vn6GFRrpQ==/base.apk!classes2.dex)
  at com.example.releaseautomation.MainActivity.onCreate(MainActivity.kt:4)
  at android.app.Activity.performCreate(Activity.java:7802)
  at android.app.Activity.performCreate(Activity.java:7791)
  at android.app.Instrumentation.callActivityOnCreate(Instrumentation.java:1299)
  at androidx.test.runner.MonitoringInstrumentation.callActivityOnCreate(MonitoringInstrumentation.java:730)
  at android.app.ActivityThread.performLaunchActivity(ActivityThread.java:3245)
  at android.app.ActivityThread.handleLaunchActivity(ActivityThread.java:3409)
  at android.app.servertransaction.LaunchActivityItem.execute(LaunchActivityItem.java:83)
  at android.app.servertransaction.TransactionExecutor.executeCallbacks(TransactionExecutor.java:135)
  at android.app.servertransaction.TransactionExecutor.execute(TransactionExecutor.java:95)
  at android.app.ActivityThread$H.handleMessage(ActivityThread.java:2016)
  at android.os.Handler.dispatchMessage(Handler.java:107)
  at android.os.Looper.loop(Looper.java:214)
  at android.app.ActivityThread.main(ActivityThread.java:7356)
  at java.lang.reflect.Method.invoke(Native Method)
  at com.android.internal.os.RuntimeInit$MethodAndArgsCaller.run(RuntimeInit.java:492)
  at com.android.internal.os.ZygoteInit.main(ZygoteInit.java:930)
```

## Steps

 - Open app in Android Studio
 - Click Build -> Select Build Variant -> Release
 - Run ExampleInstrumentedTest

## Notes

 - The app doesn't crash on its own. Only when run from the instrumentation test.
 - The test doesn't crash in debug mode.
 - The issue might be related to having to versions of the java.time.Duration class in the class path. It seems like one is in the app and one is in the test app. The test app seems to have fewer methods in it.
