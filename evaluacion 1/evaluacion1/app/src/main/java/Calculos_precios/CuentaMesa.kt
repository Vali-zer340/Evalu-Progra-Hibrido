package Calculos_precios

class CuentaMesa(val numeroMesa: Int) {
    private val items = mutableListOf<ItemMesa>()
    var aceptaPropina: Boolean = true

    fun agregarItem(itemMenu: ItemMenu, cantidad: Int) {
        // Buscar si ya existe un ItemMesa con este ItemMenu
        val itemExistente = items.find { it.itemMenu == itemMenu }

        if (itemExistente != null) {
            // Actualizar la cantidad
            itemExistente.cantidad = cantidad
        } else {
            // Agregar un nuevo ItemMesa si no existe
            items.add(ItemMesa(itemMenu, cantidad))
        }
    }

    fun calculototalSinpropina(): Int {
        return items.sumOf { it.calcularSubtotal() }
    }

    fun calculoPropina(): Int {
        return if (aceptaPropina) (calculototalSinpropina() * 0.1).toInt() else 0
    }

    fun CalculototaljuntoPropina(): Int {
        return calculototalSinpropina() + calculoPropina()
    }
}

