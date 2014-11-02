package model

class Avion extends Transporte {

  capacidad = 200
  costoTransporte = 500
  velocidad = 500
  
  override def precioEnviosRefrigerados = 0
  
  override def cargo = 3
  
  override def impusetoDistintosPaises = if (origen.pais.equalsIgnoreCase(destino.pais)) 0 else 0.1
  
  override def destinoCasaCentralDiaVeinte = if (destino.nombre.equalsIgnoreCase("Casa Central")) 0.2 else 0
  
  override def costoPorSeguimiento = seguimiento.coeficiente * distanciaAereaEntre(origen, destino) * 2
  
  override def distancia = distanciaAereaEntre(origen, destino)
}