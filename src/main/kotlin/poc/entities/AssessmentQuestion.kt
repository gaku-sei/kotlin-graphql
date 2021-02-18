package poc.entities

import org.valiktor.functions.*
import org.valiktor.validate

/**
 * An assessment question
 * @property id the id of the question
 * @property label the label displayed for this question
 * @property lang the language used for the question
 * @property answers all the possible answers for this question
 */
data class AssessmentQuestion(
    val id: Int? = null,
    val label: String,
    val lang: Lang,
    val answers: List<AssessmentAnswer>
) {
  init {
    validate(this) {
      validate(AssessmentQuestion::id).isPositive()
      validate(AssessmentQuestion::label).isNotBlank()
      validate(AssessmentQuestion::lang)
      validate(AssessmentQuestion::answers).isNotEmpty()
    }
  }
}
