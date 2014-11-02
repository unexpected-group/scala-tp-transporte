package model

trait Transporte extends CalculadorDistancia {

  var capacidad: Int = 0
  var costoTransporte: Int = 0
  var velocidad: Int = 0
  var envios: List[Envio] = List()
  var origen: Sucursal = null
  var destino: Sucursal = null
  var seguimiento: Seguimiento = null
  var tipo: TipoTransporte = null
  
  def agregarEnvio(envio: Envio) =
  if (envio.destino.nombre == destino.nombre && envio.volumen < capacidadDisponible)
	  envios = envio :: envios
  
  def cargo: Int
  
  def origenCasaCentral = origen.nombre.equalsIgnoreCase("Casa Central")
  
  def destinoCasaCentral = destino.nombre.equalsIgnoreCase("Casa Central")

  def capacidadDisponible = capacidad - envios.map(e => e.volumen).sum

  def asignarDestino(sucursal: Sucursal) = destino = sucursal

  def asignarSeguimiento(seguimientoNuevo: Seguimiento) = seguimiento = seguimientoNuevo
  
  def asignarTipoTransporte(tipoTransporte : TipoTransporte) = tipo = tipoTransporte
  
  def cuantoPagaPeaje = 0

  def cantidadEnviosSegun(f: Envio => Boolean) = envios.count(f) // autor: Juan Pablo Jacob
      
  def precioEnviosRefrigerados = 5 * cantidadEnviosSegun(e => e.esRefrigerdo) 
  
  def volumenOcupado = capacidad - capacidadDisponible
  
  def destinoCasaCentralUltimaSemana: Double = 0 // camion lo extiende
  
  def impusetoDistintosPaises: Double = 0 // avion lo extiende
  
  def destinoCasaCentralDiaVeinte: Double = 0 // avion lo extiende
  
  def pocoVolumenOcupado = volumenOcupado < 0.2 * capacidad
  
  def cargoPorPocaOcupacion = if (pocoVolumenOcupado) cargo
  
  def costoPorSeguimiento = seguimiento.coeficiente * distanciaTerrestreEntre(origen, destino) * 2
  
  def distancia = distanciaTerrestreEntre(origen, destino)
  
  def costoPorTipoDeVehiculo = tipo.costo(distancia)
  
  def cargoPorLlevarSustanciasPeligrosasUrgentes: Double = 0 
}