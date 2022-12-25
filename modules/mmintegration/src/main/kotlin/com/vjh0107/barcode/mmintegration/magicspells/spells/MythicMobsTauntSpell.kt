package com.vjh0107.barcode.mmintegration.magicspells.spells

import com.nisovin.magicspells.spells.TargetedEntitySpell
import com.nisovin.magicspells.spells.TargetedSpell
import com.nisovin.magicspells.util.MagicConfig
import io.lumine.mythic.bukkit.MythicBukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.LivingEntity

class MythicMobsTauntSpell(config: MagicConfig?, spellName: String?) : TargetedSpell(config, spellName), TargetedEntitySpell {
    override fun castSpell(sender: LivingEntity, state: SpellCastState, power: Float, args: Array<String>?): PostCastAction {
        return PostCastAction.HANDLE_NORMALLY
    }

    override fun castFromConsole(sender: CommandSender, args: Array<String>): Boolean {
        return true
    }

    override fun castAtEntity(caster: LivingEntity?, target: LivingEntity?, p2: Float): Boolean {
        MythicBukkit.inst().apiHelper.taunt(target, caster)
        return true
    }

    override fun castAtEntity(p0: LivingEntity?, p1: Float): Boolean {
        return true
    }
}