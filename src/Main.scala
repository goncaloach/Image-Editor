import java.awt.Color

object Main extends QuadTree {

  def main(args: Array[String]): Unit = {

    val qt = makeQTree(ImageUtil.readColorImage("8x8.png").toList map (x=> x.toList))
    println(qt)
    println(scale(2,qt))

    /*
    val bm = qt.QTreeToBitMap(qt)
    val guardarImagem = ImageUtil.writeImage(bm.toArray map (x=> x.toArray),"output.png","png")
    */
  }

}



