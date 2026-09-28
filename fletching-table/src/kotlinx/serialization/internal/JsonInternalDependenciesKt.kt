package kotlinx.serialization.internal

import kotlinx.serialization.descriptors.SerialDescriptor

@CoreFriendModuleApi
public fun SerialDescriptor.jsonCachedSerialNames(): Set<String> {
   return Platform_commonKt.cachedSerialNames(`$this$jsonCachedSerialNames`);
}
