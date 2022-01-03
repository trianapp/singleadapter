package app.trian.dependencies
object ApplicationId{
    const val appId = "app.trian.singleadapter"

}
object Releases{
    private const val versionMajor = 1
    private const val versionMinor = 1
    private const val versionPatch = 1
    private const val versionBuild = 1

    var versionCode = versionMajor * 10000 + versionMinor * 1000 + versionPatch * 100 + versionBuild
    const val versionName = "$versionMajor.$versionMinor.$versionPatch"
}

/**
 * Arranged alphabetically
 */
object Versions {
    val kotlinVersion = "1.5.21"
    val safeArgs = "2.2.2"
    val compileSdkVersion = 31
    val buildTool = "28"
    val minSdk = 24
    val targetSdk = 34
}

object Libs{
    object GradlePlugin{
        val androidGradlePlugin = "com.android.tools.build:gradle:7.0.1"
        val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
    }

    object AndroidX{
        var coreKtx = "androidx.core:core-ktx:1.7.0"
        var appCompat = "androidx.appcompat:appcompat:1.4.0"
        var recyclerView ="androidx.recyclerview:recyclerview:1.1.0"
    }
    object Google{
        var material = "com.google.android.material:material:1.4.0"
    }
    object Junit{
        var junit = "junit:junit:4.+"
        var testJunit = "androidx.test.ext:junit:1.1.3"
    }
    object Espresso{
        var test = "androidx.test.espresso:espresso-core:3.4.0"
    }
}