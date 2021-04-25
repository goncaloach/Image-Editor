import java.awt.Color
import scala.annotation.tailrec

class QuadTree {

  type Point = (Int, Int)
  type Coords = (Point, Point)
  type Section = (Coords, Color)
  type BitMap = List[List[Int]]


  def isSameColor4(lst2d: List[List[Int]]):Boolean = {

    @tailrec
    def isSameColor[A](lst: List[A]): Boolean = {
      lst match {
        case List() => true
        case List(h) => true
        case h :: t => h == t.head && isSameColor(t)
      }
    }
    isSameColor(lst2d map (x=>isSameColor(x)))
  }
  @tailrec
  private def isSameColor2D(lst: List[List[Int]]): Boolean = {

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
      case h :: t => isSameColor(h) == isSameColor(t.head) && isSameColor2D(t)
    }
  }
  //list.length = altura
  //list.head.length = comprimento

  def makeQTreeOG(b: BitMap): QTree[Coords] = {

    def divide(lst: List[List[Int]], x1: Int, y1: Int, x2: Int, y2: Int): QTree[Coords] = {
      val altura = lst.length
      val comprimento = lst.head.length
      if (lst.isEmpty) QEmpty
      else if (isSameColor2D(lst)) QLeaf((((x1, y1): Point, (x2, y2): Point): Coords, lst.head.head))
      else {
        val topLeft = lst.slice(0, comprimento / 2)            map (x => x.slice(0, altura / 2))
        val topRight = lst.slice(0, comprimento / 2)           map (x => x.slice(altura / 2, altura))
        val botLeft = lst.slice(comprimento / 2, comprimento)  map (x => x.slice(0, altura / 2))
        val botRight = lst.slice(comprimento / 2, comprimento) map (x => x.slice(altura / 2, altura))
        QNode(((x1, y1), (x2, y2)),
          divide(topLeft, x1, y1, x2 - comprimento / 2, y2 - altura / 2),
          divide(topRight, x1 + comprimento / 2, y1, x2, y2 - altura / 2),
          divide(botLeft, x1, y1 + altura / 2, x2 - comprimento / 2, y2),
          divide(botRight, x1 + comprimento / 2, y1 + altura / 2, x2, y2))
      }
    }

    divide(b, 0, 0, b.head.length - 1, b.length - 1)
  }

  def makeQTree(b: BitMap): QTree[Coords] = {

    def divide(lst: List[List[Int]], x1: Int, y1: Int, x2: Int, y2: Int): QTree[Coords] = {
      val altura = lst.length
      val comprimento = lst.head.length
      lst match {
        case List() => QEmpty
        case _ => if (isSameColor2D(lst)) QLeaf((((x1, y1): Point, (x2, y2): Point): Coords, lst.head.head))
                  else{
                      val topLeft = lst.slice(0, comprimento/2)            map (x => x.slice(0, altura/2))
                      val topRight = lst.slice(comprimento/2, comprimento)           map (x => x.slice(0, altura/2))
                      val botLeft = lst.slice(0, comprimento/2)  map (x => x.slice(altura/2, altura))
                      val botRight = lst.slice(comprimento/2, comprimento) map (x => x.slice(altura/2, altura))
                      QNode(((x1, y1), (x2, y2)),
                        divide(topLeft, x1, y1, x2 - comprimento / 2, y2 - altura / 2),
                        divide(topRight, x1 + comprimento / 2, y1, x2, y2 - altura / 2),
                        divide(botLeft, x1, y1 + altura / 2, x2 - comprimento / 2, y2),
                        divide(botRight, x1 + comprimento / 2, y1 + altura / 2, x2, y2))

                  }

      }
    }

    divide(b, 0, 0, b.head.length-1, b.length-1)
  }



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


  def scale[A](valor: Double, qt: QTree[A]): QTree[Any]={

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


  /* em construçao
  private def getCoords(qt: QTree[Coords]): Coords = {
    qt match {
      case QNode(root, one, two, three, four) => root
      case QLeaf(s: Section) =>
        s match {
          case (coords, color) => coords
        }
    }
  }

  private def fillList(list : List[List[Int]],coords: Coords, color : Int): List[List[Int]] ={
    val lista = List(List())
    fillList(lista,coords,color)
  }

  def QTreeToBitMap[A](qt: QTree[Coords]): BitMap = {

    def aux(lst: List[List[Int]], coords: QTree[Coords]): List[List[Int]] = {
      val lista = List.fill(getCoords(qt)._2._2 + 1)(List())
        qt match {
        case QEmpty => List(List())
        case QNode(root, one, two, three, four) =>
          val lista1 = aux(lista, one)
          val lista2 = aux(lista1, two)
          val lista3 = aux(lista2, three)
          aux(lista3, four)
        case QLeaf(coords: Coords) =>
            //fillList(lista,cords,color)
            List(List())

      }

    }
  }*/


}
