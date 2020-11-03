package models

import java.util.UUID
/*
  Objeto Movie.
 */
case class Movie(
  id: Option[String] = Option(UUID.randomUUID().toString),
  title: String,
  year: Int,
  cover: String,
  description: String,
  duration: Int,
  contentRating: String,
  source: String,
  tags: Option[String]
)

import slick.jdbc.SQLiteProfile.api._
/*
  Clase que mapea el objeto a la tabla movie.

  https://scala-slick.org/doc/3.3.1/schemas.html
 */
class MovieTable(tag: Tag) extends Table[Movie](tag, "movie") {
  def id = column[String]("id", O.PrimaryKey)
  def title = column[String]("title")
  def year = column[Int]("year")
  def cover = column[String]("cover")
  def description = column[String]("description")
  def duration = column[Int]("duration")
  def contentRating = column[String]("content_rating")
  def source = column[String]("source")
  def tags = column[Option[String]]("tags", O.Length(2000, varying = true))

  def * =
    (id.?, title, year, cover, description, duration, contentRating, source, tags) <> (Movie.tupled, Movie.unapply)
}

import javax.inject.Inject
import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import play.api.mvc.{AbstractController, ControllerComponents}
import scala.concurrent.{ExecutionContext, Future}
import slick.jdbc.JdbcProfile
/*
  Clase repository, donde irán las consultas.
 */
class MovieRepository @Inject()(
  protected val dbConfigProvider: DatabaseConfigProvider,
  cc: ControllerComponents
)(implicit ec: ExecutionContext)
  extends AbstractController(cc)
  with HasDatabaseConfigProvider[JdbcProfile] {

  private lazy val movieQuery = TableQuery[MovieTable]
}
