package org.dieschnittstelle.jee.esa.basics;


import org.dieschnittstelle.jee.esa.basics.annotations.AnnotatedStockItemBuilder;
import org.dieschnittstelle.jee.esa.basics.annotations.StockItemProxyImpl;
import org.dieschnittstelle.jee.esa.basics.annotations.DisplayAs;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.dieschnittstelle.jee.esa.utils.Utils.*;

public class ShowAnnotations {

    public static void main(String[] args) {
        // we initialise the collection
        StockItemCollection collection = new StockItemCollection(
                "stockitems_annotations.xml", new AnnotatedStockItemBuilder());
        // we load the contents into the collection
        collection.load();

        for (IStockItem consumable : collection.getStockItems()) {
            ;
            showAttributes(((StockItemProxyImpl) consumable).getProxiedObject());
        }

        // we initialise a consumer
        Consumer consumer = new Consumer();
        // ... and let them consume
        consumer.doShopping(collection.getStockItems());
    }

    /*
     * UE BAS2
     */
    private static void showAttributes(Object consumable) {
        try {

            Class klass = consumable.getClass();

            StringBuilder output = new StringBuilder("{" + klass.getSimpleName() + ": ");

            List<Method> getterMethod = Arrays.stream(klass.getDeclaredMethods())
                    .filter(x -> x.getName().startsWith("get")).collect(Collectors.toList());

            int i = 0;
            for (Method getter : getterMethod) {
                i++;

                if (getter.isAnnotationPresent(DisplayAs.class)) {
                    output.append(getter.getAnnotation(DisplayAs.class).value()).append(":").append(getter.invoke(consumable));
                } else {
                    String[] split = getter.getName().split("get");
                    output.append(split[1].toLowerCase()).append(":").append(getter.invoke(consumable));
                }

                if (i != getterMethod.size()) {
                    output.append(", ");
                }
            }

            output.append("}");
            show(output);

        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

}
