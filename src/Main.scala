
object Main {

  def main(args: Array[String]): Unit = {

    val nomeFicheiro = "Barack_Obama"
    val quad = new QuadTree(Utils.readImage(nomeFicheiro))
    //println(quad)
    val qt = quad.makeQTree()
    //println("Original "+qt)

    //val scaled = quad.scale(1.5,qt)
    //println("Scaled "+scaled)

    //val mirrorH = quad.mirrorH(qt)
    //val mirrorV = quad.mirrorV(qt)

    //val rotateR = quad.rotateR(qt)
    //val rotateL = quad.rotateL(qt)

    //val sepia = quad.applySepia(qt)
    //val contrast = quad.applyContrast(qt,256)
    //val noise = quad.applyNoise(qt)
    //val noisePure = quad.applyNoisePure(qt)

    Utils.saveImageOutput(qt,quad)
  }

}



