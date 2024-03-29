package by.runets.vehiclescrapper.persistence.domain.lookup.vehicle

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.util.*

@Table("model_lookup")
class ModelLookup(make: UUID, model: String) {
    @Id
    var id: UUID? = null
    var make: UUID? = make
    var model: String? = model

    override fun toString(): String {
        return "ModelLookup(id=$id, make=$make, model=$model)"
    }
}