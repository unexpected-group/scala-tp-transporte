package objetos

class Envio(var paquete: Paquete = null, var destino: Sucursal = null) {
  
  def asignarTransporte(transporte: Transporte) = {
    transporte.agregarEnvio(this)
    destino.agregarEnvioEntrante(this)
  }
  
  def volumen = paquete.volumen
  
  def costoBase = 10
  def precioBase = 80
  
  def esRefrigerdo = false
  def esFragil = false
  def esUrgente = false
}