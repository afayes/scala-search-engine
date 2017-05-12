package com.ixxus

/**
  * todo add comments.
  */
// todo delete
object temp extends App {

//  val htmlPattern = ("<html.*>.*").r
//  //val htmlPattern = "<html.*>.*".r
//  //val string = "a c c<html>\n<html lang=\"en-GB\" id=\"responsive-news\">aaaaa    </html>"
//  //val string = "<!DOCTYPE html>\n<!--[if lte IE 9]>\n  <html lang=\"en-GB\" class=\"no-js no-flexbox no-flexboxlegacy\">\n<![endif]-->\n<!--[if gt IE 9]><!-->\n  <html lang=\"en-GB\" class=\"no-js\">\n<!--<![endif]-->\n<head> </html>"
//  val string = "<!DOCTYPE html>\n<!--[if lte IE 9]>\n  <html lang=\"en-GB\" class=\"no-js no-flexbox no-flexboxlegacy\">\n<![endif]-->\n<!--[if gt IE 9]><!-->\n  <html lang=\"en-GB\" class=\"no-js\">\n<!--<![endif]-->\n<head> </html>"
//  println(htmlPattern.findFirstIn(string).get)

  val pattern = "word1.* word2.".r
  val text = "hello word1 word2 hello2 hellow word1 word1 word2"
  println(pattern.findAllMatchIn(text).size)

  println(List(1, 2, "helo").slice(0, 100))
}
