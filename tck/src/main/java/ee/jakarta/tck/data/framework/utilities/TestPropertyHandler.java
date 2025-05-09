/*
 * Copyright (c) 2023 Contributors to the Eclipse Foundation
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v. 2.0, which is available at
 * http://www.eclipse.org/legal/epl-2.0.
 *
 * This Source Code may also be made available under the following Secondary
 * Licenses when the conditions for such availability set forth in the
 * Eclipse Public License v. 2.0 are satisfied: GNU General Public License,
 * version 2 with the GNU Classpath Exception, which is available at
 * https://www.gnu.org/software/classpath/license.html.
 *
 * SPDX-License-Identifier: EPL-2.0 OR GPL-2.0 WITH Classpath-exception-2.0
 */
package ee.jakarta.tck.data.framework.utilities;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.asset.StringAsset;
import org.jboss.shrinkwrap.api.container.ResourceContainer;

/**
 * This uitlity class handles the caching and loading of test properties between
 * the client and container when tests are run inside an Arquillian container.
 */
public class TestPropertyHandler {

    private static final Logger log = Logger.getLogger(TestPropertyHandler.class.getCanonicalName());

    private static final String PROP_FILE = "tck.properties";
    private static Properties foundProperties;

    private TestPropertyHandler() {
        //UTILITY CLASS
    }

    /**
     * Container: Load properties from the TestProperty cache file, and return a
     * properties object. If any error occurs in finding the cache file, or
     * loading the properties, then an empty properties object is returned.
     *
     * @return - the cached properties, or an empty properties object.
     */
    static Properties loadProperties() {
        if (foundProperties != null) {
            return foundProperties;
        }

        //Try to load property file
        foundProperties = new Properties();
        InputStream propsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(PROP_FILE);
        if (propsStream != null) {
            try {
                foundProperties.load(propsStream);
            } catch (Exception e) {
                log.info("Attempted to load properties from resource " + PROP_FILE + " but failed. Because: " + e.getLocalizedMessage());
            }
        }

        return foundProperties;
    }

    /**
     * Client: Store system properties from the client to a properties file as a
     * resource on the archive sent to the container.
     *
     * @param archive - The archive going to the container
     * @return the archive with a resource file attached
     */
    public static Archive<?> storeProperties(Archive<?> archive) {
        if (!(archive instanceof ResourceContainer)) {
            throw new RuntimeException("Could not store properties to archive, because it was not a ResourceConatiner. "
                    + "Please raise an issue with the maintainers of the Jakarta Data TCK.");
        }

        Properties filteredProps = new Properties();
        for (TestProperty prop : TestProperty.values()) {
            if (prop.getKey().startsWith("java.")) {
                continue;
            }
            if (prop.isSet())
                filteredProps.put(prop.getKey(), prop.getValue());
        }

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            filteredProps.store(out, "System properties shared with Arquillian container");
            ((ResourceContainer<?>) archive).addAsResource(new StringAsset(out.toString()), PROP_FILE);
        } catch (Exception e) {
            throw new RuntimeException("Could not store properties file to archive", e);
        }

        return archive;
    }
}
