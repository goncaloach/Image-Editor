import java.awt.Color

object Main {

  def main(args: Array[String]): Unit = {

    //val qt = makeQTree(ImageUtil.readColorImage("3x4.png").toList map (x=> x.toList))
    //println(qt)
    val quad = new QuadTree(ImageUtil.readColorImage("3x4.png").toList map (x=> x.toList))

    val qt = quad.makeQTree()
    println(qt)

    //val qtScale = quad.scale(2,qt)
    //println(qtScale)


    /*
    val bm = qt.QTreeToBitMap(qt)
    val guardarImagem = ImageUtil.writeImage(bm.toArray map (x=> x.toArray),"output.png","png")
    */

  }

}



