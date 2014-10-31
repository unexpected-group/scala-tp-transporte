package model

trait Transporte {

  var seguimiento: Seguimiento = null
  var capacidad: Int = 0
  var costo: Int = 0
  var velocidad: Int = 0
  var envios: List[Envio] = List()
  var destino: String = null
  
  def capacidadDisponible = capacidad - envios.map(e => e.volumen).sum
  
  def asignarDestino(destinoFinal: String) = destino = destinoFinal 
  
  def agregarEnvio(envio: Envio) = {
    if (envio.volumen < capacidadDisponible) {
    	envios = envio :: envios
    }
  }
  
  def asignarSeguimiento(seguimiento_nuevo: Seguimiento) = seguimiento = seguimiento_nuevo
  
}