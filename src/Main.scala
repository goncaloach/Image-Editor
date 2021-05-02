import QuadTree.Coords

import java.awt.Color
import scala.annotation.tailrec

object Main {

  def main(args: Array[String]): Unit = {
    //val nomeFicheiro = "lul"
    //val quad = new QuadTree(ImageUtil.readColorImage(nomeFicheiro+".png").toList map (x=> x.toList))

    val quad = new QuadTree(ImageUtil.readColorImage("1x3.png").toList map (x=> x.toList))
    println(quad)
    val qt = quad.makeQTree()
    println(qt)

    //val scaled = quad.scale(2,qt)
    //println(scaled)

    //val mirrorH = quad.mirrorH(qt)
    //val mirrorV = quad.mirrorV(qt)

    val rotateR = quad.rotateR(qt)
    println(rotateR)
    //val rotateL = quad.rotateL(qt)

    //val sepia = quad.applySepia(qt)
    //val contrast = quad.applyContrast(qt,256)
    //val noise = quad.applyNoise(qt)
    //val noisePure = quad.applyNoisePure(qt)


    val bm = quad.QTreeToBitMap(qt)
    ImageUtil.writeImage(bm.toArray map (x=> x.toArray),"output.png","png")



    //ImageUtil.writeImage(bm.toArray map (x=> x.toArray),"new"+nomeFicheiro+".png","png")
  }

}



