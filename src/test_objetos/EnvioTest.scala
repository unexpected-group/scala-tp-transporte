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

class EnvioTest {

  val paquete: Paquete = new Paquete(30)
  val destino: Sucursal = new Sucursal("Corrientes", 200, "Argentina")

  var envio: Envio = null

  @Before
  def setUp = {
    envio = new Envio(paquete, destino)
  }

  @Test
  def testEnvioSeAsignaAlTransporte = {
    val camion = new Camion()
    envio.asignarTransporte(camion)
    assertTrue(camion.envios.contains(envio))
  }
  
  @Test
  def testEnvioDesignaDestinoTransporte = {
    val camion = new Camion()
    envio.asignarTransporte(camion)
    assertEquals(envio.destino, camion.destino)
  }
}