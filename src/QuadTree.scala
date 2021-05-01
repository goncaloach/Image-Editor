import QuadTree.{BitMap, Coords}

import java.awt.Color
import scala.annotation.tailrec

case class QuadTree(bitMap: BitMap) {

  def makeQTree():QTree[Coords]=QuadTree.makeQTree(bitMap)

  def QTreeToBitMap[A](qt: QTree[Coords]): BitMap = QuadTree.QTreeToBitMap(qt)

  def scale[A](value:Double,qt:QTree[A]):QTree[Coords]=QuadTree.scale(value,qt)

  def rotateL[A](qt:QTree[A]):QTree[Coords]=QuadTree.rotateL(qt)

  def rotateR[A](qt:QTree[A]):QTree[Coords]=QuadTree.rotateR(qt)

  def applySepia[A](qt: QTree[A]):QTree[Coords]=QuadTree.applySepia(qt)

  def applyContrast[A](qt:QTree[A]):QTree[Coords]=QuadTree.applyContrast(qt)

  def applyNoise[A](qt:QTree[A]):QTree[Coords]=QuadTree.applyNoise(qt)

  def applyNoisePure[A](qt:QTree[A]):QTree[Coords]=QuadTree.applyNoisePure(qt)

}

object QuadTree{

  type Point = (Int, Int)
  type Coords = (Point, Point)
  type Section = (Coords, Color)
  type BitMap = List[List[Int]]

  @tailrec
  private def isSameColor2D(lst:List[List[Int]]):Boolean={
    @tailrec
    def isSameColor(lst: List[Int]): Boolean = {
      lst match {
        case List() => true
        case List(h) => true
        case h :: t => h == t.head && isSameColor(t)
      }
    }
    lst match {
      case List() => true
      case List(h) => isSameColor(h)
      case h::t => isSameColor(h) == isSameColor(t.head) && h.head==t.head.head && isSameColor2D(t)
    }
  }

  def makeQTree(b: BitMap): QTree[Coords] = {

    def divide(lst: List[List[Int]],x1: Int,y1: Int,x2: Int,y2: Int): QTree[Coords] = {
      lst match {
        case List(List())  => QEmpty
        case List()  => QEmpty
        case h::t =>
          if (isSameColor2D(lst)) {
            QLeaf(((x1, y1),(x2, y2)), lst.head.head)
          } else{
            val altura = lst.length
            val comprimento = lst.head.length
            val topLeft = lst.slice(0, altura/2)          map (x => x.slice(0, comprimento/2) )
            val topRight = lst.slice(0, altura/2)         map (x => x.slice(comprimento/2, comprimento))
            val botLeft = lst.slice(altura/2,altura)      map (x => x.slice(0, comprimento/2))
            val botRight = lst.slice(altura/2,altura)     map (x => x.slice(comprimento/2, comprimento))
            QNode(((x1, y1), (x2, y2)),
              divide(topLeft, x1, y1, x2-math.ceil(comprimento/2f).toInt, y2-math.ceil(altura/2f).toInt),
              divide(topRight, x1+math.floor(comprimento/2f).toInt, y1, x2, y2-math.ceil(altura/2f).toInt),
              divide(botLeft, x1, y1+math.floor(altura/2f).toInt, x2-math.ceil(comprimento/2f).toInt, y2),
              divide(botRight, x1+math.floor(comprimento/2f).toInt, y1+math.floor(altura/2f).toInt, x2, y2))
          }
      }
    }
    divide(b,0,0,b.head.length-1,b.length-1)
  }

  //////////////////////////////////////////////////////////////////////////////////

  def QTreeToBitMap[A](qt: QTree[A]):BitMap = {
    qt match{
      case QEmpty => Nil
      case QLeaf((cords:Coords,color:Int)) =>
        List.fill(cords._2._2 - cords._1._2 + 1)(List.fill(cords._2._1 - cords._1._1+1)(color))
      case QNode(_,one,two,three,four) =>
        val top = concatListH(QTreeToBitMap(one),QTreeToBitMap(two))
        val bot = concatListH(QTreeToBitMap(three),QTreeToBitMap(four))
        concatListV(top,bot)
    }
  }

  private def concatListH(lst1 : List[List[Int]],lst2:List[List[Int]]):List[List[Int]]= {
    (lst1, lst2) match {
      case (List(), List()) => List()
      case (Nil, List(h2)) => List(h2)
      case (List(h1), Nil) => List(h1)
      case (h1 :: t1, h2 :: t2) => List(h1 ::: h2) ::: concatListH(t1,t2)
    }
  }

  private def concatListV(list1: List[List[Int]],list2: List[List[Int]]): List[List[Int]] = {
    list1 ::: list2
  }

  //////////////////////////////////////////////////////////////////////////////////////////

  def scale[A](valor: Double, qt: QTree[A]): QTree[Coords]={

    def scaleCoords(valor : Double,coords: Coords):Coords={
      val altura = (coords._2._2 - coords._1._2 + 1)*valor.toInt
      val comprimento = (coords._2._1 - coords._1._1 + 1)*valor.toInt
      new Coords((coords._1._1*valor.toInt,coords._1._2*valor.toInt),(coords._2._1*valor.toInt+comprimento-1,coords._2._2*valor.toInt+altura-1))
    }

    /*def scaleCoords(valor : Double,coords: Coords):Coords={
      val newCoords = new Coords(((valor*coords._1._1).toInt,(valor*coords._1._2).toInt):Point,
        ((valor*coords._2._1).toInt,(valor*coords._2._2).toInt):Point)
      newCoords
    }*/


    qt match {
      case QEmpty => QEmpty
      case QLeaf((cords : Coords,cor:Int)) => QLeaf(scaleCoords(valor,cords),cor)
      case QNode(root:Coords, one, two, three, four) => QNode(scaleCoords(valor,root),scale(valor,one),scale(valor,two),scale(valor,three),scale(valor,four))

    }
  }

  //////////////////////////////////////////////////////////////////////////////////

  def rotateR[A](qt: QTree[A]): QTree[Coords] = {
    qt match {
      case QEmpty => QEmpty
      case QLeaf(value) => QLeaf(value)
      case QNode(root:Coords, one, two, three, four) =>
        QNode(root:Coords, rotateR(three), rotateR(one), rotateR(four), rotateR(two))
    }
  }

  def rotateL[A](qt: QTree[A]): QTree[Coords] = {
    qt match {
      case QEmpty => QEmpty
      case QLeaf(value) => QLeaf(value)
      case QNode(root:Coords, one, two, three, four) =>
        QNode(root, rotateL(two), rotateL(four), rotateL(one), rotateL(three))
    }
  }

  ////////////////////////////////////////////////////////////////////////////////////

  def mapColourEffect[A](f:Color => Color, qt:QTree[A]):QTree[Coords]={
    qt match {
      case QEmpty => QEmpty
      case QLeaf((cords : Coords,cor:Int)) => QLeaf(cords,f(HCtoColor(cor)).hashCode())
      case QNode(root:Coords,one,two,three,four) => QNode(root, mapColourEffect(f,one), mapColourEffect(f,two),
                                                                mapColourEffect(f,three), mapColourEffect(f,four))
    }
  }

  def mapColourEffect_1[A](rndm: RandomWithState, f:(Color,RandomWithState) => (Color,RandomWithState), qt:QTree[A]):(QTree[Coords],RandomWithState)={
    val next = rndm.nextInt(2)
    val next1 = next._2.nextInt(234)
    val next2 = next1._2.nextInt(5685678)
    val next3 = next2._2.nextInt(234764)
    qt match {
      case QEmpty => (QEmpty,rndm)
      case QLeaf((cords : Coords,cor:Int)) =>
        val res:(Color,RandomWithState) = f(HCtoColor(cor),next._2)
        (QLeaf(cords,res._1.hashCode()),res._2)
      case QNode(root:Coords,one,two,three,four) => (QNode(root,mapColourEffect_1(next._2,f,one)._1, mapColourEffect_1(next1._2,f,two)._1,
        mapColourEffect_1(next2._2,f,three)._1, mapColourEffect_1(next3._2,f,four)._1),rndm)
    }
  }

  private def HCtoColor(hash : Int):Color={  //hashcode to color
    val lst = ImageUtil.decodeRgb(hash).toList
    new Color(lst.head,lst.tail.head,lst.last)
  }

  private def truncate(valor : Int):Int={
    Math.max(Math.min(255,valor),0)
  }

  private def sepiaEffect(clr : Color):Color={
    val red = clr.getRed
    val green = clr.getGreen
    val blue = clr.getBlue
    val newRed = truncate(((red * 0.393) + (green *0.769) + (blue * 0.189)).toInt)
    val newGreen = truncate(((red * 0.349) + (green *0.686) + (blue * 0.168)).toInt)
    val newBlue = truncate(((red * 0.272) + (green *0.534) + (blue * 0.131)).toInt)
    new Color(newRed,newGreen,newBlue)
  }

  private def contrastEffect(clr : Color):Color={ //falta mudar o 128 para parametro de entrada
    def factor(contrast : Int):Int={
      (259 * (contrast + 255)) / (255 * (259 - contrast))
    }
    val newRed   = truncate(factor(128) * (clr.getRed   - 128) + 128)
    val newGreen = truncate(factor(128) * (clr.getGreen - 128) + 128)
    val newBlue  = truncate(factor(128) * (clr.getBlue  - 128) + 128)
    new Color(newRed,newGreen,newBlue)
  }

  private def noiseEffect(clr:Color):Color={
    val rndm = new scala.util.Random
    rndm.nextInt(2) match{
      case 0 => clr
      case 1 =>  Color.gray        //noise with gray
      /*val r = rndm.nextInt(256)  //noise with random color
      val g = rndm.nextInt(256)
      val b = rndm.nextInt(256)
      new Color(r,g,b)*/
    }
  }

  private def noiseEffectPure(clr:Color, random:RandomWithState):(Color,RandomWithState)={
    val bool = random.nextInt(2)
    bool._1 match{
      case 0 => (clr,bool._2)
      case 1 => //(Color.gray,bool._2)  //noise with gray
        val r = bool._2.nextInt(256)     //noise with random color
        val g = r._2.nextInt(256)
        val b = g._2.nextInt(256)
        (new Color(r._1,g._1,b._1),b._2)
    }
  }

  def applySepia[A](qt: QTree[A]):QTree[Coords]={
    mapColourEffect(sepiaEffect,qt)
  }

  def applyContrast[A](qt:QTree[A]):QTree[Coords]={
    mapColourEffect(contrastEffect,qt)
  }

  def applyNoise[A](qt:QTree[A]):QTree[Coords]={
    mapColourEffect(noiseEffect,qt)
  }

  def applyNoisePure[A](qt:QTree[A]):QTree[Coords]={
    mapColourEffect_1(MyRandom(12234),noiseEffectPure,qt)._1
  }

}

