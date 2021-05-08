import BE.{QTree, QuadTree, Utils}
import BE.QuadTree.Coords
import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.control.{Button, ComboBox, MenuButton, MenuItem}
import javafx.scene.image.{Image, ImageView}

import java.io._
import javax.swing.JOptionPane
import scala.io.Source
import javax.swing.JOptionPane

class ControllerGrid {

  @FXML
  private var button1: Button = _
  @FXML
  private var button2: Button = _
  @FXML
  private var button3: Button = _
  @FXML
  private var button4: Button = _
  @FXML
  private var button5: Button = _
  @FXML
  private var button6: Button = _
  @FXML
  private var button7: Button = _
  @FXML
  private var button8: Button = _
  @FXML
  private var button9: Button = _
  @FXML
  private var img1: ImageView = _
  @FXML
  private var img2: ImageView = _
  @FXML
  private var img3: ImageView = _
  @FXML
  private var img4: ImageView = _
  @FXML
  private var img5: ImageView = _
  @FXML
  private var img6: ImageView = _
  @FXML
  private var img7: ImageView = _
  @FXML
  private var img8: ImageView = _
  @FXML
  private var img9: ImageView = _
  @FXML
  private var remove1: Button = _
  @FXML
  private var remove2: Button = _
  @FXML
  private var remove3: Button = _
  @FXML
  private var remove4: Button = _
  @FXML
  private var remove5: Button = _
  @FXML
  private var remove6: Button = _
  @FXML
  private var remove7: Button = _
  @FXML
  private var remove8: Button = _
  @FXML
  private var remove9: Button = _

  private var list: List[String] = readFile()

  private var input1: String = null
  private var input2: String = null
  private var input3: String = null
  private var input4: String = null
  private var input5: String = null
  private var input6: String = null
  private var input7: String = null
  private var input8: String = null
  private var input9: String = null


  /*
  ON WINDOW START
   */

  def inicializeExistingImages(): Unit ={
    if (list(0) != null) {
      input1 = list(0)
      button1.visibleProperty().setValue(false)
      img1.visibleProperty().setValue(true)
      remove1.visibleProperty().setValue(true)
      button2.visibleProperty().setValue(true)
      println(list(0))
      var newImage: Image = new Image("file:src/Images/" + list(0) + ".png")
      img1.setImage(newImage)
    }
    if (list(1) != null) {
      input2=list(1)
      button2.visibleProperty().setValue(false)
      img2.visibleProperty().setValue(true)
      remove2.visibleProperty().setValue(true)
      button3.visibleProperty().setValue(true)
      println(list(1))
      var newImage: Image = new Image("file:src/Images/" + list(1) + ".png")
      img2.setImage(newImage)
    }
    if (list(2) != null) {
      input3=list(2)
      button3.visibleProperty().setValue(false)
      img3.visibleProperty().setValue(true)
      remove3.visibleProperty().setValue(true)
      button4.visibleProperty().setValue(true)
      println(list(2))
      var newImage: Image = new Image("file:src/Images/" + list(2) + ".png")
      img3.setImage(newImage)
    }
    if (list(3) != null) {
      input4=list(3)
      button4.visibleProperty().setValue(false)
      img4.visibleProperty().setValue(true)
      remove4.visibleProperty().setValue(true)
      button5.visibleProperty().setValue(true)
      println(list(3))
      var newImage: Image = new Image("file:src/Images/" + list(3) + ".png")
      img4.setImage(newImage)
    }
    if (list(4) != null) {
      input5=list(4)
      button5.visibleProperty().setValue(false)
      img5.visibleProperty().setValue(true)
      remove5.visibleProperty().setValue(true)
      button6.visibleProperty().setValue(true)
      println(list(4))
      var newImage: Image = new Image("file:src/Images/" + list(4) + ".png")
      img5.setImage(newImage)
    }
    if (list(5) != null) {
      input6=list(5)
      button6.visibleProperty().setValue(false)
      img6.visibleProperty().setValue(true)
      remove6.visibleProperty().setValue(true)
      button7.visibleProperty().setValue(true)
      println(list(5))
      var newImage: Image = new Image("file:src/Images/" + list(5) + ".png")
      img6.setImage(newImage)
    }
    if (list(6) != null) {
      input7=list(6)
      button7.visibleProperty().setValue(false)
      img7.visibleProperty().setValue(true)
      remove7.visibleProperty().setValue(true)
      button8.visibleProperty().setValue(true)
      println(list(6))
      var newImage: Image = new Image("file:src/Images/" + list(6) + ".png")
      img7.setImage(newImage)
    }
    if (list(7) != null) {
      input8=list(7)
      button8.visibleProperty().setValue(false)
      img8.visibleProperty().setValue(true)
      remove8.visibleProperty().setValue(true)
      button9.visibleProperty().setValue(true)
      println(list(7))
      var newImage: Image = new Image("file:src/Images/" + list(7) + ".png")
      img8.setImage(newImage)
    }
    if (list(8) != null) {
      input9=list(8)
      var newImage: Image = new Image("Images/" + list(8) + ".png")
      button9.visibleProperty().setValue(false)
      img9.visibleProperty().setValue(true)
      remove9.visibleProperty().setValue(true)
      img9.setImage(newImage)
    }
  }

  /*
  IMAGE VIEW 1
   */

  def removeImg1(): Unit = {
    remove1.visibleProperty().setValue(false)
    button1.visibleProperty().setValue(true)
    img1.setImage(null)
    deleteFromFile(input1)
  }

  /*
    IMAGE VIEW 2
   */

  def removeImg2(): Unit = {
    remove2.visibleProperty().setValue(false)
    button2.visibleProperty().setValue(true)
    img2.setImage(null)
    deleteFromFile(input2)
  }

  /*
    IMAGE VIEW 3
   */

  def removeImg3(): Unit = {
    remove3.visibleProperty().setValue(false)
    button3.visibleProperty().setValue(true)
    img3.setImage(null)
    deleteFromFile(input3)
  }

  /*
    IMAGE VIEW 4
   */

  def removeImg4(): Unit = {
    remove4.visibleProperty().setValue(false)
    button4.visibleProperty().setValue(true)
    img4.setImage(null)
    deleteFromFile(input4)
  }

  /*
    IMAGE VIEW 5
   */

  def removeImg5(): Unit = {
    remove5.visibleProperty().setValue(false)
    button5.visibleProperty().setValue(true)
    img5.setImage(null)
    deleteFromFile(input5)
  }

  /*
    IMAGE VIEW 6
   */

  def removeImg6(): Unit = {
    remove6.visibleProperty().setValue(false)
    button6.visibleProperty().setValue(true)
    img6.setImage(null)
    deleteFromFile(input6)
  }

  /*
    IMAGE VIEW 7
   */

  def removeImg7(): Unit = {
    remove7.visibleProperty().setValue(false)
    button7.visibleProperty().setValue(true)
    img7.setImage(null)
    deleteFromFile(input7)
  }

  /*
    IMAGE VIEW 8
   */

  def removeImg8(): Unit = {
    remove8.visibleProperty().setValue(false)
    button8.visibleProperty().setValue(true)
    img8.setImage(null)
    deleteFromFile(input8)
  }

  /*
    IMAGE VIEW 9
   */

  def removeImg9(): Unit = {
    remove9.visibleProperty().setValue(false)
    button9.visibleProperty().setValue(true)
    img9.setImage(null)
    deleteFromFile(input9)
  }

  /*
    LOADS
   */

  def loadButton1(): Unit = {
    if (img2.getImage != null) {
      input1 = JOptionPane.showInputDialog("Search by name:")
      var newImage: Image = new Image("file:src/Images/" + input1 + ".png")
      button1.visibleProperty().setValue(false)
      img1.visibleProperty().setValue(true)
      remove1.visibleProperty().setValue(true)
      img1.setImage(newImage)
      list = list.appended(input1)
      writeToFile(list)
    } else {
      input1 = JOptionPane.showInputDialog("Search by name:")
      var newImage: Image = new Image("file:src/Images/" + input1 + ".png")
      button1.visibleProperty().setValue(false)
      img1.visibleProperty().setValue(true)
      remove1.visibleProperty().setValue(true)
      button2.visibleProperty().setValue(true)
      img1.setImage(newImage)
      list = list.appended(input1)
      writeToFile(list)
    }
  }

  def loadButton2(): Unit = {
    if (img3.getImage != null) {
      input2 = JOptionPane.showInputDialog("Search by name:")
      var newImage: Image = new Image("file:src/Images/" + input2 + ".png")
      button2.visibleProperty().setValue(false)
      img2.visibleProperty().setValue(true)
      remove2.visibleProperty().setValue(true)
      img2.setImage(newImage)
      list = list.appended(input2)
      writeToFile(list)
    } else {
      input2 = JOptionPane.showInputDialog("Search by name:")
      var newImage: Image = new Image("Images/" + input2 + ".png")
      button2.visibleProperty().setValue(false)
      img2.visibleProperty().setValue(true)
      remove2.visibleProperty().setValue(true)
      button3.visibleProperty().setValue(true)
      img2.setImage(newImage)
      list = list.appended(input2)
      writeToFile(list)
    }
  }

  def loadButton3(): Unit = {
    if (img4.getImage != null) {
      input3 = JOptionPane.showInputDialog("Search by name:")
      var newImage: Image = new Image("Images/" + input3 + ".png")
      button3.visibleProperty().setValue(false)
      img3.visibleProperty().setValue(true)
      remove3.visibleProperty().setValue(true)
      img3.setImage(newImage)
      list = list.appended(input3)
      writeToFile(list)
    } else {
      input3 = JOptionPane.showInputDialog("Search by name:")
      var newImage: Image = new Image("Images/" + input3 + ".png")
      button3.visibleProperty().setValue(false)
      img3.visibleProperty().setValue(true)
      remove3.visibleProperty().setValue(true)
      button4.visibleProperty().setValue(true)
      img3.setImage(newImage)
      list = list.appended(input3)
      writeToFile(list)
    }
  }

  def loadButton4(): Unit = {
    if (img5.getImage != null) {
      input4 = JOptionPane.showInputDialog("Search by name:")
      var newImage: Image = new Image("Images/" + input4 + ".png")
      button4.visibleProperty().setValue(false)
      img4.visibleProperty().setValue(true)
      remove4.visibleProperty().setValue(true)
      img4.setImage(newImage)
      list = list.appended(input4)
      writeToFile(list)
    } else {
      input4 = JOptionPane.showInputDialog("Search by name:")
      var newImage: Image = new Image("Images/" + input4 + ".png")
      button4.visibleProperty().setValue(false)
      img4.visibleProperty().setValue(true)
      remove4.visibleProperty().setValue(true)
      button5.visibleProperty().setValue(true)
      img4.setImage(newImage)
      list = list.appended(input4)
      writeToFile(list)
    }
  }

  def loadButton5(): Unit = {
    if (img6.getImage != null) {
      input5 = JOptionPane.showInputDialog("Search by name:")
      var newImage: Image = new Image("Images/" + input5 + ".png")
      button5.visibleProperty().setValue(false)
      img5.visibleProperty().setValue(true)
      remove5.visibleProperty().setValue(true)
      img5.setImage(newImage)
      list = list.appended(input5)
      writeToFile(list)
    } else {
      input5 = JOptionPane.showInputDialog("Search by name:")
      var newImage: Image = new Image("Images/" + input5 + ".png")
      button5.visibleProperty().setValue(false)
      img5.visibleProperty().setValue(true)
      remove5.visibleProperty().setValue(true)
      button6.visibleProperty().setValue(true)
      img5.setImage(newImage)
      list = list.appended(input5)
      writeToFile(list)
    }
  }

  def loadButton6(): Unit = {
    if (img7.getImage != null) {
      input6 = JOptionPane.showInputDialog("Search by name:")
      var newImage: Image = new Image("Images/" + input6 + ".png")
      button6.visibleProperty().setValue(false)
      img6.visibleProperty().setValue(true)
      remove6.visibleProperty().setValue(true)
      img6.setImage(newImage)
      list = list.appended(input6)
      writeToFile(list)
    } else {
      input6 = JOptionPane.showInputDialog("Search by name:")
      var newImage: Image = new Image("Images/" + input6 + ".png")
      button6.visibleProperty().setValue(false)
      img6.visibleProperty().setValue(true)
      remove6.visibleProperty().setValue(true)
      button7.visibleProperty().setValue(true)
      img6.setImage(newImage)
      list = list.appended(input6)
      writeToFile(list)
    }
  }

  def loadButton7(): Unit = {
    if (img8.getImage != null) {
      input7 = JOptionPane.showInputDialog("Search by name:")
      var newImage: Image = new Image("Images/" + input7 + ".png")
      button7.visibleProperty().setValue(false)
      img7.visibleProperty().setValue(true)
      remove7.visibleProperty().setValue(true)
      img7.setImage(newImage)
      list = list.appended(input7)
      writeToFile(list)
    } else {
      input7 = JOptionPane.showInputDialog("Search by name:")
      var newImage: Image = new Image("Images/" + input7 + ".png")
      button7.visibleProperty().setValue(false)
      img7.visibleProperty().setValue(true)
      remove7.visibleProperty().setValue(true)
      button8.visibleProperty().setValue(true)
      img7.setImage(newImage)
      list = list.appended(input7)
      writeToFile(list)
    }
  }

  def loadButton8(): Unit = {
    if (img9.getImage != null) {
      input8 = JOptionPane.showInputDialog("Search by name:")
      var newImage: Image = new Image("Images/" + input8 + ".png")
      button8.visibleProperty().setValue(false)
      img8.visibleProperty().setValue(true)
      remove8.visibleProperty().setValue(true)
      img8.setImage(newImage)
      list = list.appended(input8)
      writeToFile(list)
    } else {
      input8 = JOptionPane.showInputDialog("Search by name:")
      var newImage: Image = new Image("Images/" + input8 + ".png")
      button8.visibleProperty().setValue(false)
      img8.visibleProperty().setValue(true)
      remove8.visibleProperty().setValue(true)
      button9.visibleProperty().setValue(true)
      img8.setImage(newImage)
      list = list.appended(input8)
      writeToFile(list)
    }
  }

  def loadButton9(): Unit = {
    input9 = JOptionPane.showInputDialog("Search by name:")
    var newImage: Image = new Image("Images/" + input9 + ".png")
    button9.visibleProperty().setValue(false)
    img9.visibleProperty().setValue(true)
    remove9.visibleProperty().setValue(true)
    img9.setImage(newImage)
    list = list.appended(input9)
    writeToFile(list)
  }

  /*
  UTILS
   */


  def writeToFile(lst: List[String]):Unit = {
    val pw = new BufferedWriter(new FileWriter(new File("src/Imagens")))
    for (line <- lst) {
      pw.write(line)
      pw.write("\n")
    }
    pw.close()
  }

  private def readFile(): List[String] = {
    val lista = Source.fromFile("src/Imagens").getLines.toList
    lista
  }

  def deleteFromFile(name:String):Unit = {
    list = list.filter(_ != name)
    writeToFile(list)
  }


}
