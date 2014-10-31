package model

class Sucursal(val capacidad: Int) {

	var enviosSalientes: List[Envio] = List()
    var transportes: List[Transporte] = List()
    
    def capacidadDisponible = capacidad - enviosSalientes.map(e => e.volumen).sum
}