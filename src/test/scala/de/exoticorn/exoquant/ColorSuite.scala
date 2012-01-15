package de.exoticorn.exoquant

import org.scalatest.FunSuite

class ColorSuite extends FunSuite {
  test("add") {
    assert(Color(1, 2, 3, 4) + Color(5, 6, 7, 8) === Color(6, 8, 10, 12))
  }
  
  test("sub") {
    assert(Color(1, 2, 3, 4) - Color(5, 1, 4, 2) === Color(-4, 1, -1, 2))
  }
  
  test("mul") {
    assert(Color(1, 2, 3, 4) * Color(5, 6, 7, 8) === Color(5, 12, 21, 32))
  }
  
  test("scale") {
    assert(Color(1, 2, 3, 4) * 5 === Color(5, 10, 15, 20))
  }
  
  test("dot") {
    assert(Color(1, 2, 3, 4).dot(Color(5, 6, 7, 8)) === 5+12+21+32)
  }
  
  test("normalize") {
    assert((Color(1, 2, 3, 4).normalize.length - 1).abs < 0.001)
  }
}
