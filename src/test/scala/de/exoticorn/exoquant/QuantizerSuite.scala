package de.exoticorn.exoquant

import org.scalatest.FunSuite

class QuantizerSuite extends FunSuite {
  test("feed") {
    val q = new Quantizer
    q.feed(Array[Byte](1, 2, 3, 4, 5, 6, 7, 8, 1, 2, 3, 4))
    assert(q.histogram.size === 2)
  }
}