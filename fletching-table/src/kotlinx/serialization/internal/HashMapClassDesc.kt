package kotlinx.serialization.internal

import kotlinx.serialization.descriptors.SerialDescriptor

internal class HashMapClassDesc(keyDesc: SerialDescriptor, valueDesc: SerialDescriptor) : MapLikeDescriptor("kotlin.collections.HashMap", keyDesc, valueDesc)
