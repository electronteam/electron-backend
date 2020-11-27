import java.nio.file.Files
import java.nio.file.Paths

println "Moving product images !!!"

def srcDirectory = new File('src/main/resources/static/medias')
def destDirectoryName = "data/medias/products/"
def destDirectory = new File(destDirectoryName)

if (!destDirectory.exists())
{
  println "Creating local medias directory !!!"
  Files.createDirectories(Paths.get(destDirectoryName))
}

srcDirectory.eachFile {
  filename = it.name
  println(filename)
  destFileName = destDirectoryName + filename
  destFile = new File(destFileName)

  if (!destFile.exists())
  {
    def source = Paths.get(it.toURI())
    def target = Paths.get(destFile.toURI())
    Files.copy(source, target)
  }
}