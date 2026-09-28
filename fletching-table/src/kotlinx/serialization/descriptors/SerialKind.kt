package kotlinx.serialization.descriptors

public sealed class SerialKind protected constructor() {
   public override fun toString(): String {
      val var10000: java.lang.String = (this.getClass()::class).getSimpleName();
      return var10000;
   }

   public override fun hashCode(): Int {
      return this.toString().hashCode();
   }

   public object CONTEXTUAL : SerialKind()

   public object ENUM : SerialKind()
}
