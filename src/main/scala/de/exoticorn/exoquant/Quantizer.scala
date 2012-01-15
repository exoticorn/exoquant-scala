package de.exoticorn.exoquant

class Quantizer {
  var numColors = 0
  var optimized = false
  var alphaIsTransparency = true
  
  final val scaleR = 1.0
  final val scaleG = 1.2
  final val scaleB = 0.8
  final val scaleA = 1.0

  val histogram = scala.collection.mutable.Map.empty[Color, Int]
  
  def feed(pixels: Array[Byte]) {
    for(pixel <- pixels.grouped(4)) {
      val r = (pixel(0) & 0xff) * scaleR
      val g = (pixel(1) & 0xff) * scaleG
      val b = (pixel(2) & 0xff) * scaleB
      val a = (pixel(3) & 0xff) * scaleA
      
      val c = if(alphaIsTransparency) Color(r * a, b * a, g * a, a)
      else Color(r, g, b, a)

      histogram(c) = histogram.getOrElse(c, 0) + 1
    }
  }
}