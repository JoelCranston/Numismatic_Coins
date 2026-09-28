package net.peanuuutz.tomlkt.internal.parser

internal fun KeyNode.addByPath(path: List<String>, node: TreeNode, arrayOfTableIndices: Map<List<String>, Int>?): Boolean {
   return addByPathRecursively(`$this$addByPath`, path, node, arrayOfTableIndices, 0);
}

private tailrec fun KeyNode.addByPathRecursively(path: List<String>, node: TreeNode, arrayOfTableIndices: Map<List<String>, Int>?, index: Int): Boolean {
   while (true) {
      val child: TreeNode = `$this$addByPathRecursively`.get(path.get(index) as java.lang.String);
      if (index == CollectionsKt.getLastIndex(path)) {
         val var17: Boolean;
         if (child == null) {
            KeyNode.add$default(`$this$addByPathRecursively`, node, null, 2, null);
            var17 = true;
         } else {
            var17 = child is KeyNode && node is KeyNode && !(child as KeyNode).isLast();
         }

         return var17;
      }

      if (child == null) {
         val var15: KeyNode = new KeyNode(path.get(index) as java.lang.String, node is ValueNode);
         KeyNode.add$default(`$this$addByPathRecursively`, var15, null, 2, null);
         val var16: Int = index + 1;
         `$this$addByPathRecursively` = var15;
         path = path;
         node = node;
         arrayOfTableIndices = arrayOfTableIndices;
         index = var16;
      } else if (child is KeyNode) {
         val var14: KeyNode = child as KeyNode;
         val var11: Int = index + 1;
         `$this$addByPathRecursively` = var14;
         path = path;
         node = node;
         arrayOfTableIndices = arrayOfTableIndices;
         index = var11;
      } else {
         if (child !is ArrayNode) {
            if (child is ValueNode) {
               return false;
            }

            throw new NoWhenBranchMatchedException();
         }

         if (arrayOfTableIndices == null) {
            throw new IllegalStateException("Check failed.");
         }

         val var10000: Any = arrayOfTableIndices.get(path.subList(0, index + 1));
         val grandChild: KeyNode = (child as ArrayNode).get((var10000 as java.lang.Number).intValue());
         val var13: Int = index + 1;
         `$this$addByPathRecursively` = grandChild;
         path = path;
         node = node;
         arrayOfTableIndices = arrayOfTableIndices;
         index = var13;
      }
   }
}

internal fun <N : TreeNode> KeyNode.getByPath(path: List<String>, arrayOfTableIndices: Map<List<String>, Int>?): N {
   return (N)getByPathRecursively(`$this$getByPath`, path, arrayOfTableIndices, 0);
}

private tailrec fun <N : TreeNode> KeyNode.getByPathRecursively(path: List<String>, arrayOfTableIndices: Map<List<String>, Int>?, index: Int): N {
   while (true) {
      val child: TreeNode = `$this$getByPathRecursively`.get(path.get(index) as java.lang.String);
      if (index == CollectionsKt.getLastIndex(path)) {
         var var13: TreeNode = child;
         if (child == null) {
            var13 = null;
         }

         if (var13 == null) {
            throw new IllegalStateException(("Node on $path not found").toString());
         }

         return (N)var13;
      }

      if (child != null && child !is ValueNode) {
         if (child is KeyNode) {
            val var12: KeyNode = child as KeyNode;
            val var9: Int = index + 1;
            `$this$getByPathRecursively` = var12;
            path = path;
            arrayOfTableIndices = arrayOfTableIndices;
            index = var9;
            continue;
         }

         if (child is ArrayNode) {
            if (arrayOfTableIndices == null) {
               throw new IllegalStateException("Check failed.");
            }

            val var10000: Any = arrayOfTableIndices.get(path.subList(0, index + 1));
            val grandChild: KeyNode = (child as ArrayNode).get((var10000 as java.lang.Number).intValue());
            val var11: Int = index + 1;
            `$this$getByPathRecursively` = grandChild;
            path = path;
            arrayOfTableIndices = arrayOfTableIndices;
            index = var11;
            continue;
         }

         throw new NoWhenBranchMatchedException();
      }

      throw new IllegalStateException(("Node on $path not found").toString());
   }
}
