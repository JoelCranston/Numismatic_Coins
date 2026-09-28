package kotlinx.serialization.json.internal

import kotlin.jvm.internal.SourceDebugExtension
import kotlinx.serialization.json.Json

@SourceDebugExtension(["SMAP\nComposers.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Composers.kt\nkotlinx/serialization/json/internal/ComposerWithPrettyPrint\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,104:1\n1#2:105\n*E\n"])
internal class ComposerWithPrettyPrint(writer: InternalJsonWriter, json: Json) : Composer(writer) {
   private final val json: Json
   private final var level: Int

   init {
      this.json = json;
   }

   public override fun indent() {
      this.setWritingFirst(true);
      val var1: Int = this.level++;
   }

   public override fun unIndent() {
      this.level += -1;
   }

   public override fun nextItem() {
      this.setWritingFirst(false);
      this.print("\n");
      val var1: Int = this.level;

      for (int var2 = 0; var2 < var1; var2++) {
         this.print(this.json.getConfiguration().getPrettyPrintIndent());
      }
   }

   public override fun nextItemIfNotFirst() {
      if (this.getWritingFirst()) {
         this.setWritingFirst(false);
      } else {
         this.nextItem();
      }
   }

   public override fun space() {
      this.print(' ');
   }
}
