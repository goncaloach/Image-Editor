import java.awt.Color

object Main extends QuadTree {

  def main(args: Array[String]): Unit = {

    val qt = makeQTree(ImageUtil.readColorImage("objc2_2.png").toList map (x=> x.toList))
    println(qt)

    /*val v = isSameColor4(List(List(true,false),List(true,true)))
    println(v)*/


    /*
    val bm = qt.QTreeToBitMap(qt)
    val guardarImagem = ImageUtil.writeImage(bm.toArray map (x=> x.toArray),"output.png","png")
    */
  }

}



