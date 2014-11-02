package model

trait CalculadorDistancia {

  // agrego una implementacion para testear
  
  def distanciaTerrestreEntre(origen: Sucursal, destino: Sucursal): Double = 100
  def distanciaAereaEntre(origen: Sucursal, destino: Sucursal): Double = 100
  def cantidadPeajesEntre(origen: Sucursal, destino: Sucursal): Int = 5
}