package BE

import BE.QuadTree.{Coords, Point, rotateL}

import java.awt.Point

object Main {

  def main(args: Array[String]): Unit = {

    val nomeFicheiro = "raven"
    val quad = new QuadTree(Utils.readImage(nomeFicheiro))

    val qt = quad.makeQTree()

    //val scaled = quad.scale(2,qt)

    //val mirrorH = quad.mirrorH(qt)
    //val mirrorV = quad.mirrorV(qt)

    //val rotateR = quad.rotateR(qt)
    val rotateL = quad.rotateL(qt)

    //val sepia = quad.applySepia(qt)
    //val contrast = quad.applyContrast(qt,256)
    //val noise = quad.applyNoise(qt)
    //val noisePure = quad.applyNoisePure(qt)

    Utils.saveImage(rotateL,quad,"output")

  }

}



