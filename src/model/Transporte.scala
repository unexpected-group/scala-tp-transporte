package model

trait Transporte {
 
  var capacidad : Int = 0 
  
  var costo : Int = 0
  
  var velocidad : Int = 0
  
  var envios: List[Envio] = List()
  
  def agregarEnvio(envio: Envio) = envios = envio :: envios

}