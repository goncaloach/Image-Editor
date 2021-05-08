
import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.{Parent, Scene, SubScene}
import javafx.scene.control.{Button}
import javafx.scene.layout.AnchorPane
import javafx.stage.{Modality, Stage}

class ControllerMain {
  @FXML
  //Grid View
  private var button1: Button = _
  @FXML
  //Slideshow View
  private var button2: Button = _
  @FXML
  private var exit: Button = _
  @FXML
  private var subscene1: SubScene = _
  @FXML
  private var anchorpane1: AnchorPane = _

  def onButton1Clicked(): Unit = {
    val stage = new Stage();
    stage.setTitle("Grid")
    val fxmlLoader = new FXMLLoader(getClass.getResource("ControllerGrid.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()
    val scene = new Scene(mainViewRoot)
    stage.setScene(scene)
    //Inutiliza a MainStage até a SegundaStage ser fechada
    stage.initModality(Modality.APPLICATION_MODAL)
    stage.show()
  }
  def onButton2Clicked(): Unit = {
    val stage = new Stage();
    stage.setTitle("Slideshow")
    val fxmlLoader = new FXMLLoader(getClass.getResource("ControllerSlideshow.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()
    val scene = new Scene(mainViewRoot)
    stage.setScene(scene)
    //Inutiliza a MainStage até a SegundaStage ser fechada
    stage.initModality(Modality.APPLICATION_MODAL)
    stage.show()
  }

  def onExit(): Unit ={
    val stage = exit.getScene.getWindow.asInstanceOf[Stage]
    stage.close()
  }

}
