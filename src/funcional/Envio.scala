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
  
  type PrecioCosto = (Double, Double)
  
  val precioCosto: PrecioCosto = this match {
    case Comun() => (80, 10)
    case Urgente() => (110, 20)
    case Fragil() => (120, 18)
    case Refrigerado() => (210, 70)
  }
}