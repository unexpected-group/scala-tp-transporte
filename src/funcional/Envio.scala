package funcional

class Envio() {

  var paquete: Paquete = null
  var destino: Sucursal = null
  
  def asignarPaquete(paqueteNuevo: Paquete) = paquete = paqueteNuevo
  def asignarDestino(destinoNuevo: Sucursal) = destino = destinoNuevo
  
  def asignarTransporte(transporte: Transporte) = {
    transporte.agregarEnvio(this)
    destino.agregarEnvioEntrante(this)
  }

  def volumen = paquete.volumen
  
  val precio: Double = this match {
    case Comun() => 80
    case Urgente() => 110
    case Fragil() => 120
    case Refrigerado() => 210
  }
  
  val costo: Double = this match {
    case Comun() => 10
    case Urgente() => 20
    case Fragil() => 18
    case Refrigerado() => 70
  }
}