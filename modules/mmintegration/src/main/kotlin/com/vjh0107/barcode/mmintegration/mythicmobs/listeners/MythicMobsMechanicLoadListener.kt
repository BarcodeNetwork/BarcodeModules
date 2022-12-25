package com.vjh0107.barcode.mmintegration.mythicmobs.listeners

import com.vjh0107.barcode.framework.component.BarcodeComponent
import com.vjh0107.barcode.framework.component.BarcodeListener
import com.vjh0107.barcode.mmintegration.mythicmobs.mechanics.BarcodeMechanic
import io.lumine.mythic.bukkit.events.MythicMechanicLoadEvent
import org.bukkit.event.EventHandler
import org.koin.core.component.KoinComponent
import org.koin.core.parameter.parametersOf
import org.koin.core.qualifier.named

@BarcodeComponent
class MythicMobsMechanicLoadListener : BarcodeListener, KoinComponent {
    @EventHandler
    fun onMythicMechanicLoad(event: MythicMechanicLoadEvent) {
        val mechanic by getKoin().injectOrNull<BarcodeMechanic>(named(event.mechanicName.lowercase())) {
            parametersOf(event)
        }
        mechanic?.register(event)
    }
}