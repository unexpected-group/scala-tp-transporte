package objetos

class Empresa {
  
  var sucursales: List[Sucursal] = List()
  var transportes: List[Transporte] = List()
  
  def agregarSucursal(sucursal: Sucursal) = sucursales :+ sucursal
  
  def agregarTransporte(transporte: Transporte) = transportes :+ transporte
  
  def cantidadSucursales = sucursales.size
  
  def cantidadTransportes = transportes .size
}