package by.runets.vehiclescrapper.persistence.service.lookup.vehicle

import by.runets.vehiclescrapper.persistence.domain.lookup.vehicle.TransmissionType
import by.runets.vehiclescrapper.persistence.repository.lookup.vehicle.TransmissionTypeRepository
import by.runets.vehiclescrapper.persistence.service.AbstractService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.reactive.TransactionalOperator

@Service
class TransmissionTypeService(@Autowired private var transmissionTypeRepository: TransmissionTypeRepository,
                              @Autowired private var transactionalOperator: TransactionalOperator
) : AbstractService<TransmissionType>(repository = transmissionTypeRepository,
        transactionalOperator = transactionalOperator)