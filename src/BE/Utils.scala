package BE

import BE.QuadTree.Coords

import java.io.File

object Utils {

  def readImage(str: String): List[List[Int]] = {
    ImageUtil.readColorImage("src/Images/" + str + ".png").toList map (x => x.toList)
  }

  def saveImage(qt: QTree[Coords], quadT: QuadTree, name: String): Unit = {
    val bm = quadT.QTreeToBitMap(qt)
    ImageUtil.writeImage(bm.toArray map (x => x.toArray), "src/Images/" + name + ".png", "png")
  }

  def deleteFile(path: String): Unit = {
    val fileTemp = new File(path)
    if (fileTemp.exists) {
      fileTemp.delete()
    }
  }

}
