package test_objetos

import org.junit.Assert._
import org.junit.Before
import org.junit.Test
import objetos.Sucursal
import objetos.Envio
import objetos.Paquete
import objetos.Camion
import objetos.Avion

class SucursalTest {

  val paquete: Paquete = new Paquete(20)
  val destino: Sucursal = new Sucursal("Corrientes", 200, "Argentina")

  var sucursal: Sucursal = null
  var envio: Envio = null

  @Before
  def setUp = {
    sucursal = new Sucursal("Capital", 100, "Argentina")
    envio = new Envio(paquete, destino)
  }

  @Test
  def testCapacidadConEnvio = {
    sucursal.agregarEnvioSaliente(envio)
    sucursal.agregarEnvioSaliente(envio)
    sucursal.agregarEnvioSaliente(envio)
    assertEquals(40, sucursal.capacidadDisponible)
    sucursal.enviar(sucursal.enviosSalientes, new Avion())
    assertEquals(100, sucursal.capacidadDisponible)
  }

  @Test
  def testCapacidadCuandoSeAsignaUnTransporte = {
    val camion = new Camion()
    envio.asignarTransporte(camion)
    assertTrue(destino.enviosEntrantes.contains(envio))
    assertEquals(180, destino.capacidadDisponible)
  }
}