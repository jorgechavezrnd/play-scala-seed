package controllers

import javax.inject._
import play.api.mvc._
import play.api.libs.json.Json
import models.{Movie, MovieRepository}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class MovieController @Inject()(val controllerComponents: ControllerComponents,
                                val movieRepository: MovieRepository) extends BaseController {

  implicit val serializador = Json.format[Movie]

  def getMovies = Action.async {
    movieRepository
      .getAll
      .map(movies => {
        val j = Json.obj(
          "data" -> movies,
          "message" -> "Movies listed"
        )
        Ok(j)
      })
  }

  def getMovie(id: String) = Action.async {
    movieRepository
      .getOne(id)
      .map(movie => {
        val j = Json.obj(
          "data" -> movie,
          "message" -> "Movie found"
        )
        Ok(j)
      })
  }

  def createMovie = Action.async(parse.json) { request =>
    val validador = request.body.validate[Movie]

    validador.asEither match {
      case Left(error) => Future.successful(BadRequest(error.toString()))
      case Right(movie) => {
        movieRepository
          .create(movie)
          .map(movie => {
            val j = Json.obj(
              "data" -> movie,
              "message" -> "Movies created"
            )
            Ok(j)
          })
      }
    }
  }

  def updateMovie(id: String) = Action.async(parse.json) { request =>
    val validador = request.body.validate[Movie]

    validador.asEither match {
      case Left(error) => Future.successful(BadRequest(error.toString()))
      case Right(movie) => {
        movieRepository
          .update(id, movie)
          .map(movie => {
            val j = Json.obj(
              "data" -> movie,
              "message" -> "Movies updated"
            )
            Ok(j)
          })
      }
    }
  }

  def deleteMovie(id: String) = Action.async {
    movieRepository
      .delete(id)
      .map(movie => {
        val j = Json.obj(
          "data" -> movie,
          "message" -> "Movie deleted"
        )
        Ok(j)
      })
  }
}
