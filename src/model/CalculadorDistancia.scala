package model

trait CalculadorDistancia {

  def distanciaTerrestreEntre(origen: Sucursal, destino: Sucursal): Double
  def distanciaAereaEntre(origen: Sucursal, destino: Sucursal): Double
  def cantidadPeajesEntre(origen: Sucursal, destino: Sucursal): Int

}