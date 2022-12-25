package com.vjh0107.barcode.mmintegration.mythicmobs.mechanics

import io.lumine.mythic.api.config.MythicLineConfig
import io.lumine.mythic.bukkit.MythicBukkit
import io.lumine.mythic.bukkit.events.MythicMechanicLoadEvent
import io.lumine.mythic.core.skills.SkillMechanic

abstract class BarcodeMechanic(
    val event: MythicMechanicLoadEvent,
    val line: String = event.mechanicName,
    val mlc: MythicLineConfig = event.config
) : SkillMechanic(MythicBukkit.inst().skillManager, line, mlc) {
    fun register(event: MythicMechanicLoadEvent) {
        event.register(this)
    }
}