package model

trait Transporte {
  
  var envios: List[Envio] = List()
  
  def agregarEnvio(envio: Envio) = envios = envio :: envios

}