name := "documentation-test"

version := "0.1"

scalaVersion := "2.12.6"

val githubBaseUrl = "http://ghbu"
val scaladocApiBaseUrl = "http://sdabu"


lazy val documentationTest = project.in(file("."))
  .settings(
    // sbt-site needs to know where to find the paradox site
    sourceDirectory in Paradox := baseDirectory.value / "documentation",
    // paradox needs a theme
    ParadoxMaterialThemePlugin.paradoxMaterialThemeSettings(Paradox),
    paradoxProperties in Compile ++= Map(
      "github.base_url" -> s"$githubBaseUrl",
      "scaladoc.api.base_url" -> s"$scaladocApiBaseUrl"
    ),
    // Puts unified scaladocs into target/api
    siteSubdirName in ScalaUnidoc := "api",
    addMappingsToSiteDir(mappings in (ScalaUnidoc, packageDoc), siteSubdirName in ScalaUnidoc),

    //scalacOptions in (Compile, doc) ++= Seq("-doc-root-content", baseDirectory.value+"/src/main/scala/root-doc.md")

  ).enablePlugins(ParadoxSitePlugin, ParadoxMaterialThemePlugin,SiteScaladocPlugin,ScalaUnidocPlugin)
  .dependsOn(subProjA,subProjB)
  .aggregate(subProjA,subProjB)


lazy val subProjA = project.in(file("subProjA"))
  .settings()


lazy val subProjB = project.in(file("subProjB"))
  .settings()


//Documentation
//Task for building docs and copying to root level docs folder (for GitHub pages)
val updateDocsTask = TaskKey[Unit]("updateDocs","copies paradox docs to /docs directory")

updateDocsTask := {
  val siteResult = makeSite.value
  val docSource = new File("target/site")
  val docDest = new File("docs")
  IO.copyDirectory(docSource,docDest,overwrite=true,preserveLastModified=true)
}
