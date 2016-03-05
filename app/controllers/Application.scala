package controllers

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.Source
import scala.concurrent.ExecutionContext.Implicits.global
import play.api._
import play.api.mvc._

class Application extends Controller {
  def index = Action {
    implicit val system = ActorSystem("Sys")
    implicit val materializer = ActorMaterializer()
    Source((1 to 10000).toVector).
      runForeach(println).
      onComplete(_ => system.shutdown())

    Ok(views.html.index("Your new application is ready."))
  }

}
