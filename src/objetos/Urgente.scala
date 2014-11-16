package objetos

class Urgente(paquete: Paquete = null, destino: Sucursal = null)
  extends Envio(paquete, destino) {

  override def costoBase = 20

  override def precioBase = 110

  override def esUrgente = true
}