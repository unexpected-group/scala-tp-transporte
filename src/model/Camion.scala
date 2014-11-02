package model

class Camion extends Transporte {
  
  capacidad = 45
  costo = 100
  velocidad = 60 
  
  override def cuantoPagaPeaje = 12
  
  def destinoCasaCentral = if (destino.nombre.equalsIgnoreCase("Casa Central")) 0.02 else 0.0
}