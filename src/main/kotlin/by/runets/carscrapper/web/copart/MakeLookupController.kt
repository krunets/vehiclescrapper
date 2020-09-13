package by.runets.carscrapper.web.copart

import by.runets.carscrapper.database.domain.lookup.vehicle.MakeLookup
import by.runets.carscrapper.database.service.lookup.vehicle.MakeLookupService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("v1/make")
class MakeLookupController(@Autowired private var makeLookupService: MakeLookupService) {

    @PostMapping
    suspend fun saveMakeLookup(@RequestBody makeLookup: MakeLookup) {
        makeLookupService.save(makeLookup)
    }

    @GetMapping("/all")
    suspend fun readAll(): Set<MakeLookup> {
        return makeLookupService.findAll()
    }
}