package object funcional {
    
  val distintosPaises: Transporte => Boolean =
    t => ! t.origen.pais.equalsIgnoreCase(t.destino.pais)

  val destinoCasaCentral: Transporte => Boolean =
    t => t.destino.nombre.equalsIgnoreCase("Casa Central")

  val costoPorPeajes: (Transporte, Double) => (Transporte, Double) = (t, d) => t match {
    case Avion() => (t, d)
    case Camion() => (t, d + 12 * t.cantidadPeajes)
    case Furgoneta() => (t, d + 6 * t.cantidadPeajes)
  }

  val costoEnviosRefrigerados: (Transporte, Double) => (Transporte, Double) = (t, d) => t match {
    case Avion() => (t, d)
    case _ => (t, d + 5 * t.cantidadEnviosSegun({ case Refrigerado() => true case _ => false }) )
  }

  val porcentajeImpuestoDistintosPaises: (Transporte, Double) => (Transporte, Double) = (t, d) => t match {
    case Avion() if (distintosPaises(t)) => (t, d*1.1)
    case _ => (t, d)
  }

  val porcentajeRevisionTecnica: (Transporte, Double) => (Transporte, Double) = (t, d) => t match {
    case Camion() if (destinoCasaCentral(t)) => (t, d*1.02)
    case _ => (t, d)
  }

  val porcentajeEnvioInsumos: (Transporte, Double) => (Transporte, Double) = (t, d) => t match {
    case Avion() if (destinoCasaCentral(t)) => (t, d*0.8)
    case _ => (t, d)
  }

  val cargoPorPocaOcupacion: (Transporte, Double) => (Transporte, Double) = (t, d) => t match {
    case t if (t.pocoVolumenOcupado) => t match {
      case Avion() => (t, d*3)
      case Furgoneta() if (t.llevaPocosPaquetesUrgentes) => (t, d*2)
      case Camion() if (t.evaluarLugares) => (t, d * (1 + t.volumenOcupado / t.capacidad) )
      case _ => (t, d)
    }
    case _ => (t, d)
  }

  val costoPorSeguimiento: (Transporte, Double) => (Transporte, Double) = (t, d) => t.seguimiento match {
    case Gps() => (t, d + (0.5 * t.distancia * 2) )
    case Video() => (t, d + (3.74 * t.distancia * 2) )
    case _ => (t, d)
  }

  val costoPorTipoVehiculo: (Transporte, Double) => (Transporte, Double) = (t, d) => t.tipo match {
    case SustanciasPeligrosas() => (t, d + 600)
    case Animales() => (t, d + t.costoSegunDistancia)
    case _ => (t, d)
  }

  val costoPorLlevarSustanciasPeligrosasUrgentes: (Transporte, Double) => (Transporte, Double) = (t, d) => t match {
    case Camion() => t.tipo match {
      case SustanciasPeligrosas() if (t.tieneAlgunEnvioUrgente) => (t, d + 3 * t.porcentajeUrgentes)
      case _ => (t, d)
    }
    case _ => (t, d)
  }
  
  // calculo de costos
  
  def precioEnvios(t: Transporte): Double =
    t.envios.map(e => e.precio).sum
    
  def costoBaseViaje(t: Transporte): Double =
    t.costoTransporte + t.envios.map(e => e.costo).sum
    
//  implicit class FExt[A, B](f: A => B) {
//    def °[C](g: C => A) = {
//      f compose g
//    }
//  }
      
  def costoViaje(t: Transporte): Double = {
	//val costos: List[Transporte => Double] = List(costoBaseViaje, costoPorPeajes, costoEnviosRefrigerados, 
	//costoPorSeguimiento, costoPorTipoVehiculo, costoPorLlevarSustanciasPeligrosasUrgentes)
    var precio = costoBaseViaje(t);
	val costo = (costoPorPeajes compose costoEnviosRefrigerados  compose costoPorSeguimiento compose 
			costoPorTipoVehiculo compose costoPorLlevarSustanciasPeligrosasUrgentes compose porcentajeRevisionTecnica compose
			porcentajeEnvioInsumos compose porcentajeImpuestoDistintosPaises compose cargoPorPocaOcupacion)
	//var precio = costos.foldLeft(0D)((res,un_costo) => res + un_costo(t) )
	//precio += precio * porcentajeRevisionTecnica(t) - precio * porcentajeEnvioInsumos(t)
	//precio += precio * porcentajeImpuestoDistintosPaises(t)
	//precio *= cargoPorPocaOcupacion(t)
	costo((t, 0))
    
	precio
  }

  def beneficioFinal(t: Transporte): Double = precioEnvios(t) - costoViaje(t)
}