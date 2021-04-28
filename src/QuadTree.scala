import QuadTree.{BitMap, Coords}

import java.awt.Color
import scala.annotation.tailrec

case class QuadTree(bitMap: BitMap) {

  def makeQTree():QTree[Coords]=QuadTree.makeQTree(bitMap)

  def scale[A](value:Double,qt:QTree[A]):QTree[Coords]=QuadTree.scale(value,qt)

  def rotateL[A](qt:QTree[A]):QTree[Any]=QuadTree.rotateL(qt)

  def rotateR[A](qt:QTree[A]):QTree[Any]=QuadTree.rotateR(qt)

  def QTreeToBitMap[A](qt: QTree[Coords]): BitMap = QuadTree.QTreeToBitMap(qt)
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
        case List() => QEmpty
        case h::t =>
          val altura = lst.length
          val comprimento = lst.head.length
          if (isSameColor2D(lst)) QLeaf(((x1, y1),(x2, y2)), lst.head.head)
          else{
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

  def scale[A](valor: Double, qt: QTree[A]): QTree[Coords]={

    def scaleCoords(valor : Double,coords: Coords):Coords={
      val newCoords = new Coords(((valor*coords._1._1).toInt,(valor*coords._1._2).toInt):Point,
        ((valor*coords._2._1).toInt,(valor*coords._2._2).toInt):Point)
      newCoords
    }

    qt match {
      case QEmpty => QEmpty
      case QLeaf(s:Section) => QLeaf(scaleCoords(valor,s._1:Coords),s._2)
      case QNode(root:Coords, one, two, three, four) => QNode(scaleCoords(valor,root),scale(valor,one),scale(valor,two),scale(valor,three),scale(valor,four))

    }
  }

  //////////////////////////////////////////////////////////////////////////////////

  def rotateR[A](qt: QTree[A]): QTree[A] = qt match {
    case QEmpty => QEmpty
    case QLeaf(value) => QLeaf(value)
    case QNode(root, one, two, three, four) => QNode(root, rotateR(three), rotateR(one), rotateR(four), rotateR(two))
  }

  def rotateL[A](qt: QTree[A]): QTree[A] = qt match {
    case QEmpty => QEmpty
    case QLeaf(value) => QLeaf(value)
    case QNode(root, one, two, three, four) => QNode(root, rotateL(two), rotateL(four), rotateL(one), rotateL(three))
  }

  def QTreeToBitMap[A](qt: QTree[A]):BitMap = {
    qt match{
      case QEmpty => List()
      case QLeaf((cords:Coords,color:Int)) =>
        List.fill(cords._2._2 - cords._1._2 + 1)(List.fill(cords._2._1 - cords._1._1+1)(color))
      case QNode(root,one,two,three,four) =>
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
      case (List(h1), List(h2)) => List(h1 ::: h2)
      case (h1 :: t1, h2 :: t2) => List(h1 ::: h2) ::: concatListH(t1,t2)
    }
  }

  private def concatListV(list1: List[List[Int]],list2: List[List[Int]]): List[List[Int]] = {
    list1 ::: list2
  }

}

