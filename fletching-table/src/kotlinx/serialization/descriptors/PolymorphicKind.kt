package kotlinx.serialization.descriptors

import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
public sealed class PolymorphicKind protected constructor() : SerialKind() {
   public object OPEN : PolymorphicKind()

   public object SEALED : PolymorphicKind()
}
