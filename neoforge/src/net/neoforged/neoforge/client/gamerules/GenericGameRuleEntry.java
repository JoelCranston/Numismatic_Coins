package net.neoforged.neoforge.client.gamerules;

import com.mojang.serialization.DataResult;
import java.util.List;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.worldselection.AbstractGameRulesScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.level.gamerules.GameRule;

public class GenericGameRuleEntry<T> extends GameRuleEntry {
   private final EditBox input;

   public GenericGameRuleEntry(AbstractGameRulesScreen screen, Component label, List<FormattedCharSequence> tooltip, String narration, GameRule<T> gameRule) {
      super(screen.getFont(), tooltip, label);
      this.input = new EditBox(screen.getFont(), 10, 5, 44, 20, label.copy().append("\n").append(narration).append("\n"));
      this.input.setValue(screen.gameRules.getAsString(gameRule));
      this.input.setResponder(v -> {
         DataResult<T> value = gameRule.deserialize(v);
         if (value.isSuccess()) {
            this.input.setTextColor(-2039584);
            screen.clearInvalid(this);
            screen.gameRules.set(gameRule, value.getOrThrow(), null);
         } else {
            this.input.setTextColor(-65536);
            screen.markInvalid(this);
         }
      });
      this.children.add(this.input);
   }

   public void extractContent(GuiGraphicsExtractor graphics, int mouseX, int mouseY, boolean hovered, float a) {
      this.renderLabel(graphics, this.getContentY(), this.getContentX());
      this.input.setX(this.getContentRight() - 45);
      this.input.setY(this.getContentY());
      this.input.extractRenderState(graphics, mouseX, mouseY, a);
   }
}
