package net.neoforged.neoforge.client.gamerules;

import java.util.List;
import net.minecraft.client.gui.screens.worldselection.AbstractGameRulesScreen;
import net.minecraft.client.gui.screens.worldselection.AbstractGameRulesScreen.EntryFactory;
import net.minecraft.client.gui.screens.worldselection.AbstractGameRulesScreen.RuleEntry;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.level.gamerules.GameRule;

@FunctionalInterface
public interface GameRuleEntryFactory<T> {
   RuleEntry create(AbstractGameRulesScreen var1, Component var2, List<FormattedCharSequence> var3, String var4, GameRule<T> var5);

   default EntryFactory<T> toVanilla(AbstractGameRulesScreen screen) {
      return (label, tooltip, str, gameRule) -> this.create(screen, label, tooltip, str, gameRule);
   }
}
