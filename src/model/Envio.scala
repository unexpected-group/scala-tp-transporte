package model

class Envio(var paquete: Paquete = null, var destino: Sucursal = null) {
  
  def asignarTransporte(transporte: Transporte) = transporte.agregarEnvio(this)
  
  def esRefrigerdo = false
  
  def volumen: Int = paquete.volumen
}