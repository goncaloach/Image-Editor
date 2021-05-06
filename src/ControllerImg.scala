
import javafx.fxml.FXML
import java.awt.Button
import javafx.scene.image.{Image, ImageView}

class ControllerImg {

  @FXML
  private var img:ImageView = _
  @FXML
  private var button1:Button = _

 /* def onRotateRightClick(): Unit ={
    println("Hello")
    img.getImage.impl_getUrl().toString
    val quad = new QuadTree(ImageUtil.readColorImage(img.getImage.impl_getUrl().toString).toList map (x=> x.toList))
    val qt = quad.makeQTree()
    val effect = quad.applyNoisePure(qt)
    val bm = quad.QTreeToBitMap(effect)
    ImageUtil.writeImage(bm.toArray map (x=> x.toArray),img.getImage.impl_getUrl().toString,"png")
    var newImage:Image = new Image(img.getImage.impl_getUrl().toString)
    img.setImage(newImage)

  }*/

  def onRotateLeftClick(): Unit ={

  }

  def onEffectSepiaClick(): Unit ={

  }

  def onEffectNoiseClick(): Unit ={

  }

  def onEffectContrastClick(): Unit ={

  }

}
