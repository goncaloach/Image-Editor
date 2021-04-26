import java.awt.Color

object Main extends QuadTree {

  def main(args: Array[String]): Unit = {

    val qt = makeQTree(ImageUtil.readColorImage("3x4.png").toList map (x=> x.toList))
    println(qt)

    /*
    val bm = qt.QTreeToBitMap(qt)
    val guardarImagem = ImageUtil.writeImage(bm.toArray map (x=> x.toArray),"output.png","png")
    */

  }

}



