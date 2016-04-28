/*
 * Copyright (C) 2011 Markus Junginger, greenrobot (http://greenrobot.de)
 *
 * This file is part of greenDAO Generator.
 * 
 * greenDAO Generator is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * greenDAO Generator is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with greenDAO Generator.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.greenrobot.daogenerator;


import java.util.List;

public class Index extends PropertyOrderList {
    private String name;
    private boolean unique;
    private boolean nonDefaultName;

    public String getName() {
        return name;
    }

    public Index setName(String name) {
        this.name = name;
        this.nonDefaultName = name != null;
        return this;
    }

    public Index makeUnique() {
        unique = true;
        return this;
    }

    public boolean isUnique() {
        return unique;
    }

    public boolean isNonDefaultName() {
        return nonDefaultName;
    }

    /** used internally to know if name is generated by greenDAO */
    void setDefaultName(String name) {
        this.name = name;
        this.nonDefaultName = false;
    }

    public String getIndexSpec() {
        final List<Property> properties = getProperties();
        final List<String> propertiesOrder = getPropertiesOrder();
        final StringBuilder builder = new StringBuilder();
        final int size = properties.size();
        for (int i = 0; i < size; i++) {
            final Property property = properties.get(i);
            final String order = propertiesOrder.get(i);
            builder.append(property.getPropertyName());
            if (order != null) {
                builder.append(' ').append(order);
            }
            if (i < size - 1) {
                builder.append(", ");
            }
        }
        return builder.toString();
    }
}
