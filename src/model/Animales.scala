package model

class Animales extends TipoTransporte {

  override def costo(distancia: Double) = if (distancia < 100) 50 else if (distancia < 200) 86 else 137
  
  override def esAnimales = true
}