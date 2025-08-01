/**
 * Copyright (c) 2025 Contributors to the Eclipse Foundation
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
package ee.jakarta.tck.data.framework.read.only;

@jakarta.nosql.Embeddable
@jakarta.persistence.Embeddable
public class City {

    @jakarta.nosql.Column("CITYNAME")
    @jakarta.persistence.Column(name = "CITYNAME", nullable = false)
    private String name;

    @jakarta.nosql.Column("CITYPOP")
    @jakarta.persistence.Column(name = "CITYPOP", nullable = false)
    private int population;

    public City() {
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public static City of(String name, int population) {
        City city = new City();
        city.setName(name);
        city.setPopulation(population);
        return city;
    }

    public void setName(String value) {
        name = value;
    }

    public void setPopulation(int value) {
        population = value;
    }
}
