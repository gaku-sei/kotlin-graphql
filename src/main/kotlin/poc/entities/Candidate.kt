package poc.entities

import kotlin.random.Random
import org.valiktor.functions.*
import org.valiktor.validate

private const val alpha = "abcdefghijklmnopqrstuvwxyz"

data class Candidate(
    val id: Int? = null,
    val firstName: String,
    val lastName: String,
    val email: String,
) {
  init {
    validate(this) {
      validate(Candidate::id).isPositive()
      validate(Candidate::firstName).isNotBlank()
      validate(Candidate::lastName).isNotBlank()
      validate(Candidate::email).isNotBlank().isEmail()
    }
  }

  companion object {
    fun random(id: Int): Candidate {
      val firstName =
          (0..6)
              .map { alpha.get(Random.nextInt(0, alpha.length - 1)) }
              .joinToString("")
              .capitalize()
      val lastName =
          (0..6)
              .map { alpha.get(Random.nextInt(0, alpha.length - 1)) }
              .joinToString("")
              .capitalize()
      val email = "${firstName.toLowerCase()}@${lastName.toLowerCase()}.com"

      return Candidate(id = id, firstName = firstName, lastName = lastName, email = email)
    }
  }
}
