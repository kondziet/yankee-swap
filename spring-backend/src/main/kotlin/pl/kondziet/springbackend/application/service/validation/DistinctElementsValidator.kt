package pl.kondziet.springbackend.application.service.validation

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext

class DistinctElementsValidator : ConstraintValidator<DistinctElements, Collection<*>> {
    override fun isValid(value: Collection<*>?, context: ConstraintValidatorContext?): Boolean {
        if (value == null) return true

        return value.distinct().size == value.size
    }
}