package com.mayurdw.bankstatementreader.data.mapper

interface EntityMapper<Entity, DomainModel> {
    fun mapFromEntity(entity: Entity): DomainModel
    fun mapToEntity(domainModel: DomainModel): Entity

    fun mapFromEntities(entityList: List<Entity>): List<DomainModel> {
        return entityList.map { mapFromEntity(it) }
    }

    fun mapToEntities(domainModels: List<DomainModel>) : List<Entity> {
        return domainModels.map { mapToEntity(it) }
    }
}