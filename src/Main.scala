import QuadTree.{BitMap, Coords, Point, Section}

import java.awt.Color
import scala.runtime.Nothing$

object Main {

  def main(args: Array[String]): Unit = {



    val quad = new QuadTree(ImageUtil.readColorImage("5x3.png").toList map (x=> x.toList))
    val qt = quad.makeQTree()


    val bm = quad.QTreeToBitMap(qt)
    val guardarImagem = ImageUtil.writeImage(bm.toArray map (x=> x.toArray),"output.png","png")
  }

}



