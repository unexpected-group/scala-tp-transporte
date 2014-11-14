package funcional

trait TipoTransporte {

  def costo(distancia: Double): Double = 0

  def esSustanciasPeligrosas = false

  def esAnimales = false
}