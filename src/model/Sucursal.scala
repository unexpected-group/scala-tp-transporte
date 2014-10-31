package model

class Sucursal(val capacidad: Int) {

  var enviosSalientes: List[Envio] = List()
  var enviosEntrantes: List[Envio] = List()
  var transportes: List[Transporte] = List()

  def capacidadDisponible = capacidad - volumenEnvios
  
  def volumenEnvios = enviosSalientes.map(e => e.volumen).sum + enviosEntrantes.map(e => e.volumen).sum 

  def agregarEnvioEntrante(envio: Envio) = {
    if (capacidadDisponible > envio.volumen) {
      enviosEntrantes = envio :: enviosEntrantes
    }
  }
}