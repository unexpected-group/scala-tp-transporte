package test.funcional

import org.junit.Assert._
import org.junit.Test

class SimpleTest {
  
  val multiplicar: (Int, Int) => Int = { case (x, y) => x * y }

  @Test
  def testFuncionMultiplica = {
    assertEquals(12, multiplicar(3, 4))
  }
}