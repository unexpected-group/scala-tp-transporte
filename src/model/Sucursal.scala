package model

class Sucursal(val capacidad: Int) {

  var enviosAlmacenados: List[Envio] = List()
  var enviosEntrantes: List[Envio] = List()
  var transportes: List[Transporte] = List()

  def capacidadDisponible = capacidad - volumenEnvios
  
  def volumenEnvios = enviosAlmacenados.map(e => e.volumen).sum + enviosEntrantes.map(e => e.volumen).sum 

  def agregarEnvioEntrante(envio: Envio) = {
    if (capacidadDisponible > envio.volumen) {
      enviosEntrantes = envio :: enviosEntrantes
    }
  }
}