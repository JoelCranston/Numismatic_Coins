package kotlinx.serialization.descriptors

public sealed class PrimitiveKind protected constructor() : SerialKind() {
   public object BOOLEAN : PrimitiveKind()

   public object BYTE : PrimitiveKind()

   public object CHAR : PrimitiveKind()

   public object DOUBLE : PrimitiveKind()

   public object FLOAT : PrimitiveKind()

   public object INT : PrimitiveKind()

   public object LONG : PrimitiveKind()

   public object SHORT : PrimitiveKind()

   public object STRING : PrimitiveKind()
}
