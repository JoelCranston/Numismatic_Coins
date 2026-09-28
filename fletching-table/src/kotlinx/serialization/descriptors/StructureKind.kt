package kotlinx.serialization.descriptors

public sealed class StructureKind protected constructor() : SerialKind() {
   public object CLASS : StructureKind()

   public object LIST : StructureKind()

   public object MAP : StructureKind()

   public object OBJECT : StructureKind()
}
