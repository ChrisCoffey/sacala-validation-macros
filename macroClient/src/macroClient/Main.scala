package macroClient

import macroHost._
import java.io.File

object Main {

  
  def main(args: Array[String]) {
    println("hi")
   
    
//    
//    val f= macroHost.Macros.assert("/rec/test.txt")
//        println(f)       
//        println(f)
        
        
    //TODO: deal with:
//    val ff= macroHost.Macros.assert("rec/deep/path/to/thing/test.txt")
        
        
    //TODO: deal with:
    val ff= macroHost.Macros.assert("/rec/deep/path/to/thing/test.txt")
    
        println(ff)
        
    val fff= macroHost.ParboiledMacro.sql("/rec/test.txt")
        
//        val ff = new File("/macroClient\\main.class")
//            println(ff)
//                        println(ff.getParentFile.getParentFile)
//                        
//                        
//                        var l=List(ff)
//                        while(l.last.getParentFile!=null){
//                          l = l.:+( l.last.getParentFile)
//                          println(l)
//                        }
  }
 
}