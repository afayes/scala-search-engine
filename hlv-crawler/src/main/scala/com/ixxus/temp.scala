package com.ixxus

import java.util.{Timer, TimerTask}
import java.util.concurrent.{ArrayBlockingQueue, BlockingQueue}

import scala.concurrent.Future
import scala.util.Random
import scala.concurrent.ExecutionContext.Implicits.global

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

//  val pattern = "word1.* word2.".r
  //  val text = "hello word1 word2 hello2 hellow word1 word1 word2"
  //  println(pattern.findAllMatchIn(text).size)
  //
  //  println(List(1, 2, "helo").slice(0, 100))
  //
  //  println(List(1, 5, 2, 5).sortWith((n1, n2) => {
  //    if (n1 > n2) true
  //    if (n1 < n2) false
  //    n1 - n2 > 0
  //  }).toSeq)

  val q:BlockingQueue[Int] = new ArrayBlockingQueue[Int](100)

  val t = new Timer()
  t.schedule(new TimerTask {
    override def run() = {
      q.put(Random.nextInt(100))
    }
  }, 50,50)

  t.schedule(new TimerTask {
    override def run() = {
      println(q.take())
    }
  }, 50, 50)


  Future {
    while (true) {
      println("hello")
      //Thread.sleep(50)
    }
  }

  println ("abul fayes")
}
