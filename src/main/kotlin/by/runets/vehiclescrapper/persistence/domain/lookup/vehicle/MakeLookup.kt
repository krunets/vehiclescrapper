package by.runets.vehiclescrapper.persistence.domain.lookup.vehicle

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.util.*

@Table("make_lookup")
class MakeLookup {
    @Id
    var id: UUID? = null
    var type: String? = ""

    constructor()
    constructor(type: String?) {
        this.type = type
    }
    constructor(id: UUID?, type: String?) {
        this.id = id
        this.type = type
    }

    override fun toString(): String {
        return "MakeLookup(type=$type, id=$id"
    }
}