package poc.repositories.post

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.*
import poc.entities.Post

object Posts : Table() {
  val id = integer("id").autoIncrement()
  val title = varchar("title", length = 100).uniqueIndex()
  val content = text("content").nullable()

  override val primaryKey = PrimaryKey(id)
}

fun Posts.getById(id: Int) =
    transaction {
      val result = Posts.slice(Posts.title, Posts.content).select { Posts.id eq id }.single()

      Post(id = id, title = result[Posts.title], content = result[Posts.content])
    }

fun Posts.insert(title: String, content: String?) =
    transaction {
      val post = Post(title = title, content = content)

      val id =
          Posts.insert {
            it[Posts.title] = post.title
            it[Posts.content] = post.content
          } get Posts.id

      post.copy(id = id)
    }
