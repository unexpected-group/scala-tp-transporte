package model

trait Transporte {

  var capacidad: Int = 0
  var costo: Int = 0
  var velocidad: Int = 0
  var envios: List[Envio] = List()
  var destino: Sucursal = null
  var seguimiento: Seguimiento = null
  var tipo: TipoTransporte = null
  
  def capacidadDisponible = capacidad - envios.map(e => e.volumen).sum
  
  def asignarDestino(sucursal: Sucursal) = destino = sucursal 
  
  def agregarEnvio(envio: Envio) = {
    if (envio.volumen < capacidadDisponible) {
    	envios = envio :: envios
    }
  }
  
  def asignarSeguimiento(seguimiento_nuevo: Seguimiento) = seguimiento = seguimiento_nuevo
  
}