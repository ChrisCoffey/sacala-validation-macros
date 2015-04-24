package macroHost

import scala.language.experimental.macros
import scala.reflect.macros.blackbox.Context
import java.nio.file.Paths
import scala.reflect.internal.util.RangePosition
import java.io.File

object Macros {
  
  //TODO: make string interpolator
  
  //TODO: maintain getrsources as stream exactly?

  //TODO: the slash or not the slash

  //TODO: more presific red squiggleies
  
  //TODO: rename

  //TODO: scala doc
  
//  TODO: test this works on maven sbt intelijay
  
  //TOODO: System.getProperty
  
  def assert(path: String): java.net.URL = macro assertImpl

  def assertImpl(c: Context)(path: c.Expr[String]): c.Expr[java.net.URL] = {
    import c.universe._

    path.tree match {
      case t @ Literal(Constant(const: String)) => {

        //       TODO: stupid cuniverse
        //       var p:Position =null
        //       c.universe.
        //TODO: bad 
        //TODO: triple quates will fuck this up
//               val rpos =t.pos.asInstanceOf[RangePosition]
//                
//                 //TODO: bounds check
//               val outpos= new RangePosition(rpos.source, rpos.start+1, rpos.point+1, rpos.end-1) 
//               
//               
//               
//               
//          c.abort(outpos.asInstanceOf[c.universe.Position], rpos.start +" "+ rpos.point  +" "+ rpos.end)
               
        //       
        //       c.

        val paths = c.classPath.map { url => Paths.get(url.toURI()).toFile() }.filter(_.isDirectory())

        //TODO: recursively explore the jars, till then this should only be a warning

        val potentialLocations = paths.map { dir => new File(dir.getCanonicalPath() + const) }

        if (!potentialLocations.exists { f => f.exists() }) {


          
          
          
          var l = List(new File(const))
          while (l.last.getParentFile != null) {
            l = l.:+(l.last.getParentFile)
          }
          
          //TODO: this is a little meh
          val maxpath =l.filter { pp => paths.exists { dir => (new File(dir.getCanonicalPath() +pp)).exists()} }.maxBy { _.toString().length }
          
          val rpos =t.pos.asInstanceOf[scala.reflect.internal.util.OffsetPosition]
          
          val outpos= new RangePosition(rpos.source, rpos.start+1+maxpath.toString().length(), rpos.point+1, rpos.end-1) 
          
//          potentialLocations

          //may require a build to move the file
          //TODO: better msg
          c.warning(outpos.asInstanceOf[c.universe.Position], "uhh oh")
        }
        //TODO: need to test a lot of cases

        
        reify {
          
          //TODO: null validation
          "".getClass().getResource((c.Expr[String] { Literal(Constant(const)) }.splice))
    }
        
//        q"""  "".getClass().getResource($const) """
      }
      //TODO quasy quats
      //    case q""" "hi" """ => {}
      //    TODO: warn on nonliteral string
      case _ => {
        //TODO: get for real
        reify {
          
          "".getClass().getResource((c.Expr[String] { Literal(Constant("")) }.splice))
    }
      }
    }
    //  

    //  c.prefix.tree match {
    //  
    //  val paths= c.classPath.map { url => Paths.get(url.toURI()).toFile() }.filter(_.isDirectory())
    //  
    //  //TODO: recursively explore the jars, till then this should only be a warning
    //  
    //  val cont = paths.map{dir.getCanonicalPath() + File.separator}
    //  
    //  String canonicalDir = dir.getCanonicalPath() + File.separator;
    //  
    //  paths.foldRight(false)((f,b) => f. )
    //  
    //  
    //  
    //  c.abort(c.enclosingPosition, path.toString())

  }

  //TODO: asStram 
  //TODO: as text
}