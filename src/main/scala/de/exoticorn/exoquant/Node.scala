package de.exoticorn.exoquant

class Node(val colors: Seq[(Color, Int)]) {
  val count = colors.foldLeft(0) { _ + _._2 }
  val sum = colors.foldLeft(Color(0, 0, 0, 0)) { (acc, c) => acc + c._1 * c._2 }
  val sum2 = colors.foldLeft(Color(0, 0, 0, 0)) { (acc, c) => acc + c._1 * c._1 * c._2 }

  val (avg, dir, vdif, err) = if (count == 0) (Color(0, 0, 0, 0), Color(1, 0, 0, 0), 0, 0)
  else {
    val avg = sum * (1 / count)

    val vc = sum2 - sum * avg

    val v = vc.r + vc.g + vc.b + vc.a

    val sortedColors = if (vc.r > vc.g && vc.r > vc.b && vc.r > vc.a) colors.sortBy(_._1.r)
    else if (vc.g > vc.b && vc.g > vc.a) colors.sortBy(_._1.g)
    else if (vc.b > vc.a) colors.sortBy(_._1.b)
    else colors.sortBy(_._1.a)
    
    val dir = colors.foldLeft(Color(0, 0, 0, 0)) { (acc, c) =>
      val tmp = (c._1 - avg) * c._2
      acc + (if(tmp.dot(acc) < 0) tmp * -1 else tmp)
    }.normalize
    
    val finalSortedColors = colors.sortBy(_._1.dot(dir))

    (avg, dir, 0, 0)
  }
}