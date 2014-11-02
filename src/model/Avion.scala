package model

class Avion extends Transporte {

  capacidad = 200
  costo = 500
  velocidad = 500
  
  override def precioEnviosRefrigerados = 0
  
  def impusetoDistintosPaises = if (origen.pais.equalsIgnoreCase(destino.pais)) 0 else 0.1
  
  def destinoCasaCentral = if (destino.nombre.equalsIgnoreCase("Casa Central")) 0.2 else 0.0
}