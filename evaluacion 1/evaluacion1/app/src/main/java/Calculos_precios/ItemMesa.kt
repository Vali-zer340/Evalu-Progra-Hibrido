package Calculos_precios

class ItemMesa(val itemMenu: ItemMenu, var cantidad: Int){
    fun calcularSubtotal(): Int = itemMenu.precio * cantidad
}