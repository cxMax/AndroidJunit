# AndroidJunit
## introduction
As a freshman to better learn about Robolctric's API , I strongly recommend u visit official TestDemo , [please look here](https://github.com/robolectric/robolectric/tree/master/robolectric/src/test/java/org/robolectric)

## project dependences
butterknife : http://jakewharton.github.io/butterknife/  
robolctric :　http://robolectric.org/
retrofit : http://square.github.io/retrofit/  

## robolctric's error solution
some typical and usual errors: http://blog.csdn.net/misea/article/details/50436215  
````
Invalid default: public abstract java.lang.Class org.robolectric.annotation.Config.application()
````
+ solution :  
 http://blog.csdn.net/yuanyl/article/details/50963219  
 https://github.com/robolectric/robolectric/issues/1620    
  http://stackoverflow.com/questions/5896088/testing-that-button-starts-an-activity-with-robolectric/23480546#23480546  
  
    
when run JUnit method , it always in download progress and download forever 
```
Downloading: com/ibm/icu/icu4j/53.1/icu4j-53.1.jar from repository sonatype at https://oss.sonatype.org/content/groups/public/
Downloading: org/robolectric/android-all/5.0.0_r2-robolectric-1/android-all-5.0.0_r2-robolectric-1.jar from repository sonatype at https://oss.sonatype.org/content/groups/public/
Transferring 41864K from sonatype
Transferring 9482K from sonatype
```
+ solution :   
https://github.com/robolectric/robolectric/issues/2987#issuecomment-287744300  
http://codeblast.com/2015/05/13/using-robolectric-in-offline-mode/  
http://robolectric.org/blog/2017/03/01/hermetic-builds/  

```
"No such manifest file: build/intermediates/bundles/debug/AndroidManifest.xml" : 
```
+ solution :   
https://github.com/robolectric/robolectric/issues/2949

## ButterKnife error solution
generate plugin :　Zelezny  
build gradle : http://blog.csdn.net/ma_yangyang/article/details/53943198  

## thanks for
usage blog :   
http://www.jianshu.com/p/3aa0e4efcfd3  
https://segmentfault.com/a/1190000002904944  
android project demo : https://github.com/geniusmart/LoveUT  
official tutorial : http://robolectric.org/getting-started/  
official usage sample : https://github.com/robolectric/robolectric-samples  





