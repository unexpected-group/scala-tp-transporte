package model

class Envio(val paquete: Paquete = null, val destino: Sucursal = null) {
  
  def asignarTransporte(transporte: Transporte) = transporte.agregarEnvio(this)
  
  def esRefrigerdo = false 

}