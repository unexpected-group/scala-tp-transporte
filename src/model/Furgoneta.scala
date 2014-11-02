package model

class Furgoneta extends Transporte {

  capacidad = 9
  costoTransporte = 40
  velocidad = 80
  
  override def cuantoPagaPeaje = 6
  
  override def cargo = if (cantidadEnviosSegun(e => e.esUrgente) >= 3) 1 else 2
}