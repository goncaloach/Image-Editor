import BE.{QuadTree, Utils}
import javafx.fxml.FXML
import javafx.scene.control.{Button, MenuItem}
import javafx.scene.image.{Image, ImageView}
import javafx.stage.{FileChooser, Stage}

import java.io._
import javax.swing.JOptionPane
import scala.io.Source

class ControllerSlideshow {

  @FXML
  private var button1: Button = _
  @FXML
  private var button2: Button = _
  @FXML
  private var img: ImageView = _
  @FXML
  private var menuitem1: MenuItem = _

  private var count = 0
  private var list: List[String] = readFile()
  private var quad = new QuadTree(Utils.readImage(list(count)))

  private var qt = quad.makeQTree()
  private var modifiedQt = qt

  private def readFile(): List[String] = {
    val lista = Source.fromFile("src/Imagens").getLines.toList
    if(lista.isEmpty) List("add")
    else lista
  }

  def writeToFile(lst: List[String]):Unit = {
    val pw = new BufferedWriter(new FileWriter(new File("src/Imagens")))
    for (line <- lst) {
      pw.write(line)
      pw.write("\n")
    }
    pw.close()
  }

  def removeFromGallery():Unit={
    list=list.filter(_ != list(count))
    writeToFile(list)
    if(list.length==0)  img.setImage(new Image("file:src/Images/add.png"))
    else {
      onButton1Clicked()
    }
  }

  def onButton1Clicked(): Unit = { //go left
      count -= 1
      if (count == -1) count = list.length-1
      if(list(count)=="add") removeFromGallery()
      img.setImage(new Image("file:src/Images/" + list(count) + ".png"))
      quad = new QuadTree(Utils.readImage(list(count)))
      qt = quad.makeQTree()
      modifiedQt=qt
  }

  def onButton2Clicked(): Unit = {  //go right
    count += 1
    if (count == list.length) count = 0
    if(list(count)=="add") removeFromGallery()
    img.setImage(new Image("file:src/Images/" + list(count) + ".png"))
    quad = new QuadTree(Utils.readImage(list(count)))
    qt = quad.makeQTree()
    modifiedQt=qt
  }

  def leftButton1(): Unit = {
    button1.opacityProperty().setValue(0)
  }

  def overButton2(): Unit = {
    button2.opacityProperty().setValue(1)
  }

  def leftButton2(): Unit = {
    button2.opacityProperty().setValue(0)
  }

  def overButton1(): Unit = {
    button1.opacityProperty().setValue(1)
  }

  def saveImage():Unit={
    Utils.saveImage(modifiedQt,quad,list(count))
    writeToFile(list)
  }

  def loadImage():Unit={
    val stage:Stage = new Stage()
    val fileChooser: FileChooser = new FileChooser
    fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("ImageType", ".png"))
    val selectedFile: File = fileChooser.showOpenDialog(stage)
    val newName = selectedFile.getName.replaceAll(".png","")
    selectedFile.renameTo(new File(System.getProperty("user.dir") +"\\src\\Images\\"+newName+".png"))
    list = list.appended(newName)

    count =list.length-1
    img.setImage(new Image("file:src/Images/" + list(count) + ".png"))
    quad = new QuadTree(Utils.readImage(list(count)))
    qt = quad.makeQTree()
    modifiedQt=qt
  }

  def scale(): Unit = {
    val value = JOptionPane.showInputDialog("Scale value").toInt
    modifiedQt = quad.scale(value,modifiedQt)
    Utils.saveImage(modifiedQt,quad,"modified_"+list(count))
    img.setImage(new Image( "file:src/Images/modified_" + list(count) + ".png"))
    Utils.deleteFile( "src/Images/modified_" + list(count) + ".png")
  }

  def rotateLeft(): Unit = {
    modifiedQt = quad.rotateL(modifiedQt)
    Utils.saveImage(modifiedQt,quad,"modified_"+list(count))
    img.setImage(new Image( "file:src/Images/modified_" + list(count) + ".png"))
    Utils.deleteFile( "src/Images/modified_" + list(count) + ".png")
  }

  def rotateRight(): Unit = {
    modifiedQt = quad.rotateR(modifiedQt)
    Utils.saveImage(modifiedQt,quad,"modified_"+list(count))
    img.setImage(new Image( "file:src/Images/modified_" + list(count) + ".png"))
    Utils.deleteFile( "src/Images/modified_" + list(count) + ".png")
  }

  def mirrorV(): Unit = {
    modifiedQt = quad.mirrorV(modifiedQt)
    Utils.saveImage(modifiedQt,quad,"modified_"+list(count))
    img.setImage(new Image( "file:src/Images/modified_" + list(count) + ".png"))
    Utils.deleteFile( "src/Images/modified_" + list(count) + ".png")
  }

  def mirrorH(): Unit = {
    modifiedQt = quad.mirrorH(modifiedQt)
    Utils.saveImage(modifiedQt,quad,"modified_"+list(count))
    img.setImage(new Image( "file:src/Images/modified_" + list(count) + ".png"))
    Utils.deleteFile( "src/Images/modified_" + list(count) + ".png")
  }

  def sepia(): Unit = {
    modifiedQt = quad.applySepia(modifiedQt)
    Utils.saveImage(modifiedQt,quad,"modified_"+list(count))
    img.setImage(new Image( "file:src/Images/modified_" + list(count) + ".png"))
    Utils.deleteFile( "src/Images/modified_" + list(count) + ".png")
  }

  def contrast(): Unit = {
    val value = JOptionPane.showInputDialog("Contrast value").toInt
    modifiedQt = quad.applyContrast(modifiedQt,value)
    Utils.saveImage(modifiedQt,quad,"modified_"+list(count))
    img.setImage(new Image( "file:src/Images/modified_" + list(count) + ".png"))
    Utils.deleteFile( "src/Images/modified_" + list(count) + ".png")
  }

  def grayNoise(): Unit = {
    modifiedQt = quad.applyNoise(modifiedQt)
    Utils.saveImage(modifiedQt,quad,"modified_"+list(count))
    img.setImage(new Image( "file:src/Images/modified_" + list(count) + ".png"))
    Utils.deleteFile( "src/Images/modified_" + list(count) + ".png")
  }

  def colorNoise(): Unit = {
    modifiedQt = quad.applyNoisePure(modifiedQt)
    Utils.saveImage(modifiedQt,quad,"modified_"+list(count))
    img.setImage(new Image( "file:src/Images/modified_" + list(count) + ".png"))
    Utils.deleteFile( "src/Images/modified_" + list(count) + ".png")
  }

}