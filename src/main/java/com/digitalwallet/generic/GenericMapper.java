package com.digitalwallet.generic;

public interface GenericMapper<T extends GenericEntity , D extends GenericDto> {
    public D toBaseDto(T baseEntity) ;
    public  T toBaseEntity(D baseDto) ;
    public Iterable<D> toBaseDtoList(Iterable<T> baseEntityList) ;
    public Iterable<T> toBaseEntityList(Iterable<D> baseDtoList) ;
}
