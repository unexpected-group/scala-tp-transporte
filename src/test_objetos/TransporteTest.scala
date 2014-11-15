package test_objetos

import org.junit.Assert._
import org.junit.Before
import org.junit.Test
import objetos.Sucursal
import objetos.Envio
import objetos.Paquete
import objetos.Camion
import objetos.Avion
import objetos.Refrigerado
import objetos.Furgoneta

class TransporteTest {

  val paquete: Paquete = new Paquete(30)
  val destino: Sucursal = new Sucursal("Corrientes", 200, "Argentina")

  var avion: Avion = null
  var camion: Camion = null
  var furgoneta: Furgoneta = null

  @Before
  def setUp = {
    avion = new Avion()
    camion = new Camion()
    furgoneta = new Furgoneta()
  }

  @Test
  def testPeaje = {
    assertEquals(0, avion.cuantoPagaPeaje)
    assertEquals(12, camion.cuantoPagaPeaje)
    assertEquals(6, furgoneta.cuantoPagaPeaje)
  }
}