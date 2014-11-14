package funcional

trait Transporte extends CalculadorDistancia {

  var capacidad: Double = 0
  var costoTransporte: Double = 0
  var velocidad: Double = 0
  var envios: List[Envio] = List()
  var origen: Sucursal = null
  var destino: Sucursal = null
  var seguimiento: Seguimiento = null
  var tipo: TipoTransporte = null

  def cantidadEnviosSegun(f: Envio => Boolean) = envios.count(f)

  def capacidadDisponible: Double = capacidad - envios.map(e => e.volumen).sum

  def volumenOcupado: Double = capacidad - capacidadDisponible

  def pocoVolumenOcupado = volumenOcupado < 0.2 * capacidad

  def llevaPocosPaquetesUrgentes = cantidadEnviosSegun({ case Urgente() => true case _ => false }) < 3

  val origenCasaCentral: Boolean = origen.nombre match {
    case "Casa Central" => true
    case _ => false
  }
  
  val destinoCasaCentral: Boolean = destino.nombre match {
    case "Casa Central" => true
    case _ => false
  }
  
  val evaluarLugares: Boolean = ! (origenCasaCentral || destinoCasaCentral)

  
  
  
  
  
  // viejo

  def agregarEnvio(envio: Envio) =
    if (envio.destino.nombre == destino.nombre && envio.volumen < capacidadDisponible) envios :+ envio

  def cargo: Double = 0

  def asignarDestino(sucursal: Sucursal) = destino = sucursal

  def asignarSeguimiento(seguimientoNuevo: Seguimiento) = seguimiento = seguimientoNuevo

  def asignarTipoTransporte(tipoTransporte: TipoTransporte) = tipo = tipoTransporte

  def cuantoPagaPeaje = 0

  def distancia = distanciaTerrestreEntre(origen, destino)

  // condiciones

  def costoPorPeajes: Double = cuantoPagaPeaje * cantidadPeajesEntre(origen, destino)

  def impusetoDistintosPaises: Double = 0

  def revisionTecnica: Double = 0

  def enviaInsumos: Double = 0

  def cargoPorPocaOcupacion: Double = if (pocoVolumenOcupado) cargo else 1

  def costoPorSeguimiento: Double = seguimiento.coeficiente * distanciaTerrestreEntre(origen, destino) * 2

  def costoPorTipoDeVehiculo: Double = tipo.costo(distancia)

  def costoPorLlevarSustanciasPeligrosasUrgentes: Double = 0
}