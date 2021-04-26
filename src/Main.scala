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

    /*val l1: QLeaf[Coords, Section] = QLeaf((((0,0):Point,(0,0):Point):Coords, Color.red):Section)
    val l2: QLeaf[Coords, Section] = QLeaf((((1,0):Point,(1,0):Point):Coords, Color.blue):Section)
    val l3: QLeaf[Coords, Section] = QLeaf((((0,1):Point,(0,1):Point):Coords, Color.yellow):Section)
    val l4: QLeaf[Coords, Section] = QLeaf((((1,1):Point,(1,1):Point):Coords, Color.green):Section)

    val qt2: QTree[Coords] = QNode(((0,0),(1,1)), l1, l2, l3, l4)
    println(qt2)*/
  }

}



