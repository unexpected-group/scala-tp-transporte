package object funcional {

  type CostoParcial = (Transporte, Double)
    
  val distintosPaises: Transporte => Boolean =
    t => ! t.origen.pais.equalsIgnoreCase(t.destino.pais)

  val destinoCasaCentral: Transporte => Boolean =
    t => t.destino.nombre.equalsIgnoreCase("Casa Central")

  val costoPorPeajes: CostoParcial => CostoParcial = tupla => tupla._1 match {
    case Avion() => tupla
    case Camion() => (tupla._1, tupla._2 + 12 * tupla._1.cantidadPeajes)
    case Furgoneta() => (tupla._1, tupla._2 + 6 * tupla._1.cantidadPeajes)
  }

  val costoEnviosRefrigerados: CostoParcial => CostoParcial = tupla => tupla._1 match {
    case Avion() => tupla
    case _ => (tupla._1, tupla._2 + 5 * tupla._1.cantidadEnviosSegun({ case Refrigerado() => true case _ => false }) )
  }

  val porcentajeImpuestoDistintosPaises: CostoParcial => CostoParcial = tupla => tupla._1 match {
    case Avion() if (distintosPaises(tupla._1)) => (tupla._1, tupla._2*1.1)
    case _ => tupla
  }

  val porcentajeRevisionTecnica: CostoParcial => CostoParcial = tupla => tupla._1 match {
    case Camion() if (destinoCasaCentral(tupla._1)) => (tupla._1, tupla._2*1.02)
    case _ => tupla
  }

  val porcentajeEnvioInsumos: CostoParcial => CostoParcial = tupla => tupla._1 match {
    case Avion() if (destinoCasaCentral(tupla._1)) => (tupla._1, tupla._2*0.8)
    case _ => tupla
  }

  val cargoPorPocaOcupacion: CostoParcial => CostoParcial = tupla => tupla._1 match {
    case tupla._1 if (tupla._1.pocoVolumenOcupado) => tupla._1 match {
      case Avion() => (tupla._1, tupla._2*3)
      case Furgoneta() if (tupla._1.llevaPocosPaquetesUrgentes) => (tupla._1, tupla._2*2)
      case Camion() if (tupla._1.evaluarLugares) => (tupla._1, tupla._2 * (1 + tupla._1.volumenOcupado / tupla._1.capacidad) )
      case _ => tupla
    }
    case _ => tupla
  }

  val costoPorSeguimiento: CostoParcial => CostoParcial = tupla => tupla._1.seguimiento match {
    case Gps() => (tupla._1, tupla._2 + (0.5 * tupla._1.distancia * 2) )
    case Video() => (tupla._1, tupla._2 + (3.74 * tupla._1.distancia * 2) )
    case _ => tupla
  }

  val costoPorTipoVehiculo: CostoParcial => CostoParcial = tupla => tupla._1.tipo match {
    case SustanciasPeligrosas() => (tupla._1, tupla._2 + 600)
    case Animales() => (tupla._1, tupla._2 + tupla._1.costoSegunDistancia)
    case _ => tupla
  }

  val costoPorLlevarSustanciasPeligrosasUrgentes: CostoParcial => CostoParcial = tupla => tupla._1 match {
    case Camion() => tupla._1.tipo match {
      case SustanciasPeligrosas() if (tupla._1.tieneAlgunEnvioUrgente) => (tupla._1, tupla._2 + 3 * tupla._1.porcentajeUrgentes)
      case _ => tupla
    }
    case _ => tupla
  }
  
  // calculo de costos
  
  def precioEnvios(t: Transporte): Double =
    t.envios.map(e => e.precio).sum
    
  def costoBaseViaje(t: Transporte): Double =
    t.costoTransporte + t.envios.map(e => e.costo).sum
    
  implicit class FExt[A, B](f: A => B) {
    def °[C](g: C => A) = {
      f compose g
    }
  }
      
  def costoViaje(t: Transporte): Double = {
	//val costos: List[Transporte => Double] = List(costoBaseViaje, costoPorPeajes, costoEnviosRefrigerados, 
	//costoPorSeguimiento, costoPorTipoVehiculo, costoPorLlevarSustanciasPeligrosasUrgentes)
    var precio = costoBaseViaje(t);
	val costo = (costoPorPeajes ° costoEnviosRefrigerados  ° costoPorSeguimiento ° 
			costoPorTipoVehiculo ° costoPorLlevarSustanciasPeligrosasUrgentes ° porcentajeRevisionTecnica °
			porcentajeEnvioInsumos ° porcentajeImpuestoDistintosPaises ° cargoPorPocaOcupacion)
	//var precio = costos.foldLeft(0D)((res,un_costo) => res + un_costo(t) )
	//precio += precio * porcentajeRevisionTecnica(t) - precio * porcentajeEnvioInsumos(t)
	//precio += precio * porcentajeImpuestoDistintosPaises(t)
	//precio *= cargoPorPocaOcupacion(t)
	precio += costo (t, 0)._2
    
	precio
  }

  def beneficioFinal(t: Transporte): Double = precioEnvios(t) - costoViaje(t)
}