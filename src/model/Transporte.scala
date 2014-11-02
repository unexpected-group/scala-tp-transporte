package model

trait Transporte extends CalculadorDistancia {

  var capacidad: Double = 0
  var costoTransporte: Double = 0
  var velocidad: Double = 0 // no se usa en ningun lado por ahora
  var envios: List[Envio] = List()
  var origen: Sucursal = null
  var destino: Sucursal = null
  var seguimiento: Seguimiento = null
  var tipo: TipoTransporte = null

  def agregarEnvio(envio: Envio) =
    if (envio.destino.nombre == destino.nombre && envio.volumen < capacidadDisponible)
      envios = envio :: envios

  def cargo: Double

  def origenCasaCentral = origen.nombre.equalsIgnoreCase("Casa Central")

  def destinoCasaCentral = destino.nombre.equalsIgnoreCase("Casa Central")

  def capacidadDisponible = capacidad - envios.map(e => e.volumen).sum
  
  def asignarDestino(sucursal: Sucursal) = destino = sucursal
  
  def asignarSeguimiento(seguimientoNuevo: Seguimiento) = seguimiento = seguimientoNuevo
  
  def asignarTipoTransporte(tipoTransporte: TipoTransporte) = tipo = tipoTransporte
  
  def cuantoPagaPeaje = 0
  
  def cantidadEnviosSegun(f: Envio => Boolean) = envios.count(f) // autor: Juan Pablo Jacob
  
  def volumenOcupado: Double = capacidad - capacidadDisponible
  
  def pocoVolumenOcupado = volumenOcupado < 0.2 * capacidad
  
  def distancia = distanciaTerrestreEntre(origen, destino)

  // condiciones
  
  def costoPorPeajes: Double = cuantoPagaPeaje * cantidadPeajesEntre(origen, destino)
  
  def costoEnviosRefrigerados: Double = 5 * cantidadEnviosSegun(e => e.esRefrigerdo)
  
  def impusetoDistintosPaises: Double = 0
  
  def revisionTecnica: Double = 0
  
  def enviaInsumos: Double = 0
  
  def cargoPorPocaOcupacion: Double = if (pocoVolumenOcupado) cargo else 1
  
  def costoPorSeguimiento: Double = seguimiento.coeficiente * distanciaTerrestreEntre(origen, destino) * 2
  
  def costoPorTipoDeVehiculo: Double = tipo.costo(distancia)
  
  def costoPorLlevarSustanciasPeligrosasUrgentes: Double = 0

  // calculo de precios  

  def precioEnvios = envios.map(e => e.precioBase).sum

  def costoBaseViaje = costoTransporte + envios.map(e => e.costoBase).sum

  def costoViaje: Double = {
    var precio: Double = costoBaseViaje
      + costoPorPeajes
      + costoEnviosRefrigerados
      + impusetoDistintosPaises
      + costoPorSeguimiento
      + costoPorTipoDeVehiculo
      + costoPorLlevarSustanciasPeligrosasUrgentes
    precio += precio * revisionTecnica - precio * enviaInsumos
    precio *= cargoPorPocaOcupacion
    precio
  }

  def costoFinal: Double = precioEnvios - costoViaje
}