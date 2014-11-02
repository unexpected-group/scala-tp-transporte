package model

class Urgente extends Envio {

  override def costoBase = 20
  
  override def precioBase = 110
  
  override def esUrgente = true
}