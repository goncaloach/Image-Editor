package BE
import QuadTree.{BitMap, Coords}

import java.awt.Color
import scala.annotation.tailrec

case class QuadTree(bitMap: BitMap) {

  def makeQTree():QTree[Coords]=QuadTree.makeQTree(bitMap)

  def QTreeToBitMap[A](qt: QTree[Coords]): BitMap = QuadTree.QTreeToBitMap(qt)

  def scale[A](value:Double,qt:QTree[A]):QTree[Coords]=QuadTree.scale(value,qt)

  def mirrorH[A](qt: QTree[Coords]): QTree[Coords] =QuadTree.mirrorH(qt)

  def mirrorV[A](qt:QTree[A]):QTree[Coords]=QuadTree.mirrorV(qt)

  def rotateL[A](qt:QTree[A]):QTree[Coords]=QuadTree.rotateL(qt)

  def rotateR[A](qt:QTree[A]):QTree[Coords]=QuadTree.rotateR(qt)

  def applySepia[A](qt: QTree[A]):QTree[Coords]=QuadTree.applySepia(qt)

  def applyContrast[A](qt:QTree[A],value:Int):QTree[Coords]=QuadTree.applyContrast(qt,value)

  def applyNoise[A](qt:QTree[A]):QTree[Coords]=QuadTree.applyNoise(qt)

  def applyNoisePure[A](qt:QTree[A]):QTree[Coords]=QuadTree.applyNoisePure(qt)

}

object QuadTree {

  type Point = (Int, Int)
  type Coords = (Point, Point)
  type Section = (Coords, Color)
  type BitMap = List[List[Int]]

  //////////////////////////////////////// T1 (1) ///////////////////////////////////////////

  @tailrec
  private def isSameColor2D(lst: List[List[Int]]): Boolean = {  //method that finds if a list of list of int contains the same elements
    @tailrec
    def isSameColor(lst: List[Int]): Boolean = {    //method that finds if a list of int contains the same elements
      lst match {
        case List() => true
        case List(h) => true
        case h :: t => h == t.head && isSameColor(t)
      }
    }

    lst match {
      case List() => true
      case List(h) => isSameColor(h)
      case h :: t => isSameColor(h) == isSameColor(t.head) && h.head == t.head.head && isSameColor2D(t)
    }
  }

  def makeQTree(b: BitMap): QTree[Coords] = {
    def divide(lst: List[List[Int]], x1: Int, y1: Int, x2: Int, y2: Int): QTree[Coords] = {
      lst match {
        case List(List()) => QEmpty
        case List(List(), List()) => QEmpty
        case List() => QEmpty
        case h :: t => if (isSameColor2D(lst)) QLeaf(((x1, y1), (x2, y2)), lst.head.head)
        else {
          val altura = lst.length
          val comprimento = lst.head.length
          val topLeft = lst.slice(0, altura / 2) map (x => x.slice(0, comprimento / 2))                          //divide in 4 lists
          val topRight = lst.slice(0, altura / 2) map (x => x.slice(comprimento / 2, comprimento))
          val botLeft = lst.slice(altura / 2, altura) map (x => x.slice(0, comprimento / 2))
          val botRight = lst.slice(altura / 2, altura) map (x => x.slice(comprimento / 2, comprimento))
          QNode(((x1, y1), (x2, y2)),                                                                             //Create 1 node and recurse on it's children QTree's
            divide(topLeft, x1, y1, x2 - math.ceil(comprimento / 2f).toInt, y2 - math.ceil(altura / 2f).toInt),
            divide(topRight, x1 + comprimento / 2, y1, x2, y2 - math.ceil(altura / 2f).toInt),
            divide(botLeft, x1, y1 + altura / 2, x2 - math.ceil(comprimento / 2f).toInt, y2),
            divide(botRight, x1 + comprimento / 2, y1 + altura / 2, x2, y2))
        }
      }
    }

    divide(b, 0, 0, b.head.length - 1, b.length - 1)
  }

  //////////////////////////////////////// T1 (2) //////////////////////////////////////////7

  def QTreeToBitMap[A](qt: QTree[A]): BitMap = {
    qt match {
      case QEmpty => Nil
      case QLeaf((cords: Coords, color: Int)) =>
        List.fill(cords._2._2 - cords._1._2 + 1)(List.fill(cords._2._1 - cords._1._1 + 1)(color))   //Create a List[List[Int]] in witch all ints are equal
      case QNode(_, one, two, three, four) =>
        val top = concatListH(QTreeToBitMap(one), QTreeToBitMap(two))          //concat quadrant topLeft and TopRight quadrants
        val bot = concatListH(QTreeToBitMap(three), QTreeToBitMap(four))        //concat quadrant botLeft and botRight quadrants
        concatListV(top, bot)                                                   //concat top quadrants with bottom ones
    }
  }

  private def concatListH(lst1: List[List[Int]], lst2: List[List[Int]]): List[List[Int]] = {    //Concat List Horizontally
    (lst1, lst2) match {
      case (Nil, Nil) => Nil
      case (Nil, lst2) => lst2
      case (lst1, Nil) => lst1
      case (h1 :: t1, h2 :: t2) => List(h1 ::: h2) ::: concatListH(t1, t2)
    }
  }

  private def concatListV(list1: List[List[Int]], list2: List[List[Int]]): List[List[Int]] = { //Concat List Vertically
    list1 ::: list2
  }

  //////////////////////////////////////////// T2 //////////////////////////////////////////////

  private def scale[A](valor: Double, qt:QTree[A]): QTree[Coords]={
    def scaleCoords(valor : Double,cords: Coords):Coords={
      new Coords((cords._1._1*valor.toInt,cords._1._2*valor.toInt),
                (Math.ceil((cords._2._1+1)*valor).toInt -1,Math.ceil((cords._2._2+1)*valor).toInt -1))
    }
    qt match {
      case QEmpty => QEmpty
      case QLeaf((cords: Coords, cor: Int)) => QLeaf(scaleCoords(valor, cords), cor)
      case QNode(root: Coords, one, two, three, four) =>
        QNode(scaleCoords(valor, root), scale(valor, one), scale(valor, two), scale(valor, three), scale(valor, four))
    }
  }

  ////////////////////////////////////////// T3,T4 ///////////////////////////////////////

  private def getAltQT[A](qt:QTree[A]):Int={   //Get QTree Height
    qt match {
      case QEmpty=>0
      case QLeaf((cords:Coords,_)) => cords._2._2
      case QNode(cords:Coords,_,_,_,_) => cords._2._2
    }
  }

  private def getCompQT[A](qt:QTree[A]):Int={   //Get QTree Width
    qt match {
      case QEmpty=>0
      case QLeaf((cords:Coords,_)) => cords._2._1
      case QNode(cords:Coords,_,_,_,_) => cords._2._1
    }
  }
                                           //f1 changes coords      //f2 changes the order of nodes
  private def modifyImage[A](qt: QTree[A],f1:Coords=>Coords,f2:QNode[Coords]=>QTree[Coords]):QTree[Coords] = {  //General Method to modify image Coordinates
    def aux[A](q: QTree[A]): QTree[Coords] = {
      q match {
        case QEmpty => QEmpty
        case QLeaf((cords: Coords, color: Int)) => QLeaf((f1(cords), color))
        case QNode(root: Coords, one:QTree[Coords], two:QTree[Coords], three:QTree[Coords], four:QTree[Coords]) =>
          f2(QNode(root: Coords, aux(one), aux(two), aux(three), aux(four)))
      }
    }
    aux(qt)
  }

  private def mirrorHCords(comp:Int)(cords:Coords):Coords = {     //f1
    val x1 = cords._1._1
    val y1 = cords._1._2
    val x2 = cords._2._1
    val y2 = cords._2._2
    ((comp-x2,y1), (comp-x1,y2))
  }

  private def mirrorHQNode(comp:Int)(qn:QNode[Coords]):QTree[Coords]={     //f2
    QNode(mirrorHCords(comp)(qn.value), qn.two, qn.one, qn.four, qn.three)
  }

  private def mirrorVCords(alt:Int)(cords:Coords):Coords = {    //f1
    val x1 = cords._1._1
    val y1 = cords._1._2
    val x2 = cords._2._1
    val y2 = cords._2._2
    ((x1,alt-y2), (x2,alt-y1))
  }

  private def mirrorVQNode(alt:Int)(qn:QNode[Coords]):QTree[Coords]={      //f2
    QNode(mirrorHCords(alt)(qn.value), qn.three, qn.four, qn.one, qn.two)
  }

  private def rotateLCords(comp:Int)(cords:Coords):Coords = {        //f1
    val x1 = cords._1._1
    val y1 = cords._1._2
    val x2 = cords._2._1
    val y2 = cords._2._2
    ((y1,comp-x2), (y2,comp-x1))
  }

  private def rotateLQNode(comp:Int)(qn:QNode[Coords]):QTree[Coords]={     //f2
    QNode(rotateLCords(comp)(qn.value), qn.two, qn.four, qn.one, qn.three)
  }

  private def rotateRCords(alt:Int)(cords:Coords):Coords = {       //f1
    val x1 = cords._1._1
    val y1 = cords._1._2
    val x2 = cords._2._1
    val y2 = cords._2._2
    ((alt-y2,x1), (alt-y1,x2))
  }

  private def rotateRQNode(alt:Int)(qn:QNode[Coords]):QTree[Coords]={      //f2
    QNode(rotateRCords(alt)(qn.value), qn.three, qn.one, qn.four, qn.two)
  }

  def mirrorH[A](qt:QTree[A]):QTree[Coords]={
    val comp = getCompQT(qt)
    modifyImage(qt,mirrorHCords(comp),mirrorHQNode(comp))
  }

  def mirrorV[A](qt:QTree[A]):QTree[Coords]={
    val alt = getAltQT(qt)
    modifyImage(qt,mirrorVCords(alt),mirrorVQNode(alt))
  }

  def rotateL[A](qt:QTree[A]):QTree[Coords]={
    val comp = getCompQT(qt)
    modifyImage(qt,rotateLCords(comp),rotateLQNode(comp))
  }

  def rotateR[A](qt:QTree[A]):QTree[Coords]={
    val alt = getAltQT(qt)
    modifyImage(qt,rotateRCords(alt),rotateRQNode(alt))
  }

  ///////////////////////////////////////// T5 ///////////////////////////////////////////

  private def mapColourEffect[A](f:Color => Color, qt:QTree[A]):QTree[Coords]={    //General Method to modify image colors
    qt match {
      case QEmpty => QEmpty
      case QLeaf((cords : Coords,cor:Int)) => QLeaf(cords,f(HCtoColor(cor)).hashCode())
      case QNode(root:Coords,one,two,three,four) => QNode(root, mapColourEffect(f,one), mapColourEffect(f,two),
                                                                mapColourEffect(f,three), mapColourEffect(f,four))
    }
  }

                                            //General Method to modify image colors (Pure Function)
  private def mapColourEffect_1[A](rndm: RandomWithState, f:(Color,RandomWithState) => (Color,RandomWithState), qt:QTree[A]):(QTree[Coords],RandomWithState)={
    val next = MyRandom(rndm.nextInt._1)
    val next1 = MyRandom(next.nextInt._1)                       //Create 4 different seeds for each node
    val next2 = MyRandom(next1.nextInt._1)
    val next3 = MyRandom(next2.nextInt._1)
    qt match {
      case QEmpty => (QEmpty,rndm)
      case QLeaf((cords : Coords,cor:Int)) =>
        val res:(Color,RandomWithState) = f(HCtoColor(cor),next.nextInt._2)
        (QLeaf(cords,res._1.hashCode()),res._2)
      case QNode(root:Coords,one,two,three,four) => (QNode(root,mapColourEffect_1(next.nextInt._2,f,one)._1,
                                                                mapColourEffect_1(next1.nextInt._2,f,two)._1,
                                                                mapColourEffect_1(next2.nextInt._2,f,three)._1,
                                                                mapColourEffect_1(next3.nextInt._2,f,four)._1), rndm)
    }
  }

  private def HCtoColor(hash : Int):Color={  //hashcode to color
    val lst = ImageUtil.decodeRgb(hash).toList
    new Color(lst.head,lst.tail.head,lst.last)
  }

  private def truncateColor(valor : Int):Int={  //truncate color from 0 to 255
    Math.max(Math.min(255,valor),0)
  }

  private def sepiaEffect(clr : Color):Color={
    val red = clr.getRed
    val green = clr.getGreen
    val blue = clr.getBlue
    val newRed = truncateColor(((red * 0.393) + (green *0.769) + (blue * 0.189)).toInt)
    val newGreen = truncateColor(((red * 0.349) + (green *0.686) + (blue * 0.168)).toInt)
    val newBlue = truncateColor(((red * 0.272) + (green *0.534) + (blue * 0.131)).toInt)
    new Color(newRed,newGreen,newBlue)
  }

  private def contrastEffect(value:Int)(clr : Color):Color={
    def truncateContrast(valor : Int):Int={  //truncate contrast value from -255 to 255
      Math.max(Math.min(255,valor),-255)
    }
    def factor(contrast : Int):Int={
      (259 * (contrast + 255)) / (255 * (259 - contrast))
    }
    val newRed   = truncateColor(factor(truncateContrast(value)) * (clr.getRed   - 128) + 128)
    val newGreen = truncateColor(factor(truncateContrast(value)) * (clr.getGreen - 128) + 128)
    val newBlue  = truncateColor(factor(truncateContrast(value)) * (clr.getBlue  - 128) + 128)
    new Color(newRed,newGreen,newBlue)
  }

  private def noiseEffect(clr:Color):Color={ //(Impure Function)
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

  private def noiseEffectPure(clr:Color, random:RandomWithState):(Color,RandomWithState)={   //(Pure Function)
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

  def applyContrast[A](qt:QTree[A],value:Int):QTree[Coords]={
    mapColourEffect(contrastEffect(value),qt)
  }

  def applyNoise[A](qt:QTree[A]):QTree[Coords]={
    mapColourEffect(noiseEffect,qt)
  }

  def applyNoisePure[A](qt:QTree[A]):QTree[Coords]={
    mapColourEffect_1(MyRandom(12234),noiseEffectPure,qt)._1
  }

}