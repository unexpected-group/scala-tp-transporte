package model

class Envio(var volumen: Int = 0, var destino: Sucursal = null) {
  
  def asignarTransporte(transporte: Transporte) = {
    transporte.agregarEnvio(this)
    destino.agregarEnvioEntrante(this)
  }
  
  def esRefrigerdo = false
  
  def esFragil = false
}