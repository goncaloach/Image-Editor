package BE

import BE.QuadTree.Coords
import BE.QuadTree.Point

import java.awt.Point

object Main {

  def main(args: Array[String]): Unit = {

    val nomeFicheiro = "objc2_2"
    val quad = new QuadTree(Utils.readImage(nomeFicheiro))
    //println(quad)
    val qt = quad.makeQTree()
    //println("Original "+qt)

    val scaled = quad.scale(2,qt)
    println("Scaled "+scaled)

    //val mirrorH = quad.mirrorH(qt)
    //val mirrorV = quad.mirrorV(qt)

    //val rotateR = quad.rotateR(qt)
    //val rotateL = quad.rotateL(qt)

    //val sepia = quad.applySepia(qt)
    //val contrast = quad.applyContrast(qt,256)
    //val noise = quad.applyNoise(qt)
    //val noisePure = quad.applyNoisePure(qt)

    Utils.saveImage(scaled,quad,"output")
    val scaleddows = quad.scale(0.5,scaled)
    println(scaleddows)
    Utils.saveImage(scaleddows,quad,"output")

  }


}



