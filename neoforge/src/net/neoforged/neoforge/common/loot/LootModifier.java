package net.neoforged.neoforge.common.loot;

import com.mojang.datafixers.Products.P2;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder.Instance;
import com.mojang.serialization.codecs.RecordCodecBuilder.Mu;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import java.util.List;
import java.util.function.Predicate;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.AllOfCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

public abstract class LootModifier implements IGlobalLootModifier {
   protected final LootItemCondition[] conditions;
   protected final int priority;
   private final Predicate<LootContext> combinedConditions;

   protected static <T extends LootModifier> P2<Mu<T>, LootItemCondition[], Integer> codecStart(Instance<T> instance) {
      return instance.group(
         LOOT_CONDITIONS_CODEC.fieldOf("conditions").forGetter(lm -> lm.conditions), Codec.INT.optionalFieldOf("priority", 1000).forGetter(lm -> lm.priority)
      );
   }

   protected LootModifier(LootItemCondition[] conditions, int priority) {
      this.conditions = conditions;
      this.combinedConditions = AllOfCondition.allOf(List.of(conditions));
      this.priority = priority;
   }

   @Override
   public final ObjectArrayList<ItemStack> apply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
      return this.combinedConditions.test(context) ? this.doApply(generatedLoot, context) : generatedLoot;
   }

   @Override
   public int priority() {
      return this.priority;
   }

   protected abstract ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> var1, LootContext var2);
}
