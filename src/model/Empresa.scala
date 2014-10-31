package model

class Empresa {
  
  var sucursales: List[Sucursal] = List()
  var transportes: List[Transporte] = List()
  
  def agregarSucursal(sucursal: Sucursal) = sucursales = sucursal :: sucursales
  
  def agregarTransporte(transporte: Transporte) = transportes = transporte :: transportes
  
  def cantidadSucursales = sucursales.size
  
  def cantidadTransportes = transportes .size
}