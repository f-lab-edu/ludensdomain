package com.ludensdomain.util;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import static com.ludensdomain.util.ReplicationKeyConstants.MASTER;
import static com.ludensdomain.util.ReplicationKeyConstants.SLAVE;

/*
 * 어떤 DataSource를 routing해 사용할지 선택하는 클래스
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
