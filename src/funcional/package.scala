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
    case Avion() => 0
    case Camion() => 12
    case Furgoneta() => 6
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
}