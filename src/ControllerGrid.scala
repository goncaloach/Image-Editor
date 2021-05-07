import BE.QuadTree
import BE.{QTree, QuadTree}
import BE.QuadTree.Coords
import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.{Parent, Scene}
import javafx.scene.control.{Button, ComboBox, MenuButton, MenuItem}
import javafx.scene.image.{Image, ImageView}
import javafx.stage.{Modality, Stage}

import javax.swing.JOptionPane
class ControllerGrid {

  @FXML
  private var button1:Button = _
  @FXML
  private var button2:Button = _
  @FXML
  private var button3:Button = _
  @FXML
  private var button4:Button = _
  @FXML
  private var button5:Button = _
  @FXML
  private var button6:Button = _
  @FXML
  private var button7:Button = _
  @FXML
  private var button8:Button = _
  @FXML
  private var button9:Button = _
  @FXML
  private var img1:ImageView = _
  @FXML
  private var img2:ImageView = _
  @FXML
  private var img3:ImageView = _
  @FXML
  private var img4:ImageView = _
  @FXML
  private var img5:ImageView = _
  @FXML
  private var img6:ImageView = _
  @FXML
  private var img7:ImageView = _
  @FXML
  private var img8:ImageView = _
  @FXML
  private var img9:ImageView = _
  @FXML
  private var remove1:Button = _
  @FXML
  private var remove2:Button = _
  @FXML
  private var remove3:Button = _
  @FXML
  private var remove4:Button = _
  @FXML
  private var remove5:Button = _
  @FXML
  private var remove6:Button = _
  @FXML
  private var remove7:Button = _
  @FXML
  private var remove8:Button = _
  @FXML
  private var remove9:Button = _
  @FXML
  private var edit1:Button = _
  @FXML
  private var edit2:Button = _
  @FXML
  private var edit3:Button = _
  @FXML
  private var edit4:Button = _
  @FXML
  private var edit5:Button = _
  @FXML
  private var edit6:Button = _
  @FXML
  private var edit7:Button = _
  @FXML
  private var edit8:Button = _
  @FXML
  private var edit9:Button = _

  def onEdit(): Unit ={
    val stage = new Stage();
    stage.setTitle("Edit Window")
    val fxmlLoader = new FXMLLoader(getClass.getResource("ControllerSlideshow.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()
    val scene = new Scene(mainViewRoot)
    stage.setScene(scene)
    //Inutiliza a MainStage até a SegundaStage ser fechada
    stage.initModality(Modality.APPLICATION_MODAL)
    stage.show()
  }

  def removeImg1(): Unit ={
    remove1.visibleProperty().setValue(false)
    button1.visibleProperty().setValue(true)
    edit1.visibleProperty().setValue(false)
    img1.setImage(null)
  }

  def removeImg2(): Unit ={
    remove2.visibleProperty().setValue(false)
    button2.visibleProperty().setValue(true)
    edit2.visibleProperty().setValue(false)
    img2.setImage(null)
  }

  def removeImg3(): Unit ={
    remove3.visibleProperty().setValue(false)
    button3.visibleProperty().setValue(true)
    edit3.visibleProperty().setValue(false)
    img3.setImage(null)
  }

  def removeImg4(): Unit ={
    remove4.visibleProperty().setValue(false)
    button4.visibleProperty().setValue(true)
    edit4.visibleProperty().setValue(false)
    img4.setImage(null)
  }

  def removeImg5(): Unit ={
    remove5.visibleProperty().setValue(false)
    button5.visibleProperty().setValue(true)
    edit5.visibleProperty().setValue(false)
    img5.setImage(null)
  }

  def removeImg6(): Unit ={
    remove6.visibleProperty().setValue(false)
    button6.visibleProperty().setValue(true)
    edit6.visibleProperty().setValue(false)
    img6.setImage(null)
  }

  def removeImg7(): Unit ={
    remove7.visibleProperty().setValue(false)
    button7.visibleProperty().setValue(true)
    edit7.visibleProperty().setValue(false)
    img7.setImage(null)
  }

  def removeImg8(): Unit ={
    remove8.visibleProperty().setValue(false)
    button8.visibleProperty().setValue(true)
    edit8.visibleProperty().setValue(false)
    img8.setImage(null)
  }

  def removeImg9(): Unit ={
    remove9.visibleProperty().setValue(false)
    button9.visibleProperty().setValue(true)
    edit9.visibleProperty().setValue(false)
    img9.setImage(null)
  }

  def loadButton1(): Unit = {
    if (img2.getImage != null) {
      val path: String = JOptionPane.showInputDialog("Search by name:")
      var newImage: Image = new Image("file:src/Images/" + path + ".png")
      button1.visibleProperty().setValue(false)
      img1.visibleProperty().setValue(true)
      remove1.visibleProperty().setValue(true)
      edit1.visibleProperty().setValue(true)
      img1.setImage(newImage)
    } else {
      val path: String = JOptionPane.showInputDialog("Search by name:")
      var newImage: Image = new Image("file:src/Images/" + path + ".png")
      button1.visibleProperty().setValue(false)
      img1.visibleProperty().setValue(true)
      remove1.visibleProperty().setValue(true)
      button2.visibleProperty().setValue(true)
      edit1.visibleProperty().setValue(true)
      img1.setImage(newImage)
    }
  }
  def loadButton2(): Unit = {
    if (img3.getImage != null) {
      val path: String = JOptionPane.showInputDialog("Search by name:")
      var newImage: Image = new Image("Images/" + path + ".png")
      button2.visibleProperty().setValue(false)
      img2.visibleProperty().setValue(true)
      remove2.visibleProperty().setValue(true)
      edit2.visibleProperty().setValue(true)
      img2.setImage(newImage)
    } else {
      val path: String = JOptionPane.showInputDialog("Search by name:")
      var newImage: Image = new Image("Images/" + path + ".png")
      button2.visibleProperty().setValue(false)
      img2.visibleProperty().setValue(true)
      remove2.visibleProperty().setValue(true)
      button3.visibleProperty().setValue(true)
      edit2.visibleProperty().setValue(true)
      img2.setImage(newImage)
    }
  }
  def loadButton3(): Unit = {
    if (img4.getImage != null) {
      val path: String = JOptionPane.showInputDialog("Search by name:")
      var newImage: Image = new Image("Images/" + path + ".png")
      button3.visibleProperty().setValue(false)
      img3.visibleProperty().setValue(true)
      remove3.visibleProperty().setValue(true)
      edit3.visibleProperty().setValue(true)
      img3.setImage(newImage)
    } else {
      val path: String = JOptionPane.showInputDialog("Search by name:")
      var newImage: Image = new Image("Images/" + path + ".png")
      button3.visibleProperty().setValue(false)
      img3.visibleProperty().setValue(true)
      remove3.visibleProperty().setValue(true)
      button4.visibleProperty().setValue(true)
      edit3.visibleProperty().setValue(true)
      img3.setImage(newImage)
    }
  }
  def loadButton4(): Unit = {
    if (img5.getImage != null) {
      val path: String = JOptionPane.showInputDialog("Search by name:")
      var newImage: Image = new Image("Images/" + path + ".png")
      button4.visibleProperty().setValue(false)
      img4.visibleProperty().setValue(true)
      remove4.visibleProperty().setValue(true)
      edit4.visibleProperty().setValue(true)
      img4.setImage(newImage)
    } else {
      val path: String = JOptionPane.showInputDialog("Search by name:")
      var newImage: Image = new Image("Images/" + path + ".png")
      button4.visibleProperty().setValue(false)
      img4.visibleProperty().setValue(true)
      remove4.visibleProperty().setValue(true)
      button5.visibleProperty().setValue(true)
      edit4.visibleProperty().setValue(true)
      img4.setImage(newImage)
    }
  }
  def loadButton5(): Unit = {
    if (img6.getImage != null) {
      val path: String = JOptionPane.showInputDialog("Search by name:")
      var newImage: Image = new Image("Images/" + path + ".png")
      button5.visibleProperty().setValue(false)
      img5.visibleProperty().setValue(true)
      remove5.visibleProperty().setValue(true)
      edit5.visibleProperty().setValue(true)
      img5.setImage(newImage)
    } else {
      val path: String = JOptionPane.showInputDialog("Search by name:")
      var newImage: Image = new Image("Images/" + path + ".png")
      button5.visibleProperty().setValue(false)
      img5.visibleProperty().setValue(true)
      remove5.visibleProperty().setValue(true)
      button6.visibleProperty().setValue(true)
      edit5.visibleProperty().setValue(true)
      img5.setImage(newImage)
    }
  }
  def loadButton6(): Unit = {
    if (img7.getImage != null) {
      val path: String = JOptionPane.showInputDialog("Search by name:")
      var newImage: Image = new Image("Images/" + path + ".png")
      button6.visibleProperty().setValue(false)
      img6.visibleProperty().setValue(true)
      remove6.visibleProperty().setValue(true)
      edit6.visibleProperty().setValue(true)
      img6.setImage(newImage)
    } else {
      val path: String = JOptionPane.showInputDialog("Search by name:")
      var newImage: Image = new Image("Images/" + path + ".png")
      button6.visibleProperty().setValue(false)
      img6.visibleProperty().setValue(true)
      remove6.visibleProperty().setValue(true)
      button7.visibleProperty().setValue(true)
      edit6.visibleProperty().setValue(true)
      img6.setImage(newImage)
    }
  }
  def loadButton7(): Unit = {
    if (img8.getImage != null) {
      val path: String = JOptionPane.showInputDialog("Search by name:")
      var newImage: Image = new Image("Images/" + path + ".png")
      button7.visibleProperty().setValue(false)
      img7.visibleProperty().setValue(true)
      remove7.visibleProperty().setValue(true)
      edit7.visibleProperty().setValue(true)
      img7.setImage(newImage)
    } else {
      val path: String = JOptionPane.showInputDialog("Search by name:")
      var newImage: Image = new Image("Images/" + path + ".png")
      button7.visibleProperty().setValue(false)
      img7.visibleProperty().setValue(true)
      remove7.visibleProperty().setValue(true)
      button8.visibleProperty().setValue(true)
      edit7.visibleProperty().setValue(true)
      img7.setImage(newImage)
    }
  }

  def loadButton8(): Unit = {
    if (img9.getImage != null) {
      val path: String = JOptionPane.showInputDialog("Search by name:")
      var newImage: Image = new Image("Images/" + path + ".png")
      button8.visibleProperty().setValue(false)
      img8.visibleProperty().setValue(true)
      remove8.visibleProperty().setValue(true)
      edit8.visibleProperty().setValue(true)
      img8.setImage(newImage)
    } else {
      val path: String = JOptionPane.showInputDialog("Search by name:")
      var newImage: Image = new Image("Images/" + path + ".png")
      button8.visibleProperty().setValue(false)
      img8.visibleProperty().setValue(true)
      remove8.visibleProperty().setValue(true)
      button9.visibleProperty().setValue(true)
      edit8.visibleProperty().setValue(true)
      img8.setImage(newImage)
    }
  }
  def loadButton9(): Unit = {
      val path: String = JOptionPane.showInputDialog("Search by name:")
      var newImage: Image = new Image("Images/" + path + ".png")
      button9.visibleProperty().setValue(false)
      img9.visibleProperty().setValue(true)
      remove9.visibleProperty().setValue(true)
      edit9.visibleProperty().setValue(true)
      img9.setImage(newImage)
    }



}
