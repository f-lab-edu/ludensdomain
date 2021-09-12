package com.ludensdomain.mapper;

import com.ludensdomain.dto.PurchaseDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PurchaseMapper {

    void buy(PurchaseDto purchaseInfo);

    void refund(long purchaseId);

}
