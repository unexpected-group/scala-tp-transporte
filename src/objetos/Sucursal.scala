package objetos

class Sucursal(val nombre: String, val capacidad: Int, val pais:String) {

  var paquetesAlmacenados: List[Paquete] = List()
  var enviosEntrantes: List[Envio] = List()
  var transportes: List[Transporte] = List()

  def capacidadDisponible = capacidad - volumenTotal
  
  def volumenTotal = paquetesAlmacenados.map(p => p.volumen).sum + enviosEntrantes.map(e => e.volumen).sum 

  def agregarEnvioEntrante(envio: Envio) = {
    if (capacidadDisponible > envio.volumen) {
      enviosEntrantes :+ envio
    }
  }
}