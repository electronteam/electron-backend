import java.nio.file.Files
import java.nio.file.Paths

println "Moving product images !!!"

def srcDirectory = new File('src/main/resources/static/medias')
def destDirectory = "data/medias/products/"

srcDirectory.eachFile {
  filename = it.name
  println(filename)
  destFileName = destDirectory + filename
  destFile = new File(destFileName)

  if (!destFile.exists())
  {
    def source = Paths.get(it.toURI())
    def target = Paths.get(destFile.toURI())
    Files.copy(source, target)
  }
}