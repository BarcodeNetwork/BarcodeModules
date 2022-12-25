package com.vjh0107.barcode.mmintegration.magicspells.spells

import com.nisovin.magicspells.spells.InstantSpell
import com.nisovin.magicspells.util.MagicConfig
import io.lumine.mythic.bukkit.MythicBukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player

class MythicMobsMechanicSpell(config: MagicConfig?, spellName: String?) : InstantSpell(config, spellName) {
    private var skillName: String = getConfigString("skillName", "준형준형")

    override fun castSpell(sender: LivingEntity, state: SpellCastState, power: Float, args: Array<String>?): PostCastAction {
        val player = sender as Player
        val spell = skillName
        MythicBukkit.inst().apiHelper.castSkill(player, spell)
        return PostCastAction.HANDLE_NORMALLY
    }

    override fun castFromConsole(sender: CommandSender, args: Array<String>): Boolean {
        return true
    }
}