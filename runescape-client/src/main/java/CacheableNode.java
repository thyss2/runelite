import net.runelite.mapping.Export;
import net.runelite.mapping.Implements;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("ht")
@Implements("CacheableNode")
public class CacheableNode extends Node {
   @ObfuscatedName("ce")
   @ObfuscatedSignature(
      signature = "Lht;"
   )
   @Export("previous")
   public CacheableNode previous;
   @ObfuscatedName("ca")
   @ObfuscatedSignature(
      signature = "Lht;"
   )
   @Export("next")
   public CacheableNode next;

   @ObfuscatedName("cs")
   @Export("unlinkDual")
   public void unlinkDual() {
      if(this.next != null) {
         this.next.previous = this.previous;
         this.previous.next = this.next;
         this.previous = null;
         this.next = null;
      }
   }
}
