package poc.entities

import org.valiktor.functions.*
import org.valiktor.validate

data class Post(val id: Int? = null, val title: String, val content: String?) {
  init {
    validate(this) {
      validate(Post::id).isPositive()
      validate(Post::title).hasSize(min = 1, max = 80)
    }
  }
}
