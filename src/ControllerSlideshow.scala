import javafx.fxml.FXML
import javafx.scene.control.{Button, MenuItem}
import javafx.scene.image.{Image, ImageView}

import java.io._
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

  def readFile(): List[String] = {
    Source.fromFile("src/Imagens").getLines.toList
  }

  def writeToFile(lst: List[String]):Unit = {
    val pw = new BufferedWriter(new FileWriter(new File("src/Imagens")))
    for (line <- lst) {
      pw.write(line)
      pw.write("\n")
    }
    pw.close()
  }

  def onButton1Clicked(): Unit = { //go left
    count -= 1
    if (count == -1) count = list.length-1
    img.setImage(new Image("Images/" + list(count) + ".png"))
    quad = new QuadTree(Utils.readImage(list(count)))
    qt = quad.makeQTree()
    modifiedQt=qt
  }

  def onButton2Clicked(): Unit = {  //go right
    count += 1
    if (count == list.length) count = 0
    img.setImage(new Image("Images/" + list(count) + ".png"))
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

  def rotateLeft(): Unit = {
    modifiedQt = quad.rotateL(modifiedQt)
    Utils.saveImage(modifiedQt,quad,"modified_"+list(count))
    img.setImage(new Image( "file:src/Images/modified_" + list(count) + ".png"))
    Utils.deleteFile( "src/Images/modified_" + list(count) + ".png")

    //System.getProperty("user.home") + "/Desktop"


    /*ImageUtil.writeImage(quad.QTreeToBitMap(rl).toArray map (x => x.toArray),"C:/Users/Gugaskneefs/Desktop/modified_"+ list(count)+".png", "png")
    img.setImage(new Image( "file:C:/Users/Gugaskneefs/Desktop/modified_" + list(count) + ".png"))*/




    /*val stage:Stage = new Stage()
    val fileChooser: FileChooser = new FileChooser
    fileChooser.setSelectedExtensionFilter(new FileChooser.ExtensionFilter("ImageType", ".png", ".jpg", "*.jpeg"))
    val selectedFile: File = fileChooser.showOpenDialog(stage)*/
  }

}
