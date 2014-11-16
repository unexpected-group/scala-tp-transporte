package test.objetos

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
import objetos.Urgente
import objetos.Gps
import objetos.Refrigerado
import objetos.Video
import objetos.Animales

class TransporteTest {

  val paqueteGrande: Paquete = new Paquete(20)
  val paqueteChico: Paquete = new Paquete(2)
  val origen: Sucursal = new Sucursal("Casa Matriz", 100, "Argentina")
  val destino: Sucursal = new Sucursal("Corrientes", 200, "Argentina")

  var avion: Avion = null
  var camion: Camion = null
  var furgoneta: Furgoneta = null

  @Before
  def setUp = {
    avion = new Avion()
    camion = new Camion()
    furgoneta = new Furgoneta()
    
    camion.origen = origen
    camion.asignarSeguimiento(new Gps())
    
    furgoneta.origen = origen
    furgoneta.asignarSeguimiento(new Video())
    furgoneta.asignarTipoTransporte(new Animales()) 
  }

  @Test
  def testCostoDePeajes = {
    assertEquals(0, avion.costoPorPeajes, 0.5)
    assertEquals(60, camion.costoPorPeajes, 0.5)
    assertEquals(30, furgoneta.costoPorPeajes, 0.5)
  }
  
  @Test
  def testUnCamionLLevaUnEnvioUrgentes = {
    val envio: Urgente = new Urgente(paqueteGrande, destino)
    envio.asignarTransporte(camion)
    assertEquals(120, camion.costoBaseViaje, 0.5)
    assertEquals(-170, camion.beneficioFinal, 0.5)
  }
  
  @Test
  def testUnaFurgonetaLLevaDosEnviosRefrigerados = {
    val envio1: Refrigerado = new Refrigerado(paqueteChico, destino)
    val envio2: Refrigerado = new Refrigerado(paqueteChico, destino)
    envio1.asignarTransporte(furgoneta)
    envio2.asignarTransporte(furgoneta)
    assertEquals(180, furgoneta.costoBaseViaje, 0.5)
    assertEquals(-634, furgoneta.beneficioFinal, 0.5)
  }
}