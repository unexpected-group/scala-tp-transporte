package objetos

class Camion extends Transporte {
  
  capacidad = 45
  costoTransporte = 100
  velocidad = 60 
  
  override def porcentajeRevisionTecnica = if (destinoCasaCentral) 0.02 else 0
  
  override def cuantoPagaPeaje = 12
  
  override def cargo = if (origenCasaCentral || destinoCasaCentral) 1 else 1 + volumenOcupado / capacidad
  
  def llevaEnviosUrgentes = cantidadEnviosSegun(e => e.esUrgente) > 0
  
  def porcentajeVolumenEnviosUrgentes = cantidadEnviosSegun(e => e.esUrgente) / capacidad
  
  override def costoPorLlevarSustanciasPeligrosasUrgentes =
    if (tipo.esSustanciasPeligrosas && llevaEnviosUrgentes) 3 * porcentajeVolumenEnviosUrgentes else 0
}