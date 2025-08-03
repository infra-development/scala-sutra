import java.io.FileOutputStream
import scala.util.Using

trait ByteEncodable {
  def encode(): Array[Byte]
}

trait Channel {
  def write(obj: ByteEncodable): Unit
}

case class FullName(firstName: String, lastName: String) extends ByteEncodable {
  override def encode(): Array[Byte] = {
    firstName.getBytes ++ lastName.getBytes
  }
}

val projectRoot = "C:/Users/SG911PP/work/repos/gyaan-jyoti"
val filePath = s"$projectRoot/modules/scala-sutra/src/main/resources/test.txt"


object FileChannel extends Channel {

  override def write(obj: ByteEncodable): Unit = {
    val bytes = obj.encode()
    Using(new FileOutputStream(filePath, true)) { os =>
      os.write(bytes)
      os.flush()
    }.getOrElse(throw new RuntimeException("Failed to write to file"))
  }
}

FileChannel.write(FullName("John", "Doe"))



