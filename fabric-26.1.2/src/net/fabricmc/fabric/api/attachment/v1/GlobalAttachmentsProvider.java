package net.fabricmc.fabric.api.attachment.v1;

public interface GlobalAttachmentsProvider {
   default GlobalAttachments globalAttachments() {
      throw new UnsupportedOperationException("Implemented via mixin!");
   }
}
