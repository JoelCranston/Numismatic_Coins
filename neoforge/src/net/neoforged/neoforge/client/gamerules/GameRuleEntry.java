package net.neoforged.neoforge.client.gamerules;

import com.google.common.collect.Lists;
import java.util.List;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.screens.worldselection.AbstractGameRulesScreen.RuleEntry;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;
import org.jspecify.annotations.Nullable;

public abstract class GameRuleEntry extends RuleEntry {
   private final List<FormattedCharSequence> label;
   protected final List<AbstractWidget> children = Lists.newArrayList();
   protected final Font font;

   public GameRuleEntry(Font font, @Nullable List<FormattedCharSequence> tooltip, Component label) {
      super(tooltip);
      this.font = font;
      this.label = font.split(label, 170);
   }

   public List<? extends GuiEventListener> children() {
      return this.children;
   }

   public List<? extends NarratableEntry> narratables() {
      return this.children;
   }

   protected void renderLabel(GuiGraphicsExtractor graphics, int rowTop, int rowLeft) {
      if (this.label.size() == 1) {
         graphics.text(this.font, this.label.getFirst(), rowLeft, rowTop + 5, -1);
      } else if (this.label.size() >= 2) {
         graphics.text(this.font, this.label.getFirst(), rowLeft, rowTop, -1);
         graphics.text(this.font, this.label.get(1), rowLeft, rowTop + 10, -1);
      }
   }
}
