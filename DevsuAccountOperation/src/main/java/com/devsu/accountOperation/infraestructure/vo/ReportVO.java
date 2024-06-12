package com.devsu.accountOperation.infraestructure.vo;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Generated
@Getter
@Setter
/**
 * Objeto de mapeo para comunicación API REST
 *
 * @author Olman Ibanez
 */
public class ReportVO {
    private String clientName;
    private Date startDate;
    private Date endDate;
    private List<AccountVO> accounts;
    private List<OperationVO> operations;
}
