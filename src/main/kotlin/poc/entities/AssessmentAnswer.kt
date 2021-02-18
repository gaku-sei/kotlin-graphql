package poc.entities

import org.valiktor.functions.*
import org.valiktor.validate

data class AssessmentAnswer(
    val id: Int? = null,
    val label: String,
) {
  init {
    validate(this) {
      validate(AssessmentAnswer::id).isPositive()
      validate(AssessmentAnswer::label).isNotBlank()
    }
  }
}
