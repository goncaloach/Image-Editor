import javafx.scene.image.Image

import java.io._
import scala.io.Source

object testes {

  private val lista: List[String] = Source.fromFile("src/Imagens").getLines.toList

  def main(args: Array[String]): Unit = {
    //println(lista)
    /*val list:List[String] = List("ola","tudo","bem=")

    def writeToFile()={
      val pw =new BufferedWriter(new FileWriter(new File("src/Imagens")))
      for (line <- list) {
        pw.write(line)
        pw.write("\n")
      }
      pw.close()
    }

    writeToFile()

  }*/

      //Utils.deleteFile("src/Images/new2.png")

  }


  /*var count = 0

private val list: List[String] = readFile()

def readFile():List[String]={
  Source.fromFile("src/Imagens").getLines.toList
}

def writeToFile(lst : List[String])={
  val pw =new BufferedWriter(new FileWriter(new File("src/Imagens")))
  for (line <- lst) {
    pw.write(line)
    pw.write("\n")
  }
  pw.close()
}

def onButton1Clicked(): Unit ={
  count -= 1
  if(count == -1) count=list.length-1
  val newImage:Image = new Image("Images/"+list(count)+".png")
  img.setImage(newImage)
}

def onButton2Clicked(): Unit ={
  count += 1
  if(count == list.length) count=0
  val newImage:Image = new Image("Images/"+list(count)+".png")
  img.setImage(newImage)
*/
}