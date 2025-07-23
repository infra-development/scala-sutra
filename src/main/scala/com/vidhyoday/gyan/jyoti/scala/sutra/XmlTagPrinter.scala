package com.vidhyoday.gyan.jyoti.scala.sutra

import scala.xml._

object XmlTagPrinter {
  def main(args: Array[String]): Unit = {
    val file = "modules/scala-sutra/src/main/resources/sample_mx.xml"
    val xml = XML.loadFile(file)

    println("All XML tags and their text values:")
    printTags(xml)
  }

  def printTags(node: Node, indent: String = ""): Unit = {
    val tag = node.label
    val text = node.text.trim
    if (node.child.nonEmpty && text.nonEmpty) {
      println(s"$indent<$tag> => $text")
    }
    node.child.filter(_.isInstanceOf[Elem]).foreach(child => printTags(child, indent + "  "))
  }
}

