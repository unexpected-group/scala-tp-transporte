package objetos

class Fragil(paquete: Paquete = null, destino: Sucursal = null)
  extends Envio(paquete, destino) {

  override def esFragil = true

  override def costoBase = 18

  override def precioBase = 120
}