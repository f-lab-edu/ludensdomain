package com.ludensdomain.util;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import static com.ludensdomain.util.ReplicationKeyConstants.MASTER;
import static com.ludensdomain.util.ReplicationKeyConstants.SLAVE;

/*
 * 멀티 DataSource 환경에서 특정 DataSource를 선택하는 인터페이스
 * 현재 DataSource가 read-only(읽기 기능만 사용)라면 slave 서버를 사용하고, 아니라면 master 서버를 사용한다.
 */
public class RoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return TransactionSynchronizationManager.isCurrentTransactionReadOnly()
                ? SLAVE
                : MASTER;
    }
}
