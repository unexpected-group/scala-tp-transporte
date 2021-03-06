package objetos

trait Transporte extends CalculadorDistancia {

  var envios: List[Envio] = List()
  var capacidad: Double = 0
  var costoTransporte: Double = 0
  var velocidad: Double = 0
  var origen: Sucursal = null
  var destino: Sucursal = null
  var seguimiento: Seguimiento = new SinSeguimiento // null object
  var tipo: TipoTransporte = new SinTipo() // null object

  def agregarEnvio(envio: Envio) = {
	if (volumenOcupado == 0) destino = envio.destino
    if (envio.destino == destino && envio.volumen < capacidadDisponible)
      envios = envios :+ envio
    else 
      throw new RuntimeException
  }
  
  // agregado para simplicidad
  
  def cantidadPeajes = cantidadPeajesEntre(origen, destino)
  def distanciaTerrestre = distanciaTerrestreEntre(origen, destino)
  def distanciaAerea = distanciaAereaEntre(origen, destino)
  
  // metodos aux

  def cargo: Double

  def origenCasaCentral = origen.nombre.equalsIgnoreCase("Casa Central")

  def destinoCasaCentral = destino.nombre.equalsIgnoreCase("Casa Central")

  def capacidadDisponible = capacidad - envios.map(e => e.volumen).sum
  
  def asignarDestino(sucursal: Sucursal) = destino = sucursal
  
  def asignarSeguimiento(seguimientoNuevo: Seguimiento) = seguimiento = seguimientoNuevo
  
  def asignarTipoTransporte(tipoTransporte: TipoTransporte) = tipo = tipoTransporte
  
  def cuantoPagaPeaje = 0
  
  def cantidadEnviosSegun(f: Envio => Boolean) = envios.count(f)
  
  def volumenOcupado = capacidad - capacidadDisponible
  
  def pocoVolumenOcupado = volumenOcupado < 0.2 * capacidad
  
  def distancia = distanciaTerrestreEntre(origen, destino)

  // condiciones
  
  def costoPorPeajes: Double = cuantoPagaPeaje * cantidadPeajesEntre(origen, destino)
  
  def costoEnviosRefrigerados: Double = 5 * cantidadEnviosSegun(e => e.esRefrigerdo)
  
  def impusetoDistintosPaises: Double = 0
  
  def porcentajeRevisionTecnica: Double = 0
  
  def porcentajeEnvioInsumos: Double = 0
  
  def cargoPorPocaOcupacion: Double = if (pocoVolumenOcupado) cargo else 1
  
  def costoPorSeguimiento: Double =
    seguimiento.coeficiente * distanciaTerrestreEntre(origen, destino) * 2
  
  def costoPorTipoDeVehiculo: Double = tipo.costo(distancia)
  
  def costoPorLlevarSustanciasPeligrosasUrgentes: Double = 0

  // calculo de costos  

  def precioEnvios = envios.map(e => e.precioBase).sum

  def costoBaseViaje = costoTransporte + envios.map(e => e.costoBase).sum

  def costoViaje: Double = {
    var precio = costoBaseViaje +
    costoPorPeajes +
    costoEnviosRefrigerados +
    costoPorSeguimiento +
    costoPorTipoDeVehiculo +
    costoPorLlevarSustanciasPeligrosasUrgentes
    precio += precio * porcentajeRevisionTecnica - precio * porcentajeEnvioInsumos
    precio += precio * impusetoDistintosPaises
    precio *= cargoPorPocaOcupacion
    precio
  }

  def beneficioFinal: Double = precioEnvios - costoViaje
}