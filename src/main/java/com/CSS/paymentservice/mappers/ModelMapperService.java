package com.CSS.paymentservice.mappers;

import org.modelmapper.ModelMapper;

public interface ModelMapperService
{
    ModelMapper forResponse();
    ModelMapper forRequest();
}
