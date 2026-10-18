package net.neoforged.neoforge.attachment;

import net.minecraft.core.HolderLookup.Provider;
import org.jspecify.annotations.Nullable;

public interface IAttachmentCopyHandler<T> {
   @Nullable
   T copy(T var1, IAttachmentHolder var2, Provider var3);
}
