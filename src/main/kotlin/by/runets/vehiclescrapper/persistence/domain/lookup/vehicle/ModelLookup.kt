package by.runets.vehiclescrapper.persistence.domain.lookup.vehicle

import org.springframework.data.relational.core.mapping.Table
import java.util.*

@Table("model_lookup")
data class ModelLookup(
        private val id: UUID,
        private val make: UUID,
        private val model: String
)