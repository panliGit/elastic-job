/**
 * Copyright 1999-2015 dangdang.com.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package com.dangdang.ddframe.job.internal.listener;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.unitils.util.ReflectionUtils;

import com.dangdang.ddframe.job.api.JobConfiguration;
import com.dangdang.ddframe.job.fixture.TestJob;
import com.dangdang.ddframe.job.internal.config.ConfigurationListenerManager;
import com.dangdang.ddframe.job.internal.election.ElectionListenerManager;
import com.dangdang.ddframe.job.internal.execution.ExecutionListenerManager;
import com.dangdang.ddframe.job.internal.failover.FailoverListenerManager;
import com.dangdang.ddframe.job.internal.server.JobOperationListenerManager;
import com.dangdang.ddframe.job.internal.sharding.ShardingListenerManager;

public class ListenerManagerTest {
    
    @Mock
    private ElectionListenerManager electionListenerManager;
    
    @Mock
    private ShardingListenerManager shardingListenerManager;
    
    @Mock
    private ExecutionListenerManager executionListenerManager;
    
    @Mock
    private FailoverListenerManager failoverListenerManager;
    
    @Mock
    private JobOperationListenerManager jobOperationListenerManager;
    
    @Mock
    private ConfigurationListenerManager configurationListenerManager;
    
    private final ListenerManager listenerManager = new ListenerManager(null, new JobConfiguration("testJob", TestJob.class, 3, "0/1 * * * * ?"));
    
    @Before
    public void setUp() throws NoSuchFieldException {
        MockitoAnnotations.initMocks(this);
        ReflectionUtils.setFieldValue(listenerManager, "electionListenerManager", electionListenerManager);
        ReflectionUtils.setFieldValue(listenerManager, "shardingListenerManager", shardingListenerManager);
        ReflectionUtils.setFieldValue(listenerManager, "executionListenerManager", executionListenerManager);
        ReflectionUtils.setFieldValue(listenerManager, "failoverListenerManager", failoverListenerManager);
        ReflectionUtils.setFieldValue(listenerManager, "jobOperationListenerManager", jobOperationListenerManager);
        ReflectionUtils.setFieldValue(listenerManager, "configurationListenerManager", configurationListenerManager);
    }
    
    @Test
    public void assertStartAllListeners() {
        listenerManager.startAllListeners();
        verify(electionListenerManager).start();
        verify(shardingListenerManager).start();
        verify(executionListenerManager).start();
        verify(failoverListenerManager).start();
        verify(jobOperationListenerManager).start();
        verify(configurationListenerManager).start();
    }
}
