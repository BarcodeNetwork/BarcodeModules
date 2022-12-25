package com.vjh0107.barcode.mmintegration.mythicmobs.mechanics.impl

import com.nisovin.magicspells.MagicSpells
import com.vjh0107.barcode.mmintegration.mythicmobs.mechanics.BarcodeMechanic
import io.lumine.mythic.api.skills.INoTargetSkill
import io.lumine.mythic.api.skills.SkillMetadata
import io.lumine.mythic.api.skills.SkillResult
import io.lumine.mythic.api.skills.placeholders.PlaceholderString
import io.lumine.mythic.bukkit.events.MythicMechanicLoadEvent
import io.lumine.mythic.core.utils.annotations.MythicMechanic
import org.bukkit.entity.LivingEntity
import org.koin.core.annotation.Factory
import org.koin.core.annotation.InjectedParam
import org.koin.core.annotation.Named
import java.security.InvalidParameterException

@Factory(binds = [BarcodeMechanic::class])
@Named("castmagicspells")
@MythicMechanic(author = "vjh0107", name = "castmagicspells", aliases = ["cms"])
class MagicSpellsSpellCastMechanic(@InjectedParam event: MythicMechanicLoadEvent) : BarcodeMechanic(event), INoTargetSkill {
    private var spellName: PlaceholderString = PlaceholderString.of(mlc.getString(arrayOf("spell", "s"), "준형준형", *arrayOfNulls(0)))

    override fun cast(data: SkillMetadata?): SkillResult {
        val target = data!!.caster.entity
        return if (!(target.isDead || target.health <= 0)) {
            val livingEntity: LivingEntity = data.caster.entity.bukkitEntity as LivingEntity
            MagicSpells.getSpellByInGameName(this.spellName.get(data))?.cast(livingEntity) ?: throw InvalidParameterException("$spellName 스펠을 찾을 수 없습니다.")
            SkillResult.SUCCESS
        } else {
            SkillResult.CONDITION_FAILED
        }
    }
}