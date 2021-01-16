package poc.schema

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.*
import poc.repositories.*

class PostQuery {
  fun postById(id: Int) = Posts.getById(id = id)
}

class PostMutation {
  fun savePost(title: String, content: String? = null) =
      Posts.insert(title = title, content = content)
}
