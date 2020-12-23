package by.runets.vehiclescrapper.scrapper.copart.processor.impl.lookup

import by.runets.vehiclescrapper.persistence.domain.lookup.vehicle.FuelType
import by.runets.vehiclescrapper.persistence.domain.lookup.vehicle.MakeLookup
import by.runets.vehiclescrapper.scrapper.copart.processor.impl.AbstractScrapperProcessor
import by.runets.vehiclescrapper.scrapper.copart.utils.HtmlTagUtils
import by.runets.vehiclescrapper.scrapper.copart.utils.ScrapperUtils.Companion.waitBy
import org.openqa.selenium.By
import org.openqa.selenium.chrome.ChromeDriver
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class FuelTypeScrapperProcessor(@Autowired private val chromeDriver: ChromeDriver) : AbstractScrapperProcessor<FuelType>() {
    override fun scrapByCriteria(searchCriteria : Map<String, Any>?): Set<FuelType> {
        val fuelTypeSet = mutableSetOf<FuelType>()

        val page = "https://www.copart.com/search/"

        val makeLookup = searchCriteria?.get(MakeLookup::javaClass.name) as MakeLookup
        chromeDriver.get(page + makeLookup.type!!.toLowerCase())

        waitBy(chromeDriver, By.className(HtmlTagUtils.LIST_GROUP_ITEM))
        chromeDriver.findElements(By.name(HtmlTagUtils.FUEL))
                .forEach { pageData -> fuelTypeSet.add(FuelType(pageData.getAttribute(HtmlTagUtils.VALUE_ATTRIBUTE), makeLookup.id!!)) }

        return fuelTypeSet
    }
}