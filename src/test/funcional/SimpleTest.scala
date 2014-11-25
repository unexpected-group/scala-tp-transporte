package test.funcional

import org.junit.Assert._
import org.junit.Test
import org.junit.Before
import funcional._

class SimpleTest {
  
  val paqueteChico = new Paquete(2)
  val paqueteMediano = new Paquete(10)
  val paqueteGrande = new Paquete(20)
  val origen = new Sucursal("Casa Matriz", 100, "Argentina")
  val destino = new Sucursal("Corrientes", 200, "Argentina")
  val extranjero = new Sucursal("Oxford", 900, "Inglaterra")

  var avion: Avion = null
  var camion: Camion = null
  var furgoneta: Furgoneta = null

  @Before
  def setUp = {
    avion = Avion()
    camion = Camion()
    furgoneta = Furgoneta()

    camion.origen = origen
    camion.destino = destino
    camion.asignarSeguimiento(Gps())

    furgoneta.origen = origen
    furgoneta.destino = destino
    furgoneta.asignarSeguimiento(Video())
    furgoneta.asignarTipoTransporte(Animales())
    
    avion.origen = origen
    avion.destino = extranjero
    avion.asignarSeguimiento(Gps())
    avion.asignarTipoTransporte(SustanciasPeligrosas())
  }

  @Test
  def testCostoDePeajes = {
    assertEquals(0, costoPorPeajes(avion), 0.5)
    assertEquals(60, costoPorPeajes(camion), 0.5)
    assertEquals(30, costoPorPeajes(furgoneta), 0.5)
  }

  @Test
  def testUnCamionLLevaUnEnvioUrgente = {
    val envio = Urgente()
    envio.asignarPaquete(paqueteGrande)
    envio.asignarDestino(destino)
    envio.asignarTransporte(camion)
    
    assertEquals(120, costoBaseViaje(camion), 0.5)
    assertEquals(110, precioEnvios(camion), 0.5)
    assertEquals(-170, beneficioFinal(camion), 0.5)
  }

  @Test
  def testUnaFurgonetaLLevaDosEnviosRefrigerados = {
    val envio1 = Refrigerado()
    val envio2 = Refrigerado()
    envio1.asignarPaquete(paqueteChico)
    envio1.asignarDestino(destino)
    envio2.asignarPaquete(paqueteChico)
    envio2.asignarDestino(destino)
    envio1.asignarTransporte(furgoneta)
    envio2.asignarTransporte(furgoneta)
    
    assertEquals(180, costoBaseViaje(furgoneta), 0.5)
    assertEquals(420, precioEnvios(furgoneta), 0.5)
    assertEquals(-634, beneficioFinal(furgoneta), 0.5)
  }

  @Test
  def testUnAvionLLevaCincoEnviosRefrigeradosConDosUrgentes = {
    for (x <- 1 to 5) {
      val envioRefrigerado = Refrigerado()
      envioRefrigerado.asignarPaquete(paqueteMediano)
      envioRefrigerado.asignarDestino(extranjero)
      envioRefrigerado.asignarTransporte(avion)
    }
    for (x <- 1 to 2) {
      val envioUrgente = Urgente()
      envioUrgente.asignarPaquete(paqueteGrande)
      envioUrgente.asignarDestino(extranjero)
      envioUrgente.asignarTransporte(avion) 
    }
    
    assertEquals(890, costoBaseViaje(avion), 0.5)
    assertEquals(1270, precioEnvios(avion), 0.5)
    assertEquals(-479, beneficioFinal(avion), 0.5)
  }
}