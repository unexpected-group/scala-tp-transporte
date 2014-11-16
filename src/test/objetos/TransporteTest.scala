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
import objetos.SustanciasPeligrosas

class TransporteTest {

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
    avion = new Avion()
    camion = new Camion()
    furgoneta = new Furgoneta()

    camion.origen = origen
    camion.asignarSeguimiento(new Gps())

    furgoneta.origen = origen
    furgoneta.asignarSeguimiento(new Video())
    furgoneta.asignarTipoTransporte(new Animales())
    
    avion.origen = origen
    avion.asignarSeguimiento(new Gps())
    avion.asignarTipoTransporte(new SustanciasPeligrosas())
  }

  @Test
  def testCostoDePeajes = {
    assertEquals(0, avion.costoPorPeajes, 0.5)
    assertEquals(60, camion.costoPorPeajes, 0.5)
    assertEquals(30, furgoneta.costoPorPeajes, 0.5)
  }

  @Test
  def testUnCamionLLevaUnEnvioUrgentes = {
    val envio = new Urgente(paqueteGrande, destino)
    envio.asignarTransporte(camion)
    assertEquals(120, camion.costoBaseViaje, 0.5)
    assertEquals(110, camion.precioEnvios, 0.5)
    assertEquals(-170, camion.beneficioFinal, 0.5)
  }

  @Test
  def testUnaFurgonetaLLevaDosEnviosRefrigerados = {
    val envio1 = new Refrigerado(paqueteChico, destino)
    val envio2 = new Refrigerado(paqueteChico, destino)
    envio1.asignarTransporte(furgoneta)
    envio2.asignarTransporte(furgoneta)
    assertEquals(180, furgoneta.costoBaseViaje, 0.5)
    assertEquals(420, furgoneta.precioEnvios, 0.5)
    assertEquals(-634, furgoneta.beneficioFinal, 0.5)
  }

  @Test
  def testUnAvionLLevaCincoEnviosRefrigeradosConDosUrgentes = {
    val envioRefrigerado1 = new Refrigerado(paqueteMediano, extranjero)
    val envioRefrigerado2 = new Refrigerado(paqueteMediano, extranjero)
    val envioRefrigerado3 = new Refrigerado(paqueteMediano, extranjero)
    val envioRefrigerado4 = new Refrigerado(paqueteMediano, extranjero)
    val envioRefrigerado5 = new Refrigerado(paqueteMediano, extranjero)
    val envioUrgente1 = new Urgente(paqueteGrande, extranjero)
    val envioUrgente2 = new Urgente(paqueteGrande, extranjero)
    envioRefrigerado1.asignarTransporte(avion)
    envioRefrigerado2.asignarTransporte(avion)
    envioRefrigerado3.asignarTransporte(avion)
    envioRefrigerado4.asignarTransporte(avion)
    envioRefrigerado5.asignarTransporte(avion)
    envioUrgente1.asignarTransporte(avion)
    envioUrgente2.asignarTransporte(avion)
    assertEquals(890, avion.costoBaseViaje, 0.5)
    assertEquals(1270, avion.precioEnvios, 0.5)
    assertEquals(-479, avion.beneficioFinal, 0.5)
  }
}