package object funcional {

  type PrecioCosto = (Double, Double)

  val distintosPaises: Transporte => Boolean =
    t => t.origen.pais.equalsIgnoreCase(t.destino.pais)

  val destinoCasaCentral: Transporte => Boolean =
    t => t.destino.nombre.equalsIgnoreCase("Casa Central")

  val precioCosto: Envio => PrecioCosto = e => e match {
    case Comun() => (80, 10)
    case Urgente() => (110, 20)
    case Fragil() => (120, 18)
    case Refrigerado() => (210, 70)
  }

  val costoPeaje: Transporte => Double = t => t match {
    case Avion() => 0 * t.cantidadPeajes
    case Camion() => 12 * t.cantidadPeajes
    case Furgoneta() => 6 * t.cantidadPeajes
  }

  val costoEnviosRefrigerados: Transporte => Double = t => t match {
    case Avion() => 0
    case _ => 5 * t.cantidadEnviosSegun({ case Refrigerado() => true case _ => false })
  }

  val porcentajeImpuestoDistintosPaises: Transporte => Double = t => t match {
    case Avion() if (distintosPaises(t)) => 0.1
    case _ => 0
  }

  val porcentajeRevisionTecnica: Transporte => Double = t => t match {
    case Camion() if (destinoCasaCentral(t)) => 0.02
    case _ => 0
  }

  val porcentajeEnvioInsumos: Transporte => Double = t => t match {
    case Avion() if (destinoCasaCentral(t)) => 0.2
    case _ => 0
  }

  val cargoPorPocaOcupacion: Transporte => Double = t => t match {
    case t if (t.pocoVolumenOcupado) => t match {
      case Avion() => 3
      case Furgoneta() if (t.llevaPocosPaquetesUrgentes) => 2
      case Camion() if (t.evaluarLugares) => 1 + t.volumenOcupado / t.capacidad
      case _ => 1
    }
    case _ => 1
  }

  val costoPorSeguimiento: Transporte => Double = t => t.seguimiento match {
    case Gps() => 0.5 * t.distancia
    case Video() => 3.74 * t.distancia
  }

  val costoPorTipoVehiculo: Transporte => Double = t => t.tipo match {
    case SustanciasPeligrosas() => 600 * t.distancia
    case Animales() => t.costoSegunDistancia
    case _ => 0
  }

  val costoPorLlevarSustanciasPeligrosasUrgentes: Transporte => Double = t => t match {
    case Camion() => t.tipo match {
      case SustanciasPeligrosas() if (t.tieneAlgunEnvioUrgente) => 3 * t.porcentajeUrgentes
      case _ => 0
    }
    case _ => 0
  }
}