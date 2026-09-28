package kotlinx.serialization.json.internal

import java.util.Arrays
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.StructureKind

internal class JsonPath {
   private final var currentObjectPath: Array<Any?>
   private final var indicies: IntArray
   private final var currentDepth: Int

   public fun pushDescriptor(sd: SerialDescriptor) {
      this.currentDepth++;
      if (this.currentDepth == this.currentObjectPath.length) {
         this.resize();
      }

      this.currentObjectPath[this.currentDepth] = sd;
   }

   public fun updateDescriptorIndex(index: Int) {
      this.indicies[this.currentDepth] = index;
   }

   public fun updateCurrentMapKey(key: Any?) {
      if (this.indicies[this.currentDepth] != -2) {
         this.currentDepth++;
         if (this.currentDepth == this.currentObjectPath.length) {
            this.resize();
         }
      }

      this.currentObjectPath[this.currentDepth] = key;
      this.indicies[this.currentDepth] = -2;
   }

   public fun resetCurrentMapKey() {
      if (this.indicies[this.currentDepth] == -2) {
         this.currentObjectPath[this.currentDepth] = JsonPath.Tombstone.INSTANCE;
      }
   }

   public fun popDescriptor() {
      if (this.indicies[this.currentDepth] == -2) {
         this.indicies[this.currentDepth] = -1;
         this.currentDepth += -1;
      }

      if (this.currentDepth != -1) {
         this.currentDepth += -1;
      }
   }

   public fun getPath(): String {
      val var1: StringBuilder = new StringBuilder();
      val `$this$getPath_u24lambda_u241`: StringBuilder = var1;
      var1.append("$");
      val var4: Int = this.currentDepth + 1;

      for (int var5 = 0; var5 < var4; var5++) {
         val element: Any = this.currentObjectPath[var5];
         if (this.currentObjectPath[var5] is SerialDescriptor) {
            if ((element as SerialDescriptor).getKind() == StructureKind.LIST.INSTANCE) {
               if (this.indicies[var5] != -1) {
                  `$this$getPath_u24lambda_u241`.append("[");
                  `$this$getPath_u24lambda_u241`.append(this.indicies[var5]);
                  `$this$getPath_u24lambda_u241`.append("]");
               }
            } else {
               val idx: Int = this.indicies[var5];
               if (this.indicies[var5] >= 0) {
                  `$this$getPath_u24lambda_u241`.append(".");
                  `$this$getPath_u24lambda_u241`.append((element as SerialDescriptor).getElementName(idx));
               }
            }
         } else if (element != JsonPath.Tombstone.INSTANCE) {
            `$this$getPath_u24lambda_u241`.append("[");
            `$this$getPath_u24lambda_u241`.append("'");
            `$this$getPath_u24lambda_u241`.append(element);
            `$this$getPath_u24lambda_u241`.append("'");
            `$this$getPath_u24lambda_u241`.append("]");
         }
      }

      return var1.toString();
   }

   private fun prettyString(it: Any?): String {
      val var10000: SerialDescriptor = it as? SerialDescriptor;
      if ((it as? SerialDescriptor) != null) {
         val var2: java.lang.String = var10000.getSerialName();
         if (var2 != null) {
            return var2;
         }
      }

      return java.lang.String.valueOf(it);
   }

   private fun resize() {
      val newSize: Int = this.currentDepth * 2;
      val var10001: Array<Any> = Arrays.copyOf(this.currentObjectPath, this.currentDepth * 2);
      this.currentObjectPath = var10001;
      var var3: Int = 0;

      val var4: IntArray;
      for (var4 = new int[newSize]; var3 < newSize; var3++) {
         var4[var3] = -1;
      }

      ArraysKt.copyInto$default(this.indicies, var4, 0, 0, 0, 14, null);
      this.indicies = var4;
   }

   public override fun toString(): String {
      return this.getPath();
   }

   private object Tombstone
}
