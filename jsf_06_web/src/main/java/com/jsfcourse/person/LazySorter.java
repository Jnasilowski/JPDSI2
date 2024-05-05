package com.jsfcourse.person;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.util.Comparator;
import com.jsf.entities.Car;

import javax.swing.SortOrder;
import jakarta.*;

public class LazySorter implements Comparator<Car> {
	public static final Object getPropertyValueViaReflection(Object o, String field)
            throws ReflectiveOperationException, IllegalArgumentException, IntrospectionException {
return new PropertyDescriptor(field, o.getClass()).getReadMethod().invoke(o);
}
    private String sortField;
    private SortOrder sortOrder;

    public LazySorter(String sortField, SortOrder sortOrder2) {
        this.sortField = sortField;
        this.sortOrder = sortOrder2;
    }

    public int compare(Car customer1, Car customer2) {
        try {
            Object value1 = getPropertyValueViaReflection(customer1, sortField);
            Object value2 = getPropertyValueViaReflection(customer2, sortField);

            int value = ((Comparable) value1).compareTo(value2);

            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}