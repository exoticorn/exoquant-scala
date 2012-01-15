package de.exoticorn.exoquant

case class Color(r: Double, g: Double, b: Double, a: Double) {
  def +(o: Color): Color = new Color(r + o.r, g + o.g, b + o.b, a + o.a)
  def -(o: Color): Color = new Color(r - o.r, g - o.g, b - o.b, a - o.a)
  def *(o: Color): Color = new Color(r * o.r, g * o.g, b * o.b, a * o.a)
  def *(f: Double): Color = new Color(r * f, g * f, b * f, a * f)
  def dot(o: Color): Double = r * o.r + g * o.g + b * o.b + a * o.a
  def length: Double = math.sqrt(r*r + g*g + b*b + a*a)
  def normalize: Color = this * (1 / length)
}