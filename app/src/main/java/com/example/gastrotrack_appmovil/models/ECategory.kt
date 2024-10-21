package com.example.gastrotrack_appmovil.models

enum class ECategory(val id: Int) {
    Bebidas(1),
    FrutasVerduras(2),
    CarnesPescados(3),
    LácteosHuevos(4),
    PanaderíaRepostería(5),
    BebidasAlcohólicas(6),
    ConservasCongelados(7),
    EspeciasCondimentos(8),
    DulcesSnacks(9),
    AceitesVinagres(10);

    companion object {
        fun fromId(id: Int): ECategory {
            return values().first { it.id == id }
        }
    }
}