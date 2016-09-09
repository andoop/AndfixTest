## AndfixTest
###阿里[Andfix](https://github.com/alibaba/AndFix)学习demo
###github：https://github.com/andoop/AndfixTest
####大概原理
>通过修改底层的c代码，将方法指针从错误的方法指向补丁中正确的方法，如图



<img src="http://img.blog.csdn.net/20151113112729932"/>
>因为是方法的替换，所以Andfix只适用于修改方法逻辑，不能增加方法和类，而QQ空间的[Nuwa](https://github.com/jasonross/Nuwa)却可以增加方法和类

>如果只是修复方法逻辑的话，那Andfix绝对当选

---

####使用方法

	compile 'com.alipay.euler:andfix:0.4.0@aar'
>gradle中引用

---
####配置混淆文件

	-keep class * extends java.lang.annotation.Annotation
	-keepclasseswithmembernames class * {
	    native <methods>;
	}

----------


	public class MApplication extends Application{
	
	    public PatchManager patchManager;
	    @Override
	    public void onCreate() {
	        super.onCreate();
	        patchManager=new PatchManager(this);
	        //初始化patch版本，以后更新补丁包的时候，也要更新版本
	        patchManager.init("1.0");
	        //加载已经添加的patch
	        patchManager.loadPatch();
	    }
	}



>在自定义Applicaiton中初始化

---

	patchManager.addPatch(Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+"andfix/out.apatch");
>修复问题，加载.apatch文件，这个文件就是补丁包，调用此方法后，问题就马上修复了

>具体使用请看demo


####怎样生成补丁包呢？

>阿里有相应的工具，在本demo的apkpatch-1.0.3文件加载有相关工具

	准备两个安装包，一个是有问题的，一个是修复问题的
	
	根据这两个包，生成.apatch文件
	
	用法: apkpatch -f <new> -t <old> -o <output> -k <keystore> -p <***> -a <alias> -e <***>
	
	 -a,--alias <alias>     keystore entry alias.
	 -e,--epassword <***>   keystore entry password.
	 -f,--from <loc>        new Apk file path.
	 -k,--keystore <loc>    keystore path.
	 -n,--name <name>       patch name.
	 -o,--out <dir>         output dir.
	 -p,--kpassword <***>   keystore password.
	 -t,--to <loc>          old Apk file path.
	
	得到.apatch文件后，通过某种方式（网络下载或者adb命令push），将.apatch文件放到手机存储卡中.
	
	有时候，在团队协作中，产生了多个.apatch文件，下面的命令来merge多个补丁文件
	
	用法: apkpatch -m <apatch_path...> -o <output> -k <keystore> -p <***> -a <alias> -e <***>
	 -a,--alias <alias>     keystore entry alias.
	 -e,--epassword <***>   keystore entry password.
	 -k,--keystore <loc>    keystore path.
	 -m,--merge <loc...>    path of .apatch files.
	 -n,--name <name>       patch name.
	 -o,--out <dir>         output dir.
	 -p,--kpassword <***>   keystore password.

---

####怎样使用本demo呢？

>生成.apatch文件的时候，会用到签名文件，可以使用本demo的签名文件，文件在/apkpatch-1.0.3/mkeystore.jks   
>alias=andoop；password=andoop

>1. 将有问题的apk修改为old.apk,放入到apkpatch-1.0.3下
>2. 将修复问题的apk修改为new.apk,放入到apkpatch-1.0.3下，new.apk和old.apk都需要签名
>3. 双击patch.bat文件，.apatch文件就会在patchs文件夹中生成
>4. .apatch文件改名为out.apatch
>5. 通过命令 adb push 命令将out.apatch 放到手机的/sdcard/andfix中
>6. 点击demo中修复按钮，问题就会修复

####欢迎关注andoop，每周一、二，干货持续更新！
