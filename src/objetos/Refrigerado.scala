package objetos

class Refrigerado(paquete: Paquete = null, destino: Sucursal = null)
  extends Envio(paquete, destino) {
  
  override def esRefrigerdo = true
  
  override def costoBase = 70
  
  override def precioBase = 210
}