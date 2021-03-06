/*
 * Copyright 2006-2008 the original author or authors.
 *
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
 */
package org.springframework.batch.core.explore.support;

import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;
import static org.easymock.EasyMock.createMock;
import static org.junit.Assert.assertNotNull;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.explore.support.JobExplorerFactoryBean;

/**
 * @author Dave Syer
 * 
 */
public class JobExplorerFactoryBeanTests {

	private JobExplorerFactoryBean factory;

	private DataSource dataSource;

	private String tablePrefix = "TEST_BATCH_PREFIX_";

	@Before
	public void setUp() throws Exception {

		factory = new JobExplorerFactoryBean();
		dataSource = createMock(DataSource.class);
		factory.setDataSource(dataSource);
		factory.setTablePrefix(tablePrefix);

	}

	@Test
	public void testMissingDataSource() throws Exception {

		factory.setDataSource(null);
		try {
			factory.afterPropertiesSet();
			fail();
		}
		catch (IllegalArgumentException ex) {
			// expected
			String message = ex.getMessage();
			assertTrue("Wrong message: " + message, message.indexOf("DataSource") >= 0);
		}

	}

	@Test
	public void testCreateExplorer() throws Exception {

		factory.afterPropertiesSet();
		JobExplorer explorer = (JobExplorer) factory.getObject();
		assertNotNull(explorer);

	}

}
