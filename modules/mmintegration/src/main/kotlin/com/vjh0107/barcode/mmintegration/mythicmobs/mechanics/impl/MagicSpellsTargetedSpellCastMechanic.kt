package com.vjh0107.barcode.mmintegration.mythicmobs.mechanics.impl

import com.nisovin.magicspells.MagicSpells
import com.nisovin.magicspells.spells.TargetedEntitySpell
import com.vjh0107.barcode.mmintegration.mythicmobs.mechanics.BarcodeMechanic
import io.lumine.mythic.api.adapters.AbstractEntity
import io.lumine.mythic.api.skills.ITargetedEntitySkill
import io.lumine.mythic.api.skills.SkillMetadata
import io.lumine.mythic.api.skills.SkillResult
import io.lumine.mythic.api.skills.placeholders.PlaceholderString
import io.lumine.mythic.bukkit.events.MythicMechanicLoadEvent
import io.lumine.mythic.core.utils.annotations.MythicMechanic
import org.bukkit.entity.LivingEntity
import org.koin.core.annotation.Factory
import org.koin.core.annotation.InjectedParam
import org.koin.core.annotation.Named

@Factory(binds = [BarcodeMechanic::class])
@Named("casttargetedmagicspells")
@MythicMechanic(author = "vjh0107", name = "casttargetedmagicspells", aliases = ["ctms"])
class MagicSpellsTargetedSpellCastMechanic(@InjectedParam event: MythicMechanicLoadEvent) : BarcodeMechanic(event), ITargetedEntitySkill {
    private var spellName: PlaceholderString = PlaceholderString.of(mlc.getString(arrayOf("spell", "s"), "준형준형", *arrayOfNulls(0)))

    override fun castAtEntity(data: SkillMetadata?, targetEntity: AbstractEntity?): SkillResult {
        val caster = data!!.caster.entity
        val target = targetEntity ?: return SkillResult.CONDITION_FAILED
        val casterL: LivingEntity = caster.bukkitEntity as LivingEntity
        val targetL: LivingEntity = target.bukkitEntity as LivingEntity? ?: return SkillResult.CONDITION_FAILED
        return if (!(caster.isDead || caster.health <= 0)) {
            try {
                val spell = MagicSpells.getSpellByInGameName(this.spellName.get(data)) as TargetedEntitySpell
                spell.castAtEntity(casterL, targetL, 1F)
            } catch (event: ClassCastException) {
                throw ClassCastException("스펠 ${this.spellName} 이 TargetedEntitySpell 이 아닙니다.")
            }
            SkillResult.SUCCESS
        } else {
            SkillResult.CONDITION_FAILED
        }
    }
}