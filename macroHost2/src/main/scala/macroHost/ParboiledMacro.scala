package macroHost


import scala.language.experimental.macros
import scala.reflect.macros.blackbox.Context
import java.nio.file.Paths
import scala.reflect.internal.util.RangePosition
import java.io.File
import org.apache.commons.io.FileUtils
import scala.util.Success
import scala.util.Failure


object ParboiledMacro {
  
  def sql(path: String): java.net.URL = macro assertImpl

  def assertImpl(c: Context)(path: c.Expr[String]): c.Expr[java.net.URL] = {
    import c.universe._

    path.tree match {
      case t @ Literal(Constant(const: String)) => {

        val paths = c.classPath.map { url => Paths.get(url.toURI()).toFile() }.filter(_.isDirectory())


        val potentialLocations = paths.map { dir => new File(dir.getCanonicalPath() + const) }

        
        
        if (!potentialLocations.exists { f => f.exists() }) {

          c.warning(t.pos, "file not found")
        }else{
        
        val file =potentialLocations.filter{ f => f.exists() }.last

        //TODo: encodinh weirdness

        val str = FileUtils.readFileToString(file)

        import org.parboiled2._
        val rr = DdlParser(str).DDL.run()

        rr match {
          case Success(res) =>
            {}// c.warning(t.pos, "worked")
          case Failure(e:ParseError) =>
            c.warning(t.pos, ""+ e.toString())
        }
        
        
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

  }

  //TODO: asStram 
  //TODO: as text

}