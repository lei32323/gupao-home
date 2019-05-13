package com.wl.thread.lock;

public class MarkWord {

   /**
    * 线程头信息
    */
   private Long threadId;

   /**
    * 验证是否相同
    * 
    * @param threadId
    * @return
    */
   public boolean compar(long threadId) {
      return this.threadId == threadId;
   }

   /**
    * 赋值，如果保存成功返回true,否则返回false
    * 
    * @param threadId
    * @return
    */
   public boolean assign(long threadId) {
      if (this.threadId == null) {
         this.threadId = threadId;
         return true;
      }
      return false;
   }

   /**
    * 清除
    */
   public void clear() {
      //只有当该线程没有执行字节码的时候 才去清除
      this.threadId = null;
   }

}