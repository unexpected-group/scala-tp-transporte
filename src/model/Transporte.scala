package model

trait Transporte {

  var capacidad: Int = 0
  var costo: Int = 0
  var velocidad: Int = 0
  var envios: List[Envio] = List()
  var origen: Sucursal = null
  var destino: Sucursal = null
  var seguimiento: Seguimiento = null
  var tipo: TipoTransporte = null

  def capacidadDisponible = capacidad - envios.map(e => e.volumen).sum

  def asignarDestino(sucursal: Sucursal) = destino = sucursal

  def asignarSeguimiento(seguimientoNuevo: Seguimiento) = seguimiento = seguimientoNuevo
  
  def cuantoPagaPeaje = 0

  def agregarEnvio(envio: Envio) = {
    if (envio.destino.nombre == destino.nombre &&
      envio.volumen < capacidadDisponible) envios = envio :: envios
  }

  def precioEnviosRefrigerados = 5 * envios.count(e => e.esRefrigerdo)
}