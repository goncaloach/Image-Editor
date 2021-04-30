object Main {

  def main(args: Array[String]): Unit = {
    //val nomeFicheiro = "3x1"
    //val quad = new QuadTree(ImageUtil.readColorImage(nomeFicheiro+".png").toList map (x=> x.toList))


    val quad = new QuadTree(ImageUtil.readColorImage("3x2g.png").toList map (x=> x.toList))
    //println(quad)
    val qt = quad.makeQTree()
    //println(qt)

    //val scaled = quad.scale(2,qt)
    //println(scaled)

    val eff = quad.mapColourEffect(c => c,qt)


    val bm = quad.QTreeToBitMap(eff)
    val guardarImagem = ImageUtil.writeImage(bm.toArray map (x=> x.toArray),"output.png","png")


    //val guardarImagem = ImageUtil.writeImage(bm.toArray map (x=> x.toArray),"new"+nomeFicheiro+".png","png")
  }

}



