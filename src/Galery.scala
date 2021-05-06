
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.{Parent, Scene}
import javafx.stage.Stage

class Galery extends Application {

  override def start(primaryStage: Stage): Unit = {
    primaryStage.setTitle("Galery")
    val fxmlLoader = new FXMLLoader(getClass.getResource("ControllerMain.fxml"))
    val mainViewRoot: Parent = fxmlLoader.load()
    val scene = new Scene(mainViewRoot)
    //primaryStage.isMaximized
    primaryStage.setScene(scene)
    primaryStage.show()
  }
}
object FxApp {
  def main(args: Array[String]): Unit = {
    Application.launch(classOf[Galery], args: _*)
  }
}
