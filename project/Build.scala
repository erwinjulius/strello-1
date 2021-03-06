import sbt._
import Keys._
import play.Project._
import com.typesafe.sbt.SbtAtmosPlay.atmosPlaySettings


object ApplicationBuild extends Build {

  val appName         = "strello"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    jdbc,
    anorm,
    "org.squeryl" %% "squeryl" % "0.9.5-6",
    //"org.scalatest" %% "scalatest" % "1.8" % "test"
    "org.scalatest" % "scalatest_2.10" % "1.9.1" % "test"
  )


  val main = play.Project(appName, appVersion, appDependencies).settings(
    //testOptions in Test := Nil
    
    atmosPlaySettings: _*
    // Add your own project settings here      
  )

}
