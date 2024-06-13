package com.devsu.accountOperation.infraestructure.vo;

import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import java.util.List;
/**
 * Mapping object for REST API communication
 *
 * @author Olman Ibanez
 */
@Generated
@Getter
@Setter
public class ReportVO {
    private String clientName;
    private Date startDate;
    private Date endDate;
    private List<AccountVO> accounts;
    private List<OperationVO> operations;
}
