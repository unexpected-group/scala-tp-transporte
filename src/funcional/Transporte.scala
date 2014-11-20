package funcional

class Transporte extends CalculadorDistancia {

  var capacidad: Double = 0
  var costoTransporte: Double = 0
  var velocidad: Double = 0
  var envios: List[Envio] = List()
  var origen: Sucursal = null
  var destino: Sucursal = null
  var seguimiento: Seguimiento = null
  var tipo: TipoTransporte = null

  // agregado para simplicidad

  def cantidadPeajes: Double = cantidadPeajesEntre(origen, destino)

  def distancia: Double = this match {
    case Avion() => distanciaAereaEntre(origen, destino)
    case _ => distanciaTerrestreEntre(origen, destino)
  }

  // metodos aux

  val esUrgente: Envio => Boolean = { case Urgente() => true case _ => false }

  def origenCasaCentral: Boolean = origen.nombre match {
    case "Casa Central" => true
    case _ => false
  }

  def destinoCasaCentral: Boolean = destino.nombre match {
    case "Casa Central" => true
    case _ => false
  }

  def evaluarLugares: Boolean = !(origenCasaCentral || destinoCasaCentral)

  def porcentajeUrgentes: Double = envios.filter(esUrgente).map(e => e.volumen).sum / capacidad

  def cantidadEnviosSegun(f: Envio => Boolean) = envios.count(f)

  def tieneAlgunEnvioUrgente = envios.exists(esUrgente)

  def capacidadDisponible: Double = capacidad - envios.map(e => e.volumen).sum

  def volumenOcupado: Double = capacidad - capacidadDisponible

  def pocoVolumenOcupado = volumenOcupado < 0.2 * capacidad

  def llevaPocosPaquetesUrgentes = cantidadEnviosSegun(esUrgente) < 3

  def costoSegunDistancia: Double = if (distancia < 100) 50 else if (distancia < 200) 86 else 137

  // metodos auxiliares

  def agregarEnvio(envio: Envio) =
    if (envio.destino == destino && envio.volumen < capacidadDisponible)
      envios :+ envio

  def asignarDestino(sucursal: Sucursal) = destino = sucursal

  def asignarSeguimiento(seguimientoNuevo: Seguimiento) = seguimiento = seguimientoNuevo

  def asignarTipoTransporte(tipoNuevo: TipoTransporte) = tipo = tipoNuevo
}