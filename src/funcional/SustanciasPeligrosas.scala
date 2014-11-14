package funcional

class SustanciasPeligrosas extends TipoTransporte {
  
  override def costo(distancia: Double) = 600
  
  override def esSustanciasPeligrosas = true
}