import java.util.Iterator;
import net.runelite.mapping.Export;
import net.runelite.mapping.Implements;
import net.runelite.mapping.ObfuscatedName;
import net.runelite.mapping.ObfuscatedSignature;

@ObfuscatedName("gv")
@Implements("IterableHashTable")
public final class IterableHashTable implements Iterable {
   @ObfuscatedName("b")
   @Export("size")
   int size;
   @ObfuscatedName("q")
   @ObfuscatedSignature(
      signature = "[Lhe;"
   )
   @Export("buckets")
   Node[] buckets;
   @ObfuscatedName("o")
   @ObfuscatedSignature(
      signature = "Lhe;"
   )
   @Export("head")
   Node head;
   @ObfuscatedName("p")
   @ObfuscatedSignature(
      signature = "Lhe;"
   )
   @Export("tail")
   Node tail;
   @ObfuscatedName("a")
   @Export("index")
   int index;

   public IterableHashTable(int var1) {
      this.index = 0;
      this.size = var1;
      this.buckets = new Node[var1];

      for(int var2 = 0; var2 < var1; ++var2) {
         Node var3 = this.buckets[var2] = new Node();
         var3.next = var3;
         var3.previous = var3;
      }

   }

   @ObfuscatedName("b")
   @ObfuscatedSignature(
      signature = "(J)Lhe;"
   )
   @Export("get")
   public Node get(long var1) {
      Node var3 = this.buckets[(int)(var1 & (long)(this.size - 1))];

      for(this.head = var3.next; var3 != this.head; this.head = this.head.next) {
         if(this.head.hash == var1) {
            Node var4 = this.head;
            this.head = this.head.next;
            return var4;
         }
      }

      this.head = null;
      return null;
   }

   @ObfuscatedName("q")
   @ObfuscatedSignature(
      signature = "(Lhe;J)V"
   )
   @Export("put")
   public void put(Node var1, long var2) {
      if(var1.previous != null) {
         var1.unlink();
      }

      Node var4 = this.buckets[(int)(var2 & (long)(this.size - 1))];
      var1.previous = var4.previous;
      var1.next = var4;
      var1.previous.next = var1;
      var1.next.previous = var1;
      var1.hash = var2;
   }

   @ObfuscatedName("o")
   @Export("clear")
   public void clear() {
      for(int var1 = 0; var1 < this.size; ++var1) {
         Node var2 = this.buckets[var1];

         while(true) {
            Node var3 = var2.next;
            if(var3 == var2) {
               break;
            }

            var3.unlink();
         }
      }

      this.head = null;
      this.tail = null;
   }

   @ObfuscatedName("p")
   @ObfuscatedSignature(
      signature = "()Lhe;"
   )
   @Export("getHead")
   public Node getHead() {
      this.index = 0;
      return this.getTail();
   }

   @ObfuscatedName("a")
   @ObfuscatedSignature(
      signature = "()Lhe;"
   )
   @Export("getTail")
   public Node getTail() {
      Node var1;
      if(this.index > 0 && this.buckets[this.index - 1] != this.tail) {
         var1 = this.tail;
         this.tail = var1.next;
         return var1;
      } else {
         do {
            if(this.index >= this.size) {
               return null;
            }

            var1 = this.buckets[this.index++].next;
         } while(var1 == this.buckets[this.index - 1]);

         this.tail = var1.next;
         return var1;
      }
   }

   public Iterator iterator() {
      return new HashTableIterator(this);
   }
}
