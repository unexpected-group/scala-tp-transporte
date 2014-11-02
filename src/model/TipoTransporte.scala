package model

trait TipoTransporte {
  
  def esSustanciasPeligrosas = false
  
  def esAnimales = false
  
  def costo(distancia: Double): Double = 0
}