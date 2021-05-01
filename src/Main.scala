import java.awt.Color

object Main {

  def main(args: Array[String]): Unit = {
    //val nomeFicheiro = "3x1"
    //val quad = new QuadTree(ImageUtil.readColorImage(nomeFicheiro+".png").toList map (x=> x.toList))

    val quad = new QuadTree(ImageUtil.readColorImage("lul.png").toList map (x=> x.toList))
    val qt = quad.makeQTree()


    //val scaled = quad.scale(2,qt)
    //println(scaled)

    //val sepia = quad.applySepia(qt)
    //val contrast = quad.applyContrast(qt)
    //val noise = quad.applyNoise(qt)
    val noisePure = quad.applyNoisePure(qt)


    val bm = quad.QTreeToBitMap(noisePure)
    ImageUtil.writeImage(bm.toArray map (x=> x.toArray),"output.png","png")


    //ImageUtil.writeImage(bm.toArray map (x=> x.toArray),"new"+nomeFicheiro+".png","png")
  }

}



