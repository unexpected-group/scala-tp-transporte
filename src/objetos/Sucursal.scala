package objetos

class Sucursal(val nombre: String, val capacidad: Int, val pais: String) {

  var enviosSalientes: List[Envio] = List()
  var enviosEntrantes: List[Envio] = List()
  var transportes: List[Transporte] = List()

  def capacidadDisponible = capacidad - volumenOcupado

  def volumenOcupado =
    enviosSalientes.map(p => p.volumen).sum + enviosEntrantes.map(e => e.volumen).sum

  def agregatTransporte(transporte: Transporte) = {
    transportes :+ transporte
    transporte.origen = this
  }

  // cuando un cliente trae un paquete
  def agregarEnvioSaliente(envio: Envio) = {
    if (capacidadDisponible > envio.volumen)
      enviosSalientes :+ envio
    else
      throw new RuntimeException
  }

  // son los envios que van a llegar que estan siendo transportados
  def agregarEnvioEntrante(envio: Envio) = {
    if (capacidadDisponible > envio.volumen)
      enviosEntrantes :+ envio
    else
      throw new RuntimeException
  }
  
  def enviar(envios: List[Envio], transporte: Transporte) = {
    for (e <- envios) {
      e.asignarTransporte(transporte)
    }
    // enviosSalientes.dropWhile(envios.includes(e))
  }
}